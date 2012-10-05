package com.twoclams.hww.server.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.twoclams.hww.server.cache.CacheManager;
import com.twoclams.hww.server.dao.SocialStatusDao;
import com.twoclams.hww.server.model.DailyBonus;
import com.twoclams.hww.server.model.DailyBonus.Reward;
import com.twoclams.hww.server.model.House;
import com.twoclams.hww.server.model.Housewife;
import com.twoclams.hww.server.model.Husband;
import com.twoclams.hww.server.model.OtherPlayerProfileResponse;
import com.twoclams.hww.server.model.SimpleResponse;
import com.twoclams.hww.server.model.SynchronizeResponse;
import com.twoclams.hww.server.service.UsersService;
import com.twoclams.hww.server.utils.DateUtils;

@Service
public class UsersServiceImpl implements UsersService {
	private static final Log logger = LogFactory.getLog(UsersServiceImpl.class);

	@Autowired
	@Qualifier("wifeDao")
	private CacheManager wifeDao;

	@Autowired
	@Qualifier("husbandDao")
	private CacheManager husbandDao;

	@Autowired
	private SocialStatusDao statusDao;

	@Override
	public SimpleResponse registeUser(Housewife wife, Husband husband) {
		if (wife != null) {
			logger.info("Saving wife: " + wife.toString());
			wifeDao.store(wife.getId() + "-wife", wife, -1);
			statusDao.updateSocialStatusPoints(wife);
		}
		if (husband != null) {
			logger.info("Saving husband: " + husband.toString());
			husbandDao.store(husband.getId() + "-husband", husband, -1);
		}
		return getOKResponse();
	}

	private SimpleResponse getOKResponse() {
		int returnCode = REGISTRATION_RETURN_CODE_OK;
		SimpleResponse response = new SimpleResponse(returnCode, null);
		return response;
	}

	@Override
	public SimpleResponse synchronizeHusband(Husband husband) {
		husbandDao.store(husband.getId() + "-husband", husband, -1);
		return getOKResponse();
	}

	@Override
	public SimpleResponse synchronizeHousewife(Housewife housewife) {
		if (housewife.getSkinTone() != null) {
			logger.info("Saving wife: " + housewife.toString());
			wifeDao.store(housewife.getId() + "-wife", housewife, -1);
		} else {
			Housewife storedWife = this.getWife(housewife.getId());
			if (storedWife != null) {
				housewife.setSkinTones(storedWife.getSkinTone());
				wifeDao.store(housewife.getId() + "-wife", housewife, -1);
			}
		}
		statusDao.updateSocialStatusPoints(housewife);
		return getOKResponse();
	}

	@Override
	public OtherPlayerProfileResponse getOtherPlayerProfile(String papayaUserId) {
		OtherPlayerProfileResponse response = new OtherPlayerProfileResponse();
		Husband husband = findHusband(papayaUserId);
		if (husband == null) {
			husband = new Husband(papayaUserId, 0, "Federico", 7, 4, 3, 5);
		}
		response.setHusband(husband);
		Housewife wife = findHousewife(papayaUserId);
		if (wife == null) {
			wife = new Housewife("123", "Paula", 345000, Housewife.Type.Modern,
					new Integer[] { 85, 79, 66 }, 2, 3);
		}
		response.setWife(wife);
		// House house = (House) wifeDao.get("house-" + papayaUserId);
		response.setHouseLevel(3);
		return response;
	}

	private Husband findHusband(String papayaUserId) {
		Husband husband = (Husband) husbandDao.get(papayaUserId + "-husband");
		return husband;
	}

	@Override
	public Housewife findBestHousewife() {
		String papayaUserId = statusDao.getHighestScore();
		Housewife wife = findHousewife(papayaUserId);
		if (wife == null) {
			wife = new Housewife("12323", "Michi", 3000, Housewife.Type.Modern,
					new Integer[] { 85, 79, 66 }, 2, 3);
		}
		return wife;
	}

	private Housewife findHousewife(String papayaUserId) {
		Housewife wife = (Housewife) wifeDao.get(papayaUserId + "-wife");
		return wife;
	}

	@Override
	public Reward getDailyBonus(String papayaUserId) {
		DailyBonus dailyBonus = (DailyBonus) wifeDao.get("daily-"
				+ papayaUserId);
		Reward reward = null;
		if (dailyBonus == null) {
			dailyBonus = new DailyBonus();
			reward = dailyBonus.getReward();
		} else {
			DateTime start = new DateTime(DateUtils.getDayOf(dailyBonus
					.getLastLogin()));
			DateTime end = new DateTime(DateUtils.getCurrentDay());
			Days days = Days.daysBetween(start, end);
			if (days.getDays() == 1) {
				dailyBonus.increment();
				if (dailyBonus.getCount() == 6) {
					dailyBonus.reset();
				}
				reward = dailyBonus.getReward();
			} else {
				if (days.getDays() != 0) {
					dailyBonus.reset();
					reward = dailyBonus.getReward();
				}
			}
		}
		wifeDao.store("daily-" + papayaUserId, dailyBonus, -1);
		return reward;
	}

	@Override
	public SimpleResponse synchronizeHouse(String papayaUserId, House house) {
		logger.info("Saving house: " + house.toString());
		wifeDao.store("house-" + papayaUserId, house, -1);
		return new SimpleResponse();
	}

	@Override
	public House getHouse(String papayaUserId) {
		House house = (House) wifeDao.get("house-" + papayaUserId);
		if (house == null) {
			house = new House();
		}
		return house;
	}

	@Override
	public Housewife getWife(String papayaUserId) {
		return findHousewife(papayaUserId);
	}

	@Override
	public Husband getHusband(String papayaUserId) {
		return findHusband(papayaUserId);
	}

	@Override
	public SynchronizeResponse synchronizeGame(String papayaUserId) {
		House house = this.getHouse(papayaUserId);
		if (house.isEmpty()) {
			house = null;
		}
		Housewife wife = this.getWife(papayaUserId);
		Husband husband = this.getHusband(papayaUserId);
		SynchronizeResponse response = new SynchronizeResponse(wife, husband,
				house);
		return response;
	}

}
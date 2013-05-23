package com.twoclams.hww.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twoclams.hww.server.cache.CacheManager;
import com.twoclams.hww.server.dao.SocialStatusDao;
import com.twoclams.hww.server.model.DailyBonus;
import com.twoclams.hww.server.model.DailyBonus.Reward;
import com.twoclams.hww.server.model.House;
import com.twoclams.hww.server.model.Housewife;
import com.twoclams.hww.server.model.Husband;
import com.twoclams.hww.server.model.OtherPlayerProfileResponse;
import com.twoclams.hww.server.model.Passport;
import com.twoclams.hww.server.model.Realstate;
import com.twoclams.hww.server.model.SimpleResponse;
import com.twoclams.hww.server.model.SynchronizeResponse;
import com.twoclams.hww.server.model.Wallet;
import com.twoclams.hww.server.service.UserReward;
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
            wifeDao.store(getWifeKey(wife.getId()), wife, -1);
            statusDao.updateSocialStatusPoints(wife);
        }
        if (husband != null) {
            logger.info("Saving husband: " + husband.toString());
            husbandDao.store(getHusbandKey(husband.getPapayaUserId()), husband, -1);
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
        husbandDao.store(getHusbandKey(husband.getPapayaUserId()), husband, -1);
        return getOKResponse();
    }

    @Override
    public SimpleResponse synchronizeHousewife(Housewife housewife) {
        if (housewife.getSkinTone() != null) {
            logger.info("Saving wife: " + housewife.toString());
            wifeDao.store(getWifeKey(housewife.getId()), housewife, -1);
        } else {
            Housewife storedWife = this.getWife(housewife.getId());
            if (storedWife != null) {
                housewife.setSkinTones(storedWife.getSkinTone());

                wifeDao.store(getWifeKey(housewife.getId()), housewife, -1);
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
            husband = new Husband();
        }
        response.setHusband(husband);
        Housewife wife = findHousewife(papayaUserId);
        if (wife == null) {
            wife = new Housewife("123", "MysteriousWife", 345000, Housewife.Type.Modern, new Integer[] { 85, 79, 66 },
                    2, 3, new Integer[] {}, new HashMap<String, String>(), 0);
        }
        response.setWife(wife);
        response.setHouseLevel(3);
        return response;
    }

    private Husband findHusband(String papayaUserId) {
        Husband husband = (Husband) husbandDao.get(getHusbandKey(papayaUserId));
        return husband;
    }

    private String getHusbandKey(String papayaUserId) {
        return papayaUserId + "-husband";
    }

    @Override
    public List<Housewife> findBestHousewife() {
        List<Housewife> bestwifes = statusDao.getHighestScore();
        List<Housewife> wives = new ArrayList<Housewife>();
        for (Housewife bestwife : bestwifes) {
            Housewife wife = findHousewife(bestwife.getId());
            if (wife == null) {
                wife = new Housewife("12323", "MysteriousWife", 3000, Housewife.Type.Modern,
                        new Integer[] { 85, 79, 66 }, 2, 3, new Integer[] {}, new HashMap<String, String>(), 0);
            }
            wife.setSocialStatusPoints(bestwife.getSocialStatusPoints());
            wives.add(wife);
        }
        return wives;
    }

    @Override
    public List<Housewife> findTop25() {
        List<Housewife> bestwifes = statusDao.getTop25();
        List<Housewife> wives = new ArrayList<Housewife>();
        for (Housewife bestwife : bestwifes) {
            Housewife wife = findHousewife(bestwife.getId());
            if (wife == null) {
                wife = new Housewife(bestwife.getId(), "MysteriousWife", 3000, Housewife.Type.Modern,
                        new Integer[] { 85, 79, 66 }, 2, 3, new Integer[] {}, new HashMap<String, String>(), 0);
            }
            wife.nullify();
            wife.setSocialStatusPoints(bestwife.getSocialStatusPoints());
            wife.careName();
            wives.add(wife);
        }
        return wives;
    }

    private Housewife findHousewife(String papayaUserId) {
        Housewife wife = (Housewife) wifeDao.get(getWifeKey(papayaUserId));
        return wife;
    }

    private String getWifeKey(String papayaUserId) {
        return papayaUserId + "-wife";
    }

    @Override
    public Reward getDailyBonus(String papayaUserId) {
        DailyBonus dailyBonus = (DailyBonus) wifeDao.get(getDailyRewardKey(papayaUserId));
        Reward reward = null;
        if (dailyBonus == null) {
            dailyBonus = new DailyBonus();
            reward = dailyBonus.getReward();
        } else {
            DateTime start = new DateTime(DateUtils.getDayOf(dailyBonus.getLastLogin()));
            DateTime end = new DateTime(DateUtils.getCurrentDay());
            Days days = Days.daysBetween(start, end);
            if (days.getDays() >= 1) {
                dailyBonus.increment();
                if (dailyBonus.getCount() == 6) {
                    dailyBonus.reset();
                }
                reward = dailyBonus.getReward();
            }
        }
        wifeDao.store(getDailyRewardKey(papayaUserId), dailyBonus, -1);
        return reward;
    }

    private String getDailyRewardKey(String papayaUserId) {
        return "daily-" + papayaUserId;
    }

    @Override
    public SimpleResponse synchronizeHouse(String papayaUserId, House house) {
        logger.info("Saving house: " + house.toString());
        wifeDao.store(getHouseKey(papayaUserId), house, -1);
        return new SimpleResponse();
    }

    @Override
    public House getHouse(String papayaUserId) {
        House house = (House) wifeDao.get(getHouseKey(papayaUserId));
        if (house == null) {
            house = new House();
        }
        return house;
    }

    private String getHouseKey(String papayaUserId) {
        return "house-" + papayaUserId;
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
        } else {
            house.updateItemId();
        }
        Housewife wife = this.getWife(papayaUserId);
        Husband husband = this.getHusband(papayaUserId);
        Wallet wallet = (Wallet) wifeDao.get(this.getWalletKey(papayaUserId));
        Passport passport = (Passport) wifeDao.get(this.getPassportKey(papayaUserId));
        Realstate realstate = (Realstate) wifeDao.get(this.getRealstateKey(papayaUserId));
        return new SynchronizeResponse(wife, husband, house, wallet, passport, realstate);
    }

    @Override
    public SimpleResponse synchronizeWallet(Wallet wallet) {
        wifeDao.store(getWalletKey(wallet.getPapayaUserId()), wallet, -1);
        return new SimpleResponse();
    }

    private String getWalletKey(String papayaUserId) {
        return papayaUserId + "-wallet";
    }

    @Override
    public SimpleResponse synchronizePassport(String papayaUserId, Passport passport) {
        logger.info("Saving passport: " + papayaUserId + passport);
        wifeDao.store(getPassportKey(papayaUserId), passport, -1);
        return new SimpleResponse();
    }

    private String getPassportKey(String papayaUserId) {
        return papayaUserId + "-passport";
    }

    @Override
    public SimpleResponse synchronizeRealstate(String papayaUserId, Realstate realstate) {
        logger.info("Saving realstate: " + papayaUserId + realstate);
        wifeDao.store(getRealstateKey(papayaUserId), realstate, -1);
        return new SimpleResponse();
    }

    private String getRealstateKey(String papayaUserId) {
        return papayaUserId + "-realstate";
    }

    @Override
    public Map<String, Housewife> checkUsers(List<String> papayaUserIds) {
        Map<String, Housewife> housewifePerPapayaUserId = wifeDao.get(Housewife.class, papayaUserIds.toArray(new String[]{}));
        return housewifePerPapayaUserId;
    }

    @Override
    @Transactional
    public void finishTournament() {
        List<Housewife> top25 = this.statusDao.getTop25();
        for (int i = 0; i < top25.size(); i++) {
            Housewife wife = top25.get(i);
            int amount = 0;
            if (i+1 == 1) {
                amount = 10;
            } else if (i+1 == 2) {
                amount = 7;
            } else if (i+1 == 3) {
                amount = 5;
            } else {
                amount = 2;
            }
            statusDao.reward(wife.getId(), amount, "Diamonds");
        }
    }

    @Override
    @Transactional
    public List<UserReward> getPendingRewards(String papayaUserId) {
        List<UserReward> rewards = statusDao.getPendingRewards(papayaUserId);
        for (UserReward reward : rewards) {
            statusDao.rewardClaimed(reward.getId());
        }
        return rewards;
    }

    @Override
    public int getUserWeeklyScore(String papayaUserId) {
        return statusDao.getWeeklyScore(papayaUserId);
    }

}
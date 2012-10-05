package com.twoclams.hww.server.service;

import com.twoclams.hww.server.model.DailyBonus.Reward;
import com.twoclams.hww.server.model.House;
import com.twoclams.hww.server.model.Housewife;
import com.twoclams.hww.server.model.Husband;
import com.twoclams.hww.server.model.OtherPlayerProfileResponse;
import com.twoclams.hww.server.model.SimpleResponse;
import com.twoclams.hww.server.model.SynchronizeResponse;

public interface UsersService {
    static final int REGISTRATION_RETURN_CODE_OK = 0;
    static final int REGISTRATION_RETURN_CODE_DUPLICATED_ID = 1;

    SimpleResponse registeUser(Housewife housewife, Husband husband);

    SimpleResponse synchronizeHusband(Husband husband);

    SimpleResponse synchronizeHousewife(Housewife housewife);

    OtherPlayerProfileResponse getOtherPlayerProfile(String papayaUserId);

    Housewife findBestHousewife();

    Reward getDailyBonus(String papayaUserId);

    SimpleResponse synchronizeHouse(String papayaUserId, House house);

    House getHouse(String papayaUserId);

    Housewife getWife(String papayaUserId);

    Husband getHusband(String papayaUserId);

    SynchronizeResponse synchronizeGame(String papayaUserId);

}
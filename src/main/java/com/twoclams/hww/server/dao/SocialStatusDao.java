package com.twoclams.hww.server.dao;

import java.util.List;

import com.twoclams.hww.server.model.Housewife;
import com.twoclams.hww.server.service.UserReward;

public interface SocialStatusDao {

    void updateSocialStatusPoints(Housewife housewife);

    List<Housewife> getHighestScore();

    List<Housewife> getTop25();

    void reward(String papayaUserId, int amount, String currency);

    List<UserReward> getPendingRewards(String papayaUserId);

    void rewardClaimed(long id);

    int getWeeklyScore(String papayaUserId);

}

package com.twoclams.hww.server.dao;

import com.twoclams.hww.server.model.Housewife;

public interface SocialStatusDao {

    void updateSocialStatusPoints(Housewife housewife);

    Housewife getHighestScore();
}

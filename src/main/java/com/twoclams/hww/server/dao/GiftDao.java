package com.twoclams.hww.server.dao;

import java.util.List;

import com.twoclams.hww.server.model.Gift;

public interface GiftDao {

    void saveGift(String papayaUserId, Gift gift);

    List<Gift> retrieveAllGifts(String papayaUserId);
}

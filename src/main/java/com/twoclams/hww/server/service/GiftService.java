package com.twoclams.hww.server.service;

import java.util.List;

import com.twoclams.hww.server.model.Gift;

public interface GiftService {

    void sendGift(String papayaUserId, Gift gift);

    List<Gift> retrieveAllGifts(String papayaUserId);

}

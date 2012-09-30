package com.twoclams.hww.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twoclams.hww.server.dao.GiftDao;
import com.twoclams.hww.server.model.Gift;
import com.twoclams.hww.server.service.GiftService;

@Service
public class GiftServiceImpl implements GiftService {
    @Autowired
    private GiftDao giftDao;

    @Override
    public void sendGift(String papayaUserId, Gift gift) {
        giftDao.saveGift(papayaUserId, gift);
    }

    @Override
    public List<Gift> retrieveAllGifts(String papayaUserId) {
        return giftDao.retrieveAllGifts(papayaUserId);
    }

}

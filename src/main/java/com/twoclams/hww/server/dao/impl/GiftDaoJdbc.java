package com.twoclams.hww.server.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.twoclams.hww.server.dao.GiftDao;
import com.twoclams.hww.server.model.Gift;

public class GiftDaoJdbc extends NamedParameterJdbcDaoSupport implements GiftDao {

    private Map<String, List<Gift>> giftsPerPapayaId = new HashMap<String, List<Gift>>();
    @Override
    public void saveGift(String papayaUserId, Gift gift) {
        List<Gift> gifts = giftsPerPapayaId.get(papayaUserId);
        if (gifts == null) {
            gifts = new ArrayList<Gift>();
        }

        gifts.add(gift);
        giftsPerPapayaId.put(papayaUserId, gifts);
    }

    @Override
    public List<Gift> retrieveAllGifts(String papayaUserId) {
        List<Gift> gifts = giftsPerPapayaId.remove(papayaUserId);
        if (gifts == null) {
            return new ArrayList<Gift>();
        }
        return gifts;
    }

}

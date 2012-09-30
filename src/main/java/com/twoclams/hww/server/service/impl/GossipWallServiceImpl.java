package com.twoclams.hww.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twoclams.hww.server.dao.GossipMessageDao;
import com.twoclams.hww.server.model.GossipWallMessage;
import com.twoclams.hww.server.model.GossipWallResponse;
import com.twoclams.hww.server.model.Housewife;
import com.twoclams.hww.server.service.GossipWallService;
import com.twoclams.hww.server.service.UsersService;

@Service
public class GossipWallServiceImpl implements GossipWallService {
    @Autowired
    private GossipMessageDao messageDao;

    @Autowired
    private UsersService userService;

    @Override
    @Transactional
    public void postGossipWall(GossipWallMessage message) {
        GossipWallMessage newMessage = new GossipWallMessage(message.getPapayaUserId(),
                message.getMessage(), message.getHouseWifeName());
        messageDao.add(newMessage);
    }

    @Override
    public GossipWallResponse findGossipWallResponse() {
        GossipWallResponse response = new GossipWallResponse();
        Housewife bestHousewife = userService.findBestHousewife();
        response.setBestHouseWife(bestHousewife);
        response.setMessages(messageDao.findLatestMessages());
        return response;
    }

}

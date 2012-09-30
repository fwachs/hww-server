package com.twoclams.hww.server.dao;

import java.util.List;

import com.twoclams.hww.server.model.GossipWallMessage;

public interface GossipMessageDao {

    List<GossipWallMessage> findLatestMessages();

    void add(GossipWallMessage newMessage);

}

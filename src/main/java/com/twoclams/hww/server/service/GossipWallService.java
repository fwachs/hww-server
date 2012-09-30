package com.twoclams.hww.server.service;

import com.twoclams.hww.server.model.GossipWallMessage;
import com.twoclams.hww.server.model.GossipWallResponse;

public interface GossipWallService {

    void postGossipWall(GossipWallMessage message);

    GossipWallResponse findGossipWallResponse();

}

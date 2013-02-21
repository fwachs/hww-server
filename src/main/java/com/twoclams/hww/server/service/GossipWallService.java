package com.twoclams.hww.server.service;

import com.twoclams.hww.server.model.GossipWallMessage;
import com.twoclams.hww.server.model.GossipWallResponse;
import com.twoclams.hww.server.model.TournamentResponse;

public interface GossipWallService {

    void postGossipWall(GossipWallMessage message);

    GossipWallResponse findGossipWallResponse(String papayaUserId);

    TournamentResponse findTournamentResponse();

}

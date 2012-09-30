package com.twoclams.hww.server.model;

import java.util.List;


public class GossipWallResponse {

    private List<GossipWallMessage> messages;
    private Housewife bestHouseWife;

    public List<GossipWallMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<GossipWallMessage> messages) {
        this.messages = messages;
    }

    public Housewife getBestHouseWife() {
        return bestHouseWife;
    }

    public void setBestHouseWife(Housewife bestHouseWife) {
        this.bestHouseWife = bestHouseWife;
    }

}

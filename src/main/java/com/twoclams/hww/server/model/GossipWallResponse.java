package com.twoclams.hww.server.model;

import java.util.List;

public class GossipWallResponse {

    private List<GossipWallMessage> messages;
    private Housewife bestHouseWife;
    private Housewife secondHouseWife;
    private Housewife thirdHouseWife;
    private String tournamentEndDate;
    private int weeklyScore;

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

    public Housewife getSecondHouseWife() {
        return secondHouseWife;
    }

    public void setSecondHouseWife(Housewife secondHouseWife) {
        this.secondHouseWife = secondHouseWife;
    }

    public Housewife getThirdHouseWife() {
        return thirdHouseWife;
    }

    public void setThirdHouseWife(Housewife thirdHouseWife) {
        this.thirdHouseWife = thirdHouseWife;
    }

    public String getTournamentEndDate() {
        return tournamentEndDate;
    }

    public void setTournamentEndDate(String tournamentEndDate) {
        this.tournamentEndDate = tournamentEndDate;
    }

    public int getWeeklyScore() {
        return weeklyScore;
    }

    public void setWeeklyScore(int weeklyScore) {
        this.weeklyScore = weeklyScore;
    }

}

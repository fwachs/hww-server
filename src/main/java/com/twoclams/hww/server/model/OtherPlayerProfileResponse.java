package com.twoclams.hww.server.model;

public class OtherPlayerProfileResponse {

    private Husband husband;
    private Housewife wife;
    private Integer houseLevel;

    public Housewife getWife() {
        return wife;
    }

    public void setWife(Housewife wife) {
        this.wife = wife;
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public Integer getHouseLevel() {
        return houseLevel;
    }

    public void setHouseLevel(Integer houseLevel) {
        this.houseLevel = houseLevel;
    }

}

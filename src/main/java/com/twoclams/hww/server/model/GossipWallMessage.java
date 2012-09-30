package com.twoclams.hww.server.model;

import java.io.Serializable;
import java.util.Date;

import com.twoclams.hww.server.utils.DateUtils;

public class GossipWallMessage implements Serializable {

    private static final long serialVersionUID = 3809164255191248746L;
    private String papayaUserId;
    private String message;
    private String timeAgo;
    private String houseWifeName;
    private int houseLevel;
    private Date createdOn;

    public GossipWallMessage() {
    }

    public GossipWallMessage(String papayaUserId, String message, String housewifeName) {
        this.papayaUserId = papayaUserId;
        this.message = message;
        this.houseWifeName = housewifeName;
        this.createdOn = new Date();
    }

    public GossipWallMessage(String message, String houseWifeName, int houseLevel, Date createdOn) {
        this.message = message;
        this.houseWifeName = houseWifeName;
        this.houseLevel = houseLevel;
        this.createdOn = createdOn;
        this.update();
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String minutesAgo) {
        this.timeAgo = minutesAgo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHouseWifeName() {
        return houseWifeName;
    }

    public void setHouseWifeName(String houseWifeName) {
        this.houseWifeName = houseWifeName;
    }

    public int getHouseLevel() {
        if (houseLevel == 0) {
            houseLevel = 1;
        }
        return houseLevel;
    }

    public void setTotalMessages(int houseLevel) {
        this.houseLevel = houseLevel;
    }

    public String getPapayaUserId() {
        return papayaUserId;
    }

    public void setPapayaUserId(String papayaUserId) {
        this.papayaUserId = papayaUserId;
    }

    public void setHouseLevel(int houseLevel) {
        this.houseLevel = houseLevel;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void update() {
        String amountOfTime = DateUtils.getTimeDiferenceAsString(this.createdOn);
        String unitOfTime = DateUtils.getTimeDiferenceUnitAsString(this.createdOn);
        this.timeAgo = amountOfTime + " " + unitOfTime + " ago";        
    }

}

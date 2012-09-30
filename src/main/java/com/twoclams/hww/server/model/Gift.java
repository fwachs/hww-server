package com.twoclams.hww.server.model;

public class Gift {

    private long id;
    private String senderName;
    private boolean opened;

    public Gift() {
    }

    public Gift(long id, String senderName) {
        this.id = id;
        this.senderName = senderName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

}

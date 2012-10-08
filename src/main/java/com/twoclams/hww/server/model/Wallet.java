package com.twoclams.hww.server.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Wallet implements Serializable {
    private static final long serialVersionUID = 4642456350403536432L;
    private String papayaUserId;
    private Long diamonds;
    private Long gameBucks;
    private Long miles;

    public Wallet(JSONObject jsonWallet) throws JSONException {
        this.papayaUserId = jsonWallet.getString("papayaUserId");
        this.diamonds = jsonWallet.getLong("diamonds");
        this.gameBucks = jsonWallet.getLong("gameBucks");
        this.miles = jsonWallet.getLong("miles");
    }

    public String getPapayaUserId() {
        return papayaUserId;
    }

    public Long getDiamonds() {
        return diamonds;
    }

    public Long getGameBucks() {
        return gameBucks;
    }

    public Long getMiles() {
        return miles;
    }

    @Override
    public String toString() {
        return "Wallet [papayaUserId=" + papayaUserId + ", diamonds=" + diamonds + ", gameBucks=" + gameBucks
                + ", miles=" + miles + "]";
    }

}

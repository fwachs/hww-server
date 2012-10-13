package com.twoclams.hww.server.model;

import java.io.Serializable;
import java.util.Arrays;

public class Realstate implements Serializable {

    private static final long serialVersionUID = -5241306104292438994L;
    private String papayaUserId;
    private Integer[] propertyListing;

    public Realstate(String papayaUserId, Integer[] propertyListing) {
        this.papayaUserId = papayaUserId;
        this.propertyListing = propertyListing;
    }

    public String getPapayaUserId() {
        return papayaUserId;
    }

    public Integer[] getPropertyListing() {
        return propertyListing;
    }

    @Override
    public String toString() {
        return "Realstate [papayaUserId=" + papayaUserId + ", propertyListing=" + Arrays.toString(propertyListing)
                + "]";
    }

}

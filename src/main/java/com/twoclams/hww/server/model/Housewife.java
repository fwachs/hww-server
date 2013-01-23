package com.twoclams.hww.server.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

public class Housewife implements Serializable {

    private static final long serialVersionUID = 3449992099839611264L;
    private String id;
    private String name;
    private int socialStatusPoints;
    private Type type;
    private Integer[] skinTone;
    private int hairColor;
    private int hairStyle;
    private Integer[] mysteryItems;
    private Map<String, String> clothingItems;
    private Integer lastMissionId;

    public Housewife() {
        
    }

    public Housewife(String papayaUserId, String name, int socialStatusPoints, Type type, Integer[] skinTone,
            int hairColor, int hairStyle, Integer[] mysteryItems, Map<String, String> clothingItems, Integer lastMissionId) {
        this.id = papayaUserId;
        this.name = name;
        this.socialStatusPoints = socialStatusPoints;
        this.type = type;
        this.skinTone = skinTone;
        this.hairColor = hairColor;
        this.hairStyle = hairStyle;
        this.mysteryItems = mysteryItems;
        this.clothingItems = clothingItems;
        this.lastMissionId = lastMissionId;
    }

    public enum Type {
        Modern, Rocker, Business, Retro, Celeb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSocialStatusPoints() {
        return socialStatusPoints;
    }

    public void setSocialStatusPoints(int socialStatusPoints) {
        this.socialStatusPoints = socialStatusPoints;
    }

    public String getType() {
        return type.name();
    }

    public void setType(String typeString) {
        this.type = Type.valueOf(typeString);
    }

    public Integer[] getSkinTone() {
        return skinTone;
    }

    public void setSkinTones(Integer[] skinTone) {
        this.skinTone = skinTone;
    }

    public int getHairColor() {
        return hairColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
    }

    public int getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(int hairStyle) {
        this.hairStyle = hairStyle;
    }

    public String getId() {
        return id;
    }

    public void setId(String papayaUserId) {
        this.id = papayaUserId;
    }

    @Override
    public String toString() {
        return "Housewife [id=" + id + ", name=" + name + ", socialStatusPoints=" + socialStatusPoints + ", type="
                + type + ", skinTone=" + Arrays.toString(skinTone) + ", hairColor=" + hairColor + ", hairStyle="
                + hairStyle + "]";
    }

    public Integer[] getMysteryItems() {
        return mysteryItems;
    }

    public Map<String, String> getClothingItems() {
        return clothingItems;
    }

    public Integer getLastMissionId() {
        if (lastMissionId == null) {
            return new Integer(1);
        }
        return lastMissionId;
    }

    public void setLastMissionId(Integer lastMissionId) {
        if (lastMissionId == null || new Integer(0).equals(lastMissionId)) {
            this.lastMissionId = 1;
        }
        this.lastMissionId = lastMissionId;
    }
}

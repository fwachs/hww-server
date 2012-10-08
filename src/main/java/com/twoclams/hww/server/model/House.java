package com.twoclams.hww.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class House implements Serializable {
    private static final long serialVersionUID = 5507003990317674340L;

    private String papayaUserId;
    private String type;
    private Integer level;
    private List<HouseFurniture> furnitures;
    private List<HouseFurniture> storage;
    private List<HouseTile> customTiles;
    private volatile boolean isEmpty;

    public House() {
        furnitures = new ArrayList<HouseFurniture>();
        storage = new ArrayList<HouseFurniture>();
        customTiles = new ArrayList<HouseTile>();
        type = "brick-yellow";
        level = 1;
        isEmpty = true;
    }

    public House(String type, Integer level, List<HouseFurniture> houseFurnitures,
            List<HouseFurniture> houseStorage, List<HouseTile> tiles) {
        this.type = type;
        this.level = level;
        this.furnitures = houseFurnitures;
        this.storage = houseStorage;
        this.customTiles = tiles;
    }

    public House(String type, Integer level, List<HouseFurniture> houseFurnitures, List<HouseFurniture> houseStorage,
            List<HouseTile> tiles, String papayaUserId) {
        this(type, level, houseFurnitures, houseStorage, tiles);
        this.papayaUserId = papayaUserId;
    }

    public Integer getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }

    public List<HouseFurniture> getFurnitures() {
        return furnitures;
    }

    public List<HouseFurniture> getStorage() {
        return storage;
    }

    public List<HouseTile> getCustomTiles() {
        return customTiles;
    }

    public boolean isEmpty() {
		return isEmpty;
	}

    public String getPapayaUserId() {
        return papayaUserId;
    }

    public void setPapayaUserId(String papayaUserId) {
        this.papayaUserId = papayaUserId;
    }

    @Override
    public String toString() {
        return "House [type=" + type + ", level=" + level + ", furnitures=" + furnitures
                + ", storage=" + storage + ", customTiles=" + customTiles + "]";
    }

}

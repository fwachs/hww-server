package com.twoclams.hww.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class House implements Serializable {
    private static final long serialVersionUID = 5507003990317674340L;

    private String papayaUserId;
    private String type;
    private Integer level;
    private List<HouseFurniture> furnitures;
    private List<HouseFurniture> storage;
    private List<HouseTile> customTiles;
    private Integer itemId;
    private volatile boolean isEmpty;

    public House() {
        furnitures = new ArrayList<HouseFurniture>();
        storage = new ArrayList<HouseFurniture>();
        customTiles = new ArrayList<HouseTile>();
        type = "brick-yellow";
        level = 1;
        isEmpty = true;
        itemId = 1;
    }

    public House(String type, Integer level, List<HouseFurniture> houseFurnitures,
            List<HouseFurniture> houseStorage, List<HouseTile> tiles, Integer itemId) {
        this.type = type;
        this.level = level;
        this.furnitures = houseFurnitures;
        this.storage = houseStorage;
        this.customTiles = tiles;
        this.itemId = itemId;
    }

    public House(String type, Integer level, List<HouseFurniture> houseFurnitures, List<HouseFurniture> houseStorage,
            List<HouseTile> tiles, String papayaUserId, Integer itemId) {
        this(type, level, houseFurnitures, houseStorage, tiles, itemId);
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

    public Integer getItemId() {
        return itemId;
    }

    @Override
    public String toString() {
        return "House [type=" + type + ", level=" + level + ", furnitures=" + furnitures
                + ", storage=" + storage + ", customTiles=" + customTiles + "]";
    }

    public void updateItemId() {
        List<Integer> itemIds = new ArrayList<Integer>();
        for (HouseFurniture furniture : this.furnitures) {
            itemIds.add(furniture.getItemId());
        }

        for (HouseFurniture storage : this.storage) {
            itemIds.add(storage.getItemId());
        }

        Collections.sort(itemIds);
        Integer itemId = 1;
        if (!itemIds.isEmpty()) {
            itemId = itemIds.get(itemIds.size()-1) + 1;
        }
        this.itemId = itemId;
    }

}

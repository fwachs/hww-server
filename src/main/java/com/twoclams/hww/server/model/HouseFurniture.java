package com.twoclams.hww.server.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class HouseFurniture implements Serializable {

    private static final long serialVersionUID = 977558237113538454L;
    private Integer itemId;
    private Integer top;
    private Integer furnitureType;
    private Integer isFlipped;
    private Integer left;

    public HouseFurniture(Integer itemId, Integer top, Integer furnitureType, Integer isFlipped,
            Integer left) {
        super();
        this.itemId = itemId;
        this.top = top;
        this.furnitureType = furnitureType;
        this.isFlipped = isFlipped;
        this.left = left;
    }

    public HouseFurniture(JSONObject jsonFurniture) throws JSONException {
        this((Integer) jsonFurniture.get("itemId"), (Integer) jsonFurniture.get("top"),
                (Integer) jsonFurniture.get("furnitureType"), (Integer) jsonFurniture
                        .get("isFlipped"), (Integer) jsonFurniture.get("left"));
    }

    public Integer getItemId() {
        return itemId;
    }

    public Integer getTop() {
        return top;
    }

    public Integer getFurnitureType() {
        return furnitureType;
    }

    public Integer getIsFlipped() {
        return isFlipped;
    }

    public Integer getLeft() {
        return left;
    }

    @Override
    public String toString() {
        return "HouseFurniture [itemId=" + itemId + ", top=" + top + ", furnitureType="
                + furnitureType + ", isFlipped=" + isFlipped + ", left=" + left + "]";
    }

}

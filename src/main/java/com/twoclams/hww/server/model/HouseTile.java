package com.twoclams.hww.server.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class HouseTile implements Serializable {

    private static final long serialVersionUID = -4856459479299737962L;
    private Integer id;
    private Integer row;
    private Integer col;
    private String type;
    private Integer height;
    private String furnitureId;

    public HouseTile(Integer id, Integer row, Integer col, String type, Integer height,
            String furnitureId) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.type = type;
        this.height = height;
        this.furnitureId = furnitureId;
    }

    public HouseTile(JSONObject jsonTile) throws JSONException {
        Integer id = Integer.valueOf(jsonTile.get("id").toString());
        Integer row = (Integer) jsonTile.get("row");
        Integer col = (Integer) jsonTile.get("col");
        String type = (String) jsonTile.get("type");
        Integer height = (Integer) jsonTile.get("height");
        String furnitureId = ((Integer) jsonTile.get("furnitureId")).toString();
        this.id = id;
        this.row = row;
        this.col = col;
        this.type = type;
        this.height = height;
        this.furnitureId = furnitureId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getCol() {
        return col;
    }

    public String getType() {
        return type;
    }

    public Integer getHeight() {
        return height;
    }

    public String getFurnitureId() {
        return furnitureId;
    }

    @Override
    public String toString() {
        return "HouseTile [id=" + id + ", row=" + row + ", col=" + col + ", type=" + type
                + ", height=" + height + ", furnitureId=" + furnitureId + "]";
    }

}

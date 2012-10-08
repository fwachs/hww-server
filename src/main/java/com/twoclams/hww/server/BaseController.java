package com.twoclams.hww.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.twoclams.hww.server.model.House;
import com.twoclams.hww.server.model.HouseFurniture;
import com.twoclams.hww.server.model.HouseTile;
import com.twoclams.hww.server.model.Housewife;
import com.twoclams.hww.server.model.Husband;

import flexjson.JSONSerializer;

public class BaseController {
    private static final Log logger = LogFactory.getLog(BaseController.class);

    protected JSONSerializer getDefaultSerializer() {
        JSONSerializer serializer = new JSONSerializer();
        serializer.exclude("*.class");
        return serializer;
    }

    protected Integer[] getSkinTone(HttpServletRequest request) {
        String skinToneString = request.getParameter("skinTone");
        skinToneString = skinToneString.replace("[", "").replace("]", "");
        String[] skinToneStr = skinToneString.split(",");
        Integer[] skinTone = new Integer[3];
        for (int i = 0; i < skinToneStr.length; i++) {
            skinTone[i] = Integer.valueOf(skinToneStr[i]);
        }
        return skinTone;
    }

    protected Husband buildHusband(JSONObject husbandJson) throws JSONException {
        String id = new String(husbandJson.get("papayaUserId").toString());
        Integer occupation = (Integer) husbandJson.get("occupation");
        Object name = husbandJson.get("name");
        Integer careerLevel = (Integer) husbandJson.get("careerLevel");
        Integer stressLevel = (Integer) husbandJson.get("stressMeterValue");
        Integer loveTank = (Integer) husbandJson.get("loveTankValue");
        Integer totalVisits = (Integer) husbandJson.get("citiesVisited");
        return new Husband(id, occupation, String.valueOf(name), careerLevel, stressLevel, loveTank, totalVisits);
    }

    protected Housewife buildWife(JSONObject wifeJson) throws JSONException {
        String id = new String(wifeJson.get("id").toString());
        Object wifeName = wifeJson.get("name");
        if (wifeName == null) {
            wifeName = "MysteryWife";
        }
        Integer socialStatusPoints = (Integer) wifeJson.get("socialStatusPoints");
        Housewife.Type type = Housewife.Type.valueOf((String) wifeJson.get("type"));
        JSONArray jsonSkinTone = (JSONArray) wifeJson.get("skinTone");
        Integer[] skinTone = new Integer[3];
        for (int i = 0; i < jsonSkinTone.length(); i++) {
            skinTone[i] = (Integer) jsonSkinTone.get(i);
        }
        Integer hairColor = (Integer) wifeJson.get("hairColor");
        Integer hairStyle = (Integer) wifeJson.get("hairStyle");
        return new Housewife(id, wifeName.toString(), socialStatusPoints, type, skinTone, hairColor, hairStyle);
    }

    protected House buildHouse(String type, String level, String furnituresJsonStr, String storageJsonStr,
            String customTilesJsonStr) {
        String[] customTiles = customTilesJsonStr.replace("[", "").replace("]", "").split("},");
        List<HouseTile> tiles = new ArrayList<HouseTile>();
        for (String customTile : customTiles) {
            if (StringUtils.isEmpty(customTile)) {
                continue;
            }
            try {
                JSONObject jsonTile = new JSONObject(customTile.concat("}"));
                HouseTile tile = new HouseTile(jsonTile);
                tiles.add(tile);
            } catch (JSONException e) {
                logger.error("CustomTile - " + customTile, e);
            }
        }
        String[] furnitures = furnituresJsonStr.replace("[", "").replace("]", "").split("},");
        List<HouseFurniture> houseFurnitures = new ArrayList<HouseFurniture>();
        for (String furniture : furnitures) {
            if (StringUtils.isEmpty(furniture)) {
                continue;
            }
            try {
                JSONObject jsonFurniture = new JSONObject(furniture.concat("}"));
                HouseFurniture houseFurniture = new HouseFurniture(jsonFurniture);
                houseFurnitures.add(houseFurniture);
            } catch (JSONException e) {
                logger.error("HouseFurniture - " + furniture, e);
            }
        }
        String[] storage = storageJsonStr.replace("[", "").replace("]", "").split("},");
        List<HouseFurniture> houseStorage = new ArrayList<HouseFurniture>();
        for (String storageItem : storage) {
            if (StringUtils.isEmpty(storageItem)) {
                continue;
            }
            try {
                JSONObject jsonStorage = new JSONObject(storageItem.concat("}"));
                HouseFurniture houseFurniture = new HouseFurniture(jsonStorage);
                houseStorage.add(houseFurniture);
            } catch (JSONException e) {
                logger.error("HouseStorage - " + storageItem, e);
            }
        }
        return new House(type, Integer.valueOf(level), houseFurnitures, houseStorage, tiles);
    }

    protected House buildHouse(JSONObject jsonObject) throws JSONException {
        Integer level = jsonObject.getInt("level");
        String papayaUserId = jsonObject.getString("papayaUserId");
        JSONArray furnitures = jsonObject.getJSONArray("furnitures");
        List<HouseFurniture> houseFurnitures = new ArrayList<HouseFurniture>();
        for (int i = 0; i < furnitures.length(); i++) {
            JSONObject row = furnitures.getJSONObject(i);
            try {
                HouseFurniture furniture = new HouseFurniture(row);
                houseFurnitures.add(furniture);
            } catch (JSONException e) {
                logger.error("Error adding furniture. ", e);
            }
        }
        JSONArray storage = jsonObject.getJSONArray("storage");
        List<HouseFurniture> houseStorage = new ArrayList<HouseFurniture>();
        for (int i = 0; i < storage.length(); i++) {
            JSONObject row = storage.getJSONObject(i);
            try {
                HouseFurniture furniture = new HouseFurniture(row);
                houseStorage.add(furniture);
            } catch (JSONException e) {
                logger.error("Error adding storage. ", e);
            }
        }
        List<HouseTile> tiles = new ArrayList<HouseTile>();
        JSONArray customTiles = jsonObject.getJSONArray("customTiles");
        for (int i = 0; i < customTiles.length(); i++) {
            JSONObject row = customTiles.getJSONObject(i);
            try {
                HouseTile customTile = new HouseTile(row);
                tiles.add(customTile);
            } catch (JSONException e) {
                logger.error("Error adding custom tiles. ", e);
            }
        }
        String type = jsonObject.getString("type");
        return new House(type, level, houseFurnitures, houseStorage, tiles, papayaUserId);
    }
}

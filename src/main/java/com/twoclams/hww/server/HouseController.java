package com.twoclams.hww.server;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.twoclams.hww.server.model.House;
import com.twoclams.hww.server.model.SimpleResponse;
import com.twoclams.hww.server.service.UsersService;

@Controller
public class HouseController extends BaseController {
    private static final Log logger = LogFactory.getLog(HouseController.class);

    @Autowired
    private UsersService userService;

    @RequestMapping(value = "/syncHouse")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String syncHouse(
            @RequestParam(value = "type") String type,
            @RequestParam(value = "level") String level,
            @RequestParam(value = "furnitures") String furnituresJsonStr,
            @RequestParam(value = "storage") String storageJsonStr,
            @RequestParam(value = "customTiles") String customTilesJsonStr,
            @RequestParam(value = "papayaUserId") String papayaUserId, 
            @RequestParam(value = "socialId") String socialId,
            HttpServletRequest request)
            throws IOException {
        String itemIdStr = request.getParameter("itemId");
        Integer itemId = new Integer(1000);
        if (StringUtils.isNotBlank(itemIdStr) && StringUtils.isNumeric(itemIdStr)) {
            itemId = Integer.valueOf(itemIdStr);
        }
        logger.info("House Synchronization for" + " papayaUserId: " + papayaUserId + " - Furniture: " + furnituresJsonStr + ", Storage: "
                + storageJsonStr + " CustomTiles: " + customTilesJsonStr);
        House house = this.buildHouse(type, level, itemId, furnituresJsonStr, storageJsonStr,
                customTilesJsonStr);

        SimpleResponse response = userService.synchronizeHouse(papayaUserId, socialId, house);
        return getDefaultSerializer().deepSerialize(response);
    }

    @RequestMapping(value = "/getPlayerHouse")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getPlayerHouse(@RequestParam(value = "papayaUserId") String papayaUserId,
            HttpServletRequest request) throws IOException, JSONException {
        House house = userService.getHouse(papayaUserId);
        return getDefaultSerializer().deepSerialize(house);
    }

}

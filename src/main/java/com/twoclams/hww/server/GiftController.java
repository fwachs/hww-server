package com.twoclams.hww.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.twoclams.hww.server.model.Gift;
import com.twoclams.hww.server.model.SimpleResponse;
import com.twoclams.hww.server.service.GiftService;

@Controller
public class GiftController extends BaseController {

    @Autowired
    private GiftService giftService;

    @RequestMapping(value = "/sendGift")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String sendGift(@ModelAttribute Gift gift,
            @RequestParam(value = "papayaUserId") String papayaUserId, HttpServletRequest request)
            throws IOException, JSONException {
        giftService.sendGift(papayaUserId, gift);
        return this.getDefaultSerializer().deepSerialize(new SimpleResponse());
    }

    @RequestMapping(value = "/getGifts")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String fetchGifts(@RequestParam(value = "papayaUserId") String papayaUserId,
            HttpServletRequest request) throws IOException, JSONException {
        Map<String, Object> response = new HashMap<String, Object>();
        List<Gift> userGifts = giftService.retrieveAllGifts(papayaUserId);
        response.put("gifts", userGifts);
        return this.getDefaultSerializer().deepSerialize(response);
    }
}

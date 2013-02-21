package com.twoclams.hww.server;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.twoclams.hww.server.model.GossipWallMessage;
import com.twoclams.hww.server.model.GossipWallResponse;
import com.twoclams.hww.server.model.Housewife;
import com.twoclams.hww.server.service.GossipWallService;
import com.twoclams.hww.server.service.UsersService;

@Controller
public class GossipWallController extends BaseController {

    private static final Log logger = LogFactory.getLog(GossipWallController.class);

    private static List<String> bannedPapayaUserIds = new ArrayList<String>();

    @Autowired
    private UsersService userService;

    @Autowired
    private GossipWallService gossipWallService;

    static {
        bannedPapayaUserIds.add("111038210");
    }

    @RequestMapping(value = "/postGossip", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String postGossip(HttpServletRequest request, @ModelAttribute GossipWallMessage message) throws IOException,
            JSONException {
        if (!StringUtils.isEmpty(message.getMessage()) && isBanned(message.getPapayaUserId())) {
            // gossipWallService.postGossipWall(message);
        }
        return getLatestGossipMessages(null, request);
    }

    private boolean isBanned(String papayaUserId) {
        return !bannedPapayaUserIds.contains(papayaUserId);
    }

    @RequestMapping(value = "/getLatestGossipMessages")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getLatestGossipMessages(@ModelAttribute Housewife housewife, HttpServletRequest request)
            throws IOException, JSONException {
        LocalDate localDate = new LocalDate(new Date());
        LocalDate nextSunday = this.calcNextSunday(localDate);
        DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        GossipWallResponse response = gossipWallService.findGossipWallResponse(housewife.getId());
        response.setTournamentEndDate(dateFormatter.format(nextSunday.toDate()));
        return getDefaultSerializer().include("messages").include("skinTone").deepSerialize(response);
    }

    @RequestMapping(value = "/tournament")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String tournament(HttpServletRequest request) throws IOException, JSONException {
        LocalDate localDate = new LocalDate(new Date());
        LocalDate nextSunday = this.calcNextSunday(localDate);
        List<Housewife> top25 = userService.findTop25();
        DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("players", top25);
        params.put("tournamentEndDate", dateFormatter.format(nextSunday.toDate()));
        return getDefaultSerializer().exclude("clothingItems", "mysteryItems", "skinTone").deepSerialize(params);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
    }
}

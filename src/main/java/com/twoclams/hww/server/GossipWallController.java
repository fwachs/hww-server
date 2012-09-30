package com.twoclams.hww.server;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    @Autowired
    private UsersService userService;

    @Autowired
    private GossipWallService gossipWallService;

    @RequestMapping(value = "/postGossip", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String postGossip(HttpServletRequest request, @ModelAttribute GossipWallMessage message)
            throws IOException, JSONException {
        if (!StringUtils.isEmpty(message.getMessage())) {
            gossipWallService.postGossipWall(message);
        }
        return getLatestGossipMessages(null, request);
    }

    @RequestMapping(value = "/getLatestGossipMessages")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getLatestGossipMessages(@ModelAttribute Housewife housewife,
            HttpServletRequest request) throws IOException, JSONException {
        if (housewife != null && StringUtils.isNotEmpty(housewife.getId())) {
            logger.info("HousewifeBuilt: " + housewife.toString());
            userService.synchronizeHousewife(housewife);
        }
        GossipWallResponse response = gossipWallService.findGossipWallResponse();
        return getDefaultSerializer().include("messages").include("skinTone")
                .deepSerialize(response);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
    }
}

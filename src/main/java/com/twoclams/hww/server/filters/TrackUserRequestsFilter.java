/**
 * 
 */
package com.twoclams.hww.server.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter used to track what users are doing in the system. Logs the request
 * URI, the user (if available) and the IP. If a session is already available it
 * is printed as well. <br/>
 * It is important that this filter is executed AFTER the security filter.
 * 
 * @author Federico Wachs (federico.wachs@2clams.com)
 * 
 */
public class TrackUserRequestsFilter extends OncePerRequestFilter {

    private static final Logger log = Logger.getLogger(TrackUserRequestsFilter.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String user = getScreenname(request);
        String ip = getUserIp(request);
        String url = getUrl(request);

        log.info(url + " - " + user + " (" + ip + ")");
        long start = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } finally {
            long total = System.currentTimeMillis() - start;
            log.info(url + " - " + user + " (" + ip + ") - Took: " + total + "ms");
        }
    }

    private String getUrl(HttpServletRequest request) {
        return request.getRequestURI();
    }

    /**
     * Returns the current user's IP.
     */
    private String getUserIp(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    /**
     * Returns the screenname of the currently logged in user or Anonymous if
     * not yet logged in.
     * 
     * @return
     */
    private String getScreenname(HttpServletRequest request) {
        String papayaUserId = request.getParameter("papayaUserId");
        if (StringUtils.isEmpty(papayaUserId)) {
            return "anonymous";
        }
        return papayaUserId;
    }

}

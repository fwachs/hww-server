package com.twoclams.hww.server.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.twoclams.hww.server.dao.SocialStatusDao;
import com.twoclams.hww.server.model.Housewife;

public class SocialStatusDaoJdbc extends NamedParameterJdbcDaoSupport implements SocialStatusDao {
    private static final String INSERT_POINTS = "insert into wife_status_points (papayaUserId, points) VALUES (:papayaUserId, :points)";

    private static final String UPDATE_POINTS = "update wife_status_points set points=:points where papayaUserId=:papayaUserId";

    private static final String GET_HIGHSCORE = "select papayaUserId from wife_status_points order by points desc limit 1";
    @Override
    public void updateSocialStatusPoints(Housewife housewife) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("papayaUserId", Long.valueOf(housewife.getId()));
        params.addValue("points", housewife.getSocialStatusPoints());
        int rows = getNamedParameterJdbcTemplate().update(UPDATE_POINTS, params);
        if (rows == 0) {
            getNamedParameterJdbcTemplate().update(INSERT_POINTS, params);
        }
    }

    @Override
    public String getHighestScore() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return this.getNamedParameterJdbcTemplate().queryForObject(GET_HIGHSCORE, params, String.class);
    }

}

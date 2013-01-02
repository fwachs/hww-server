package com.twoclams.hww.server.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.twoclams.hww.server.dao.SocialStatusDao;
import com.twoclams.hww.server.model.Housewife;

public class SocialStatusDaoJdbc extends NamedParameterJdbcDaoSupport implements SocialStatusDao {
    private static final String INSERT_POINTS = "insert into wife_status_points (papayaUserId, points) VALUES (:papayaUserId, :points)";

    private static final String UPDATE_POINTS = "update wife_status_points set points=:points where papayaUserId=:papayaUserId";

    private static final String GET_HIGHSCORE = 
            "select papayaUserId, (points - last_week_score) as points from wife_status_points order by points desc limit 1"; 
//          "select papayaUserId from wife_status_points order by points desc limit 1";

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
    public Housewife getHighestScore() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        Housewife obj = this.getNamedParameterJdbcTemplate().query(GET_HIGHSCORE, params,
                new ObjectResultSetExtractor());
        return obj;
    }

    private class ObjectResultSetExtractor implements ResultSetExtractor<Housewife> {

        @Override
        public Housewife extractData(ResultSet rs) throws SQLException, DataAccessException {
            Housewife wife = new Housewife();
            if (rs.next()) {
                wife.setSocialStatusPoints(rs.getInt("points"));
                wife.setId(rs.getString("papayaUserId"));    
            }
            return wife;
        }

    }
}

package com.twoclams.hww.server.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.twoclams.hww.server.dao.SocialStatusDao;
import com.twoclams.hww.server.model.Housewife;
import com.twoclams.hww.server.service.UserReward;

public class SocialStatusDaoJdbc extends NamedParameterJdbcDaoSupport implements SocialStatusDao {
    private static final String INSERT_REWARD = 
             "INSERT INTO wife_rewards (papayaUserId, amount, currency, reason, used) " +
    		" VALUES (:papayaUserId, :amount, :currency, :reason, :used)";

    private static final String INSERT_POINTS = "insert into wife_status_points (papayaUserId, points) VALUES (:papayaUserId, :points)";

    private static final String UPDATE_POINTS = "update wife_status_points set points=:points where papayaUserId=:papayaUserId";

    private static final String GET_25_HIGHSCORE = 
            "select papayaUserId, (points - last_week_score) as points from wife_status_points order by points desc limit 25";

    private static final String GET_HIGHSCORE = 
            "select papayaUserId, (points - last_week_score) as points from wife_status_points order by points desc limit 3"; 

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
    public List<Housewife> getHighestScore() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        List<Housewife> obj = this.getNamedParameterJdbcTemplate().query(GET_HIGHSCORE, params,
                new ObjectResultSetExtractor());
        return obj;
    }

    
    private class ObjectResultSetExtractor implements RowMapper<Housewife> {

        @Override
        public Housewife mapRow(ResultSet rs, int rowNum) throws SQLException {
            Housewife wife = new Housewife();
            wife.setSocialStatusPoints(rs.getInt("points"));
            wife.setId(rs.getString("papayaUserId"));    
            return wife;
        }

    }

    @Override
    public List<Housewife> getTop25() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        List<Housewife> obj = this.getNamedParameterJdbcTemplate().query(GET_25_HIGHSCORE, params,
                new ObjectResultSetExtractor());
        return obj;
    }

    @Override
    public void reward(String papayaUserId, int amount, String currency) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("papayaUserId", Long.valueOf(papayaUserId));
        params.addValue("amount", amount);
        params.addValue("currency", currency);
        params.addValue("reason", "You won because you succeded in the tournament!");
        params.addValue("used", Boolean.FALSE);
        getNamedParameterJdbcTemplate().update(INSERT_REWARD, params);
    }

    @Override
    public List<UserReward> getPendingRewards(String papayaUserId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("papayaUserId", Long.valueOf(papayaUserId));
        List<UserReward> obj = this.getNamedParameterJdbcTemplate().query(
                "select * from wife_rewards where papayaUserId=:papayaUserId and used = 0", params, new RewardsRowMapper());
        return obj;
    }

    private class RewardsRowMapper implements RowMapper<UserReward> {

        @Override
        public UserReward mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserReward reward = new UserReward();
            reward.setCurrency(rs.getString("currency"));
            reward.setAmount(rs.getInt("amount"));
            reward.setId(rs.getLong("id"));
            reward.setReason(rs.getString("reason"));
            return reward;
        }

    }

    @Override
    public void rewardClaimed(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        getNamedParameterJdbcTemplate().update("update wife_rewards set used = 1 where id=:id", params);
    }
}

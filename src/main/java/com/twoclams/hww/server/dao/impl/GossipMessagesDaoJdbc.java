package com.twoclams.hww.server.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.twoclams.hww.server.dao.GossipMessageDao;
import com.twoclams.hww.server.model.GossipWallMessage;

public class GossipMessagesDaoJdbc extends NamedParameterJdbcDaoSupport implements GossipMessageDao {
    private static final String SAVE_GOSSIP_MESSAGE =
              " INSERT INTO wall_messages (papayaUserId, message, housewife_name, house_level, created_on)"
            + " VALUES (:papayaUserId, :message, :housewife_name, :house_level, :created_on)";

    private static final String SELECT_LATEST_GOSSIP_MESSAGES = "select * from wall_messages order by created_on desc limit 15";

    @Override
    public List<GossipWallMessage> findLatestMessages() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return getNamedParameterJdbcTemplate().query(SELECT_LATEST_GOSSIP_MESSAGES, params,
                new WallMessageRowMapper());
    }

    private class WallMessageRowMapper implements RowMapper<GossipWallMessage> {

        @Override
        public GossipWallMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
            GossipWallMessage message = new GossipWallMessage(rs.getString("message"),
                    rs.getString("housewife_name"), rs.getInt("house_level"),
                    rs.getTimestamp("created_on"));
            return message;
        }

    }

    @Override
    public void add(GossipWallMessage newMessage) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("papayaUserId", newMessage.getPapayaUserId());
        params.addValue("message", newMessage.getMessage());
        params.addValue("housewife_name", newMessage.getHouseWifeName());
        params.addValue("house_level", newMessage.getHouseLevel());
        params.addValue("created_on", new Date());

        this.getNamedParameterJdbcTemplate().update(SAVE_GOSSIP_MESSAGE, params);
    }
}

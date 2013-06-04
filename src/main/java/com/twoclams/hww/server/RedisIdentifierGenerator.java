package com.twoclams.hww.server;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisIdentifierGenerator implements IdentifierGenerator {
  
  private String sequenceKey;
  private JedisPool pool;
  
  public RedisIdentifierGenerator(String sequenceKey, JedisPool pool) {
    this.sequenceKey = sequenceKey;
    this.pool = pool;
  }
  
  @Override
  public String getName() {
    return RedisIdentifierGenerator.class.getName();
  }
  
  @Override
    public Long newId() {
      Jedis jedis = this.pool.getResource();
      Long newId = 0L;
      try {
          newId = jedis.incr(this.sequenceKey);
      } finally {
          this.pool.returnResource(jedis);
      }
      return newId;
    }
  
  public void shutdown() {
    this.pool.destroy();
  }
  
}
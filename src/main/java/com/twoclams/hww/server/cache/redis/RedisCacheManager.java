package com.twoclams.hww.server.cache.redis;

import static com.twoclams.hww.server.cache.redis.Bytes.decode;
import static com.twoclams.hww.server.cache.redis.Bytes.encode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import com.twoclams.hww.server.cache.CacheManager;

/**
 * An implementation of the CacheManager interface based on redis.
 */
public class RedisCacheManager implements CacheManager {

    private static final Log logger = LogFactory.getLog(RedisCacheManager.class);

    /**
     * Default timeout value {@value #DEFAULT_TIMEOUT} in seconds
     */
    private static final int DEFAULT_TIMEOUT = 60 * 5;

    protected JedisPool pool = null;

    protected boolean enabled = true;

    protected int defaultTimeout = DEFAULT_TIMEOUT;

    @Override
    public boolean store(String key, Serializable o) {
        return store(key, o, this.defaultTimeout);
    }

    @Override
    public boolean store(String key, Serializable o, int timeout) {
        if (!this.enabled) {
            return false;
        }
        boolean success = true;
        Jedis client = null;
        try {
            client = this.pool.getResource();
            if (logger.isDebugEnabled()) {
                logger.debug("Storing object [" + o + "] into redis with key["
                        + key + "], timeout[" + timeout + "]");
            }
            if (timeout > 0) {
                client.setex(encode(key), timeout, encode(o));    
            } else {
                client.set(encode(key), encode(o));
            }
        } catch (JedisConnectionException e) {
            success = false;
            logger.info("Error storing object with key[" + key + "] into redis. Attempted to" +
                    " retrieve a resource and failed.", e);
        } finally {
            if (client != null) {
                this.pool.returnResource(client);
            }
        }
        return success;
    }

    @Override
    public boolean delete(String key) {
        if(!this.enabled){
            return false;
        }
        boolean success = true;
        Jedis client = null;
        try {
            client = this.pool.getResource();
            if(logger.isDebugEnabled()){
                logger.debug("Deleting object from redis with key[" + key + "]");
            }
            Long qty = client.del(key);
            success = qty != 0;
            if (!success) {
                logger.error("Error deleting object with key[" + key + "] from redis");
            }
        } catch(JedisConnectionException e) {
            success = false;
            logger.error("Error deleting object with key[" + key + "] from redis. Attempted to" +
                    " retrieve a resource and failed.", e);
        } finally {
            if (client != null) {
                this.pool.returnResource(client);
            }
        }
        return success;
    }

    @Override
    public Serializable get(String key) {
        return this.get(Serializable.class, key);
    }

    @Override
    public <T extends Serializable> T get(Class<T> clazz, String key) {
        if(!this.enabled){
            return null;
        }
        T value = null;
        Jedis client = null;
        try {
            client = this.pool.getResource();
            if(logger.isDebugEnabled()){
                logger.debug("Getting object from redis with key[" + key + "]");
            }
            byte[]reply = client.get(encode(key));
            if (reply != null) {
                value = clazz.cast(decode(reply));
            }
        } catch(JedisConnectionException e) {
            logger.error("Error getting object with key[" + key + "] from redis. Attempted to" +
                    " retrieve a resource and failed.", e);
        } catch(Exception e) {
            logger.error("Error getting object with key[" + key + "] from redis", e);
        } finally {
            if (client != null) {
                this.pool.returnResource(client);
            }
        }
        return value;
    }

    @Override
    public Map<String, Serializable> get(String... keys) {
        return this.get(Serializable.class, keys);
    }

    @Override
    public <T extends Serializable> Map<String, T> get(Class<T> clazz, String... keys) {
        if(!this.enabled ){
            return null;
        }
        Map<String, T> values = new HashMap<String, T>();
        Jedis client = null;
        try {
            client = this.pool.getResource();
            if(logger.isDebugEnabled()){
                logger.debug("Getting bulk objects from memcached with keys[" + keys + "]");
            }
            List<byte[]> reply = client.mget(encode(keys));
            int i = 0;
            for (byte[] value : reply) {
                values.put(keys[i], clazz.cast(decode(value)));
                i++;
            }
        } catch(JedisConnectionException e) {
            logger.error("Error getting object with keys[" + keys + "] from redis. Attempted to" +
                    " retrieve a resource and failed.", e);
        } catch(Exception e) {
            logger.error("Error getting bulk objects with keys[" + keys + "] from redis");
        } finally {
            if (client != null) {
                this.pool.returnResource(client);
            }
        }
        return values;
    }

    @Override
    public boolean replace(String key, Serializable o) {
        if(!enabled ){
            return false;
        }
        boolean success = true;
        boolean exists = false;
        Jedis client = null;
        try {
            client = this.pool.getResource();
            if(logger.isDebugEnabled()){
                logger.debug("Replacing object [" + o + "] from redis with key[" + key + "]");
            }
            exists = client.exists(encode(key));
        } catch(JedisConnectionException e) {
            success = false;
            logger.error("Error replacing object [" + o + "] with key[" + key + "] into redis. Attempted to" +
                    " retrieve a resource and failed.", e);
        }
        if (exists) {
            success = store(key, o);
        }
        return success;
    }

    @Override
    public long increment(String key, int amount) {
        return increment(key, amount, this.defaultTimeout);
    }

    @Override
    public long increment(String key, int amount, int timeout) {
        long value = -1;
        Jedis client = null;
        try {
            client = this.pool.getResource();
            byte[] encodedKey = encode(key);
            value = client.incrBy(encodedKey, amount);
            client.expire(encodedKey, timeout);
        } catch(JedisConnectionException e) {
            logger.error("Error incrementing key[" + key + "] value in redis. Attempted to" +
                    " retrieve a resource and failed.", e);
        } finally {
            if (client != null) {
                this.pool.returnResource(client);
            }
        }
        return value;
    }

    @Override
    public long decrement(String key, int amount) {
        long value = -1;
        Jedis client = null;
        try {
            client = this.pool.getResource();
            byte[] encodedKey = encode(key);
            value = client.decrBy(encodedKey, amount);
        } catch(JedisConnectionException e) {
            logger.error("Error decrementing key[" + key + "] value in redis. Attempted to" +
                    " retrieve a resource and failed.", e);
        } finally {
            if (client != null) {
                this.pool.returnResource(client);
            }
        }
        return value;
    }

    @Override
    public void shutdown() {
        this.pool.destroy();
    }

    /**
     * Specifies the redis pool to use.
     * 
     * @param pool
     */
    public void setPool(JedisPool pool) {
        logger.info("Initializing Redis pool with [" + pool + "]");
        this.pool = pool;
    }

    /**
     * Defines if redis is enabled or not.
     * 
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
    
}

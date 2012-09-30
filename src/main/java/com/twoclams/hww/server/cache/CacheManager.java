/**
 * 
 */
package com.twoclams.hww.server.cache;

import java.io.Serializable;
import java.util.Map;

/**
 * An abstraction layer for getting objects from a central
 * cache.
 *
 */
public interface CacheManager {

	/**
	 * Stores the given object in the cache, using the default
	 * timeout
	 * @param this object's key in the cache
	 * @param o the object to store
	 */
	public boolean store(String key, Serializable o);
	
	/**
	 * Stores the given object in the cache, using the specified
	 * timeout in seconds
	 * @param this object's key in the cache
	 * @param o the object to store
	 * @param timeout how long this object will live in the cache
	 */
	public boolean store(String key, Serializable o, int timeout);

	/**
	 * Deletes the object with the specified key
	 * from the cache
	 * @param key
	 */
	public boolean delete(String key);
	
	/**
	 * Returns the object stored in the cache with the
	 * given key, or null if not found
	 * @param key
	 * @return the stored object or null if not found
	 */
	public Serializable get(String key);

	/**
	 * Returns the object stored in the cache with the
	 * given key, or null if not found
	 * @param key
	 * @return the stored object or null if not found
	 */
	public <T extends Serializable> T get(Class<T> clazz, String key);
	
	/**
	 * Returns all the objects with the given keys
	 * from the cache. If any of these keys is not found
	 * then less objects than keys.length may be returned.
	 * If no key is found then null is returned.
	 * @param keys
	 * @return
	 */
	public Map<String, Serializable> get(String... keys);

	/**
	 * Returns all the objects with the given keys
	 * from the cache. If any of these keys is not found
	 * then less objects than keys.length may be returned.
	 * If no key is found then null is returned.
	 * @param keys
	 * @return
	 */
	public <T extends Serializable> Map<String, T> get(Class<T> clazz, String... keys);
	
	/**
	 * Replaces the given object from the cache. 
	 * If the object is not already there, then it is
	 * added.
	 * @param key the object key
	 * @param o what we want to store
	 */
	public boolean replace(String key, Serializable o);
	
	/**
	 * Increments the 'key' counter by 'amount', in a
	 * non-atomic way (although some caches may make
	 * it atomic, do not assume it is) 
	 */
	public long increment(String key, int amount);
	
	/**
	 * Increments the 'key' counter by 'amount', in a
	 * non-atomic way (although some caches may make
	 * it atomic, do not assume it is).
	 * Key is persisted for the given timout since creation.
	 */
	public long increment(String key, int amount, int timeout);
	
	/**
	 * Decrements the 'key' counter by 'amount', in a
	 * non-atomic way.
	 * @see #increment(String, int)
	 * @param key
	 * @return
	 */
	public long decrement(String key, int amount); 

	public void shutdown();
}

/**
 * 
 */
package com.twoclams.hww.server.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * A memcached client that round-robins requests to the internally
 * configured cache managers. Do NOT mix different caches (i.e. with
 * different objects) as composits of this class. The idea is to generate
 * multiple clients to the same cache.
 * @author Federico Wachs (federico.wachs@2clams.com)
 *
 */
public class RoundRobinCompositeCacheManager implements CacheManager {
	
	protected CacheManager[]cacheList = null;
	
	/**
	 * Contains a "pointer" to the current cache, to be used in the next
	 * call to a client.
	 */
	protected int currentCache = 0;
	
	protected int maxSize;

	@Override
	public long decrement(String key, int amount) {
		return getCache().decrement(key, amount);
	}

	@Override
	public boolean delete(String key) {
		return getCache().delete(key);
	}

	/* (non-Javadoc)
	 * @see com.twoclams.commons.service.cache.CacheManager#get(java.lang.String)
	 */
	@Override
	public Serializable get(String key) {
		return getCache().get(key);
	}

	@Override
	public <T extends Serializable> T get(Class<T> clazz, String key) {
		return getCache().get(clazz, key);
	}

	@Override
	public Map<String, Serializable> get(String... keys) {
		return getCache().get(keys);
	}

	@Override
	public <T extends Serializable> Map<String, T> get(Class<T> clazz, String... keys) {
		return getCache().get(clazz, keys);
	}
	
	@Override
	public long increment(String key, int amount) {
		return getCache().increment(key, amount);
	}

	@Override
	public long increment(String key, int amount, int timeout) {
		return getCache().increment(key, amount, timeout);
	}

	
	@Override
	public boolean replace(String key, Serializable o) {
		return getCache().replace(key, o);
	}

	@Override
	public boolean store(String key, Serializable o) {
		return getCache().store(key, o);
	}

	@Override
	public boolean store(String key, Serializable o, int timeout) {
		return getCache().store(key, o, timeout);
	}

	/**
	 * Returns the CacheManager to be used for a request.
	 * @return
	 */
	protected CacheManager getCache(){
		currentCache++;
		if( currentCache < 0 ){
			currentCache = 0;
		}
		//we do the % here to avoid synchronizing the code.
		// if not, we might be in the case where currentCache
		// is at its biggest allowed number (maxSize - 1) and
		// another thread increments it just before we perform
		// the get() operation
		return cacheList[currentCache % maxSize];
	}
	
	public void setCacheList(List<CacheManager> cacheListList) {
		this.cacheList = new CacheManager[cacheListList.size()];
		this.maxSize = cacheList.length;
		cacheListList.toArray(this.cacheList);
	}
	
	public void shutdown() {
		for (CacheManager c : cacheList) {
			c.shutdown();
		}
	}
}

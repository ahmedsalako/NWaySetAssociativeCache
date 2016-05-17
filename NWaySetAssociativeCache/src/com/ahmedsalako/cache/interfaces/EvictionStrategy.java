package com.ahmedsalako.cache.interfaces;

import com.ahmedsalako.cache.enums.CacheChanges;
import com.ahmedsalako.cache.extensions.Key;

/**
 * 
 * @author Ahmed
 * 
 * interface implemented by all eviction strategy internally/externally
 * @param <TTag>
 */
public interface EvictionStrategy<TTag> 
{
	/**
	 * should be used by the eviction strategy for initialisation purpose
	 * @param cacheSize the size of the targeted cache
	 */
	void onInitialisation(int cacheSize);
	
	/**
	 * Get the key that is evictable according to the eviction selection
	 * @return Key{TTag} the key object of an evictable element
	 */
	Key<TTag> getEvictableKey();
	
	/**
	 * accept cache change notification
	 * @param key
	 * @param changes
	 */
	void OnCacheChange(Key<TTag> key, CacheChanges changes);
}

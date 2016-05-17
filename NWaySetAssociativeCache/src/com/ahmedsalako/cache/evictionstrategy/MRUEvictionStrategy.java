package com.ahmedsalako.cache.evictionstrategy;

import com.ahmedsalako.cache.enums.CacheChanges;
import com.ahmedsalako.cache.extensions.Key;
import com.ahmedsalako.cache.interfaces.EvictionStrategy;

/**
 * The strategy that represents how the Most Recently Used SetLine item is evicted from the cache
 * 
 * @author Ahmed
 *
 * @param <TTag>
 */
public class MRUEvictionStrategy<TTag> implements EvictionStrategy<TTag>
{
	/**
	 * the most recently added key
	 */
	private Key<TTag> mostRecentKey = null;
	
	/**
	 * Cache Size
	 */
	private int cacheSize;
	
	@Override
	public void onInitialisation(int cacheSize) {
		this.cacheSize = cacheSize;
	}

	/**
	 * Get the key that is evictable according to the eviction selection
	 */
	@Override
	public Key<TTag> getEvictableKey() {

		return mostRecentKey;
	}

	/**
	 * accept cache change notification
	 */
	@Override
	public void OnCacheChange(Key<TTag> key, CacheChanges changes) {
        if(changes == CacheChanges.Access || 
                changes == CacheChanges.Add)
            {
                mostRecentKey = key;
            }
            else if(changes == CacheChanges.Remove)
            {
                if (key.equals(mostRecentKey))
                {
                    mostRecentKey = null;
                }
            }		
	}
}

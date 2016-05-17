package com.ahmedsalako.cache.evictionstrategy;

import java.util.LinkedList;

import com.ahmedsalako.cache.enums.CacheChanges;
import com.ahmedsalako.cache.extensions.Key;
import com.ahmedsalako.cache.interfaces.EvictionStrategy;

/**
 * The strategy that represents how the Least Recently Used SetLine item is evicted from the cache.
 * The eviction strategy itself is based on listening to changes that are tracked via the
 * @author Ahmed
 *
 * @param <TTag>
 */
public class LRUEvictionStrategy<TTag> implements EvictionStrategy<TTag>
{
	/**
	 * List of tracked keys
	 */
	private LinkedList<Key<TTag>> keys = new LinkedList<Key<TTag>>();

	/**
	 * The cache size
	 */
    private int cacheSize;	
	
    /**
     * should be used by the eviction strategy for initialisation purpose
     */
	@Override
	public void onInitialisation(int cacheSize) {
		this.cacheSize = cacheSize;
	}

	/**
	 * Get the key that is evictable according to the eviction selection
	 */
	@Override
	public Key<TTag> getEvictableKey() {

		return keys.getLast();
	}

	/**
	 * A method that receives change tracking notification from the 
	 */
	@Override
	public void onCacheChange(Key<TTag> key, CacheChanges changes) {
		if(changes == CacheChanges.Add || 
				changes == CacheChanges.Access){
			keys.remove(key); //remove existing entry to ensure no double entry
			keys.addFirst(key);
		}
		else if(changes == CacheChanges.Remove){
			keys.remove(key);
		}
	}
}

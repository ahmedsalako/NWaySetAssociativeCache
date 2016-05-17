package com.ahmedsalako.cache.evictionstrategy;

import java.util.LinkedList;

import com.ahmedsalako.cache.enums.CacheChanges;
import com.ahmedsalako.cache.extensions.Key;
import com.ahmedsalako.cache.interfaces.EvictionStrategy;

/**
 * this eviction policy is based on the first in first out approach. Removes the long existing key
 * 
 * @author Ahmed
 *
 * @param <TTag>
 */
public class FIFOEvictionStrategy<TTag> implements EvictionStrategy<TTag>
{
	/**
	 * The LinkedList tracking the ordering of keys as they are added, removed
	 */
	private LinkedList<Key<TTag>> keys = new LinkedList<Key<TTag>>();
	
	/**
	 * The cache size
	 */
	private int cacheSize;

	/**
	 * accept cache change notification
	 */
	@Override
	public void onInitialisation(int cacheSize) {
		this.cacheSize = cacheSize;
	}

	/**
	 * Get the key that is evictable according to the eviction strategy
	 */
	@Override
	public Key<TTag> getEvictableKey() {
		return keys.getFirst();
	}

	@Override
	public void OnCacheChange(Key<TTag> key, CacheChanges changes) {
		if(changes == CacheChanges.Add){
			keys.remove(key);
			keys.addLast(key);
		}
		else if(changes == CacheChanges.Remove){
			keys.remove(key);
		}
	}
}

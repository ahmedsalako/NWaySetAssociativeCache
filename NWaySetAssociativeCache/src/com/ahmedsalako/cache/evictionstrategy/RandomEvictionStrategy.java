package com.ahmedsalako.cache.evictionstrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.ahmedsalako.cache.enums.CacheChanges;
import com.ahmedsalako.cache.extensions.Key;
import com.ahmedsalako.cache.interfaces.EvictionStrategy;

public class RandomEvictionStrategy<TTag> implements EvictionStrategy<TTag>
{
	/**
	 * If the keys are to be random, then the sorting should be unordered
	 */
	HashSet<Key<TTag>> keys = new HashSet<Key<TTag>>();
	
	/**
	 * Cache size
	 */
	private int cacheSize;
	
	private Random random = new Random();
	
	@Override
	public void onInitialisation(int cacheSize) {
		this.cacheSize = cacheSize;
	}

	/**
	 * Get the key that is evictable according to the eviction selection
	 */
	@Override
	public Key<TTag> getEvictableKey() {

		Key<TTag> key = new ArrayList<Key<TTag>>(keys)
								.get(random.nextInt());
		
		return key;
	}

	/**
	 * should be used by the eviction strategy for initialisation purpose
	 */
	@Override
	public void onCacheChange(Key<TTag> key, CacheChanges changes) {
		if(changes == CacheChanges.Add){
			keys.remove(key);
			keys.add(key);
		}
		else if(changes == CacheChanges.Remove){
			keys.remove(key);
		}
	}
}

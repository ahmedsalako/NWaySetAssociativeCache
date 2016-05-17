package com.ahmedsalako.cache.interfaces;

public interface InMemoryCache<TTag, TData> {

	/**
	 * add data to cache by the specified tag
	 * 
	 * @param tag a unique key that represents the data
	 * @param data the data to be stored in the cache
	 */
	void add(TTag tag, TData data);
	
	/**
	 * get data from cache by the specified tag
	 * 
	 * @param tag a unique key that represents the data
	 * @return the stored data if any, otherwise null
	 */
	TData get(TTag tag);
	
	/**
	 * remove data from cache using the specified tag
	 * @param tag a unique key that represents the data
	 */
	void remove(TTag tag);
	
	/**
	 * Clears the entire cache lines in all sets
	 */
	void clear();
}

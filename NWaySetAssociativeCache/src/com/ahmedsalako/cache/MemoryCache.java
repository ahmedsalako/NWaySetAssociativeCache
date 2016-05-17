package com.ahmedsalako.cache;

import com.ahmedsalako.cache.enums.EvictionType;
import com.ahmedsalako.cache.evictionstrategy.FIFOEvictionStrategy;
import com.ahmedsalako.cache.evictionstrategy.LRUEvictionStrategy;
import com.ahmedsalako.cache.evictionstrategy.MRUEvictionStrategy;
import com.ahmedsalako.cache.extensions.Key;
import com.ahmedsalako.cache.interfaces.EvictionStrategy;
import com.ahmedsalako.cache.interfaces.InMemoryCache;

public class MemoryCache<TTag, TData> implements InMemoryCache<TTag, TData>
{
	/**
	 * A member field that stores the lists of SetBlock
	 */
    private SetBlockCollection<TTag, TData> setBlockCollection;

    /**
     * ctor for initialising MemoryCache
     * 
     * @param setCapacity
     * @param lineCapacity
     * @param evictionStrategy
     */
    public MemoryCache(int setCapacity, int lineCapacity, EvictionStrategy<TTag> evictionStrategy)
    {
        this.setBlockCollection = new SetBlockCollection<TTag, TData>(setCapacity, lineCapacity, new CacheChangeTracker<TTag, TData>(evictionStrategy));
    }

    /**
     * Ctor for initialising MemoryCache
     * 
     * @param setCapacity
     * @param lineCapacity
     * @param evictionType
     */
    public MemoryCache(int setCapacity, int lineCapacity, EvictionType evictionType) 
    {
       this(setCapacity, lineCapacity, getEvictionStrategy(evictionType)); 
    }

    private static <TTag> EvictionStrategy<TTag> getEvictionStrategy(EvictionType evictionType)
    {
        switch (evictionType)
        {
            case FIFO:
                return new FIFOEvictionStrategy<TTag>();
            case MRU:
                return new MRUEvictionStrategy<TTag>();
            default:
                return new LRUEvictionStrategy<TTag>();
        }
    }

    /**
     * add data to cache by the specified tag
     * 
     * @param tag
     * @param data
     */
    @Override
    public void add(TTag tag, TData data)
    {
        Key<TTag> key = Key.getInstance(tag);

        SetBlock<TTag, TData> set = setBlockCollection.get(key);

        set.add(key, data);
    }

    /**
     * get data from cache by the specified tag
     * @param tag
     * @return
     */
    @Override
    public TData get(TTag tag)
    {
        Key<TTag> key = Key.getInstance(tag);

        SetBlock<TTag, TData> set = setBlockCollection.get(key);

        SetLine<TTag, TData> line = set.get(key);

        return (null == line)? null : line.getData();
    }

    /**
     * remove data from cache using the specified tag
     * 
     * @param tag
     */
    public void remove(TTag tag)
    {
        Key<TTag> key = Key.getInstance(tag);

        SetBlock<TTag, TData> set = setBlockCollection.get(key);

        set.remove(key);
    }

    /// <summary>
    /// Clears the entire cache lines in all sets
    /// </summary>
    public void clear()
    {
        setBlockCollection.clear();
    }
}

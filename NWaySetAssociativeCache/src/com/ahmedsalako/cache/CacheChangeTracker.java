package com.ahmedsalako.cache;

import com.ahmedsalako.cache.enums.CacheChanges;
import com.ahmedsalako.cache.extensions.Key;
import com.ahmedsalako.cache.interfaces.EvictionStrategy;

public class CacheChangeTracker<TTag, TData>
{
    private EvictionStrategy<TTag> evictionStrategy;

    /**
     * 
     * @param evictionStrategy the eviction strategy instance
     */
    CacheChangeTracker(EvictionStrategy<TTag> evictionStrategy)
    {
        this.evictionStrategy = evictionStrategy;
    }

    /**
     * initialisation method invoked when the 
     * @param cacheSize
     */
    void init(int cacheSize)
    {
        evictionStrategy.onInitialisation(cacheSize);
    }

    void onRemove(Key<TTag> key)
    {
        evictionStrategy.onCacheChange(key, CacheChanges.Remove);
    }

    void onAdd(Key<TTag> key)
    {
        evictionStrategy.onCacheChange(key, CacheChanges.Add);
    }

    void onMiss(Key<TTag> key)
    {
        evictionStrategy.onCacheChange(key, CacheChanges.Remove);
    }

    void onHit(Key<TTag> key)
    {
        evictionStrategy.onCacheChange(key, CacheChanges.Access);
    }

    void performEviction(SetBlock<TTag, TData> setBlock)
    {
        if (setBlock.atFullCapacity())
        {
            setBlock.remove(evictionStrategy.getEvictableKey());
        }
    }
}

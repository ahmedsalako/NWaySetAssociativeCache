package com.ahmedsalako.cache;

import com.ahmedsalako.cache.extensions.Key;

public class SetLine<TTag, TData> 
{
    private int hitCount;

    private TData data;

    private Key<TTag> tag;

    /**
     * the SetBlock that this line belongs to
     */
    private SetBlock<TTag, TData> owner;

    SetLine(Key<TTag> tag, TData data, SetBlock<TTag, TData> owner)
    {
        this.tag = tag;
        this.data = data;
        this.owner = owner;
        this.hit();
    }
    
    public TData getData(){
    	return data;
    }

    /**
     * Records the hit count of the cache item
     */
    void hit()
    {
        ++hitCount;
    }
}

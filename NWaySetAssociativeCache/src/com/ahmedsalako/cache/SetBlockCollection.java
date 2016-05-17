package com.ahmedsalako.cache;

import java.util.ArrayList;
import java.util.Iterator;

import com.ahmedsalako.cache.extensions.Key;

public class SetBlockCollection<TTag, TData> implements Iterable<SetBlock<TTag, TData>>
{
	/**
	 * A lists of allowed sets in the cache
	 */
    private ArrayList<SetBlock<TTag, TData>> sets;

    /**
     * the allowed capacity of the cache
     */
    private int setCapacity;

    /**
     * the allowed line capacity of a set
     */
    private int lineCapacity;

    /**
     * Ctor of SetBlockCollection for initialising the collection
     * 
     * @param setCapacity
     * @param lineCapacity
     * @param changeTracker
     * @return
     */
    SetBlockCollection(int setCapacity, int lineCapacity, CacheChangeTracker<TTag, TData> changeTracker)
    {
        this.setCapacity = setCapacity;
        this.lineCapacity = lineCapacity;

        sets = new ArrayList<SetBlock<TTag, TData>>(setCapacity);

        for(int i=0; i < setCapacity; i++) {
            sets.add(new SetBlock<TTag, TData>(lineCapacity, i, changeTracker));
        }
    }

    /**
     * gets the SetBlock from the lists by the tag provided by the client
     * 
     * @param tag
     * @return
     */
    public SetBlock<TTag, TData> get(Key<TTag> tag)
    {
        int index = getIndex(tag);

        return sets.get(index);
    }

    /**
     * get the index of the SetBlock by the tag provided by the client
     * 
     * @param tag
     * @return
     */
    public int getIndex(Key<TTag> tag)
    {
        int tagHash = tag.hashCode();

        return Math.abs(tagHash) % setCapacity;
    }

    /**
     * Clears the entire cache lines in all sets
     */
    public void clear()
    {
        for(SetBlock<TTag, TData> setBlock : sets)
        {
            setBlock.clear();
        }
    }

    /**
     * Iterator for the items contained
     */
	@Override
	public Iterator<SetBlock<TTag, TData>> iterator() {
		
		return sets.iterator();
	}
}

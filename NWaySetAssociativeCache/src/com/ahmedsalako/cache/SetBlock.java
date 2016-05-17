package com.ahmedsalako.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.ahmedsalako.cache.extensions.Key;

public class SetBlock<TTag, TData>
{
	/**
	 * Thread safe dictionary for storing lines within the set block
	 */
	private ConcurrentMap<Key<TTag>, SetLine<TTag, TData>> setLines;

	/**
	 * The number of lines allowed in the set block
	 */
    private int capacity;

    /**
     * The set index
     */
    private int index;

    /**
     * Change tracker, for propagating changes to the interested listeners
     */
    private CacheChangeTracker<TTag, TData> changeTracker;


    /**
     * for accessing the index of the SetLine items
     */
    private SetLine<TTag, TData> getInternal(Key<TTag> tag)
    {
        SetLine<TTag, TData> setLine = null;
        
        return setLines.getOrDefault(tag, setLine);
    }

    /**
     * ctor for initialising the SetBlock class
     * 
     * @param capacity
     * @param index
     * @param changeTracker
     * @return
     */
    SetBlock(int capacity, int index, CacheChangeTracker<TTag, TData> changeTracker)
    {
        this.capacity = capacity;
        this.index = index;
        this.changeTracker = changeTracker;
        this.setLines = new ConcurrentHashMap<Key<TTag>, SetLine<TTag, TData>>();
    }

    /**
     * add cache data by key
     */
    void add(Key<TTag> tag, TData data)
    {
        if (null == tag) return;
        if (null == data) return;

        changeTracker.performEviction(this);

        if (setLines.containsKey(tag)){
            remove(tag);
        }

        setLines.putIfAbsent(tag, new SetLine<TTag, TData>(tag, data, this));
        changeTracker.onAdd(tag);
    }

    /**
     * gets a SetLine by the given tag/key
     */
    SetLine<TTag, TData> get(Key<TTag> tag)
    {
        SetLine<TTag, TData> setLine = getInternal(tag);

        if (setLine != null){
            setLine.hit();

            changeTracker.onHit(tag);

            return setLine;
        }

        changeTracker.onMiss(tag);

        return null;
    }

    /**
     * removes a set line by the given key
     * 
     * @param tag
     */
    void remove(Key<TTag> tag)
    {
        SetLine<TTag, TData> setLine = setLines.remove(tag);

        changeTracker.onRemove(tag);
    }

    /**
     * Clears the entire cache lines
     */
    public void clear()
    {
        setLines.clear();
    }

    /**
     * checks if set is at full capacity
     * 
     * @return
     */
    boolean atFullCapacity()
    {
        return (capacity == setLines.size());
    }
}

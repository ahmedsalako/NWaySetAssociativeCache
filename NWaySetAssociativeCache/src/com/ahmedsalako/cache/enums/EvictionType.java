package com.ahmedsalako.cache.enums;

public enum EvictionType 
{
	/**
	 *  Least Recently Used
	 */
	LRU (0),
	
	/**
	 * Most Recently Used
	 */
	MRU (1),
	
	/**
	 *  First In First Out
	 */
	FIFO (2),
	
	/**
	 * Random
	 */
	Random (3);
	
	final private int value;
	
	private EvictionType(int value) {
		this.value = value;
	}
}

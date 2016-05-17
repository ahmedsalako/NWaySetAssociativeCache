package com.ahmedsalako.cache.enums;

public enum CacheChanges {

	/**
	 * when a SetLine is added
	 */
	Add (0),
	
	/**
	 * when a SetLine is removed
	 */
	Remove (1),
	
	/**
	 * when a SetLine is accessed
	 */
	Access (2);
	
	final private int value;
	
	private CacheChanges(int value) {
		this.value = value;
	}
}

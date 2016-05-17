package com.ahmedsalako.cache.exceptions;

/**
 * An exception thrown when the capacity of a SetBlock
 * is full and there is an attempt to use it.
 * @author Ahmed
 *
 */
public class SetBlockAtFullCapacityException extends Exception 
{
	/**
	 * The index of the SetBlock represented by the action of this exception
	 */
	private int index;
	
	
	/**
	 *  ctor of the set block at full capacity exception class
	 *  
	 * @param index
	 */
	public SetBlockAtFullCapacityException(int index){
		super(String.format("Set Block at index %s is at full capacity!", index));				
	}	
}

package com.ahmedsalako.cache.extensions;

/**
 * The key class is used as a wrapper over the client supplied tag
 * @author Ahmed
 *
 * @param <TTag>
 */
public final class Key<TTag> 
{
	/**
	 *  Arbitrary tag supplied by the client
	 */
	private TTag tag;
	
	/**
	 *  the hash code of the client supplied tag
	 */
	private int hash;
	
	/**
	 *  private Ctor
	 * @param tag
	 */
	private Key(TTag tag){
		this.tag = tag;
		this.hash = tag.hashCode();
	}
	
	/**
	 *  the factory function for generating a key by the tag
	 * @param tag
	 * @return
	 */
	public static <TTag> Key<TTag> New(TTag tag){
		return new Key(tag);
	}
	
	/**
	 * returns the tag client hash code
	 */
	@Override
	public int hashCode() {
		return hash;
	}
	
	/**
	 * an equality test for the Key class
	 */
	@Override
	public boolean equals(Object obj) {
        if(obj instanceof Key<?> && obj != null){
            Key<TTag> valueToCompare = (Key<TTag>)obj;

            return this.hash == valueToCompare.hash;
        }

        return false;
	}
}

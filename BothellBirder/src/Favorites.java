/** @file Favorites.java
 * 
 *  Stores a collection of Bird objects representing a user's Favorites list.
 *  
 *  Design Decisions:
 *  
 *  	Considered LinkedHashSet vs LinkedList. LinkedHashSet does not allow
 *  	duplicate items, however, I and (possibly) the group, are unfamiliar
 *  	with them. Not sure if I would only need to implement a comparator in
 *  	Bird class for this to work, or if it would be something more complex.
 *  	Because of this, I have chosen to stick with LinkedList for now.
 * 
 *  @author
 *    Doug Tyler (dougwt@gmail.com)
 */

import java.util.LinkedList;


public class Favorites {
	private LinkedList<Bird> list;
	
	// Default Constructor
	public Favorites() {
		list = new java.util.LinkedList<Bird>();
	}
	
	// TODO: Add constructor that accepts path to file containing Bird IDs
	//		 used to populate the list.
	
	public boolean add(Bird b) {
		// TODO: check if the list already contains the bird before adding
		return list.add(b);
	}
	
	public boolean remove(Bird b) {
		return list.remove(b);
	}
	
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public int getSize() {
		return list.size();
	}
	
	// TODO: export(), import(), flush()
}

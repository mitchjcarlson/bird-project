import java.util.*;

public class Search {
	
	private ArrayDeque<String> colors, locations;
	private String name, description, scientificName;
	private boolean video, picture, sound;
	
	// Thinking about populating name, scientific name, color, and location 
	// at instantiation with a call to the database for a set of valid search 
	// values.
	String[] COLOR = {"red", "blue", "green", "yellow", "orange", "purple"};
	String[] LOCATION = {"washington", "oregon", "california"};
	
	/**
	 * Constructor: 
	 * parses initial search string, splitting it into an array by non-word
	 * characters followed by whitespace or just splitting it by whitespace.
	 * @pre initialString is not null
	 * @post initialized Search object with database query values in private
	 * data member array
	 * @param initialString:String
	 */
	public Search(String initialString) {
		colors = new ArrayDeque<String>();
		locations = new ArrayDeque<String>();
		
		String[] queryValue = parse(initialString);
		
		for (int i = 0; i < queryValue.length; i++) {
			if (is(COLOR, queryValue[i])) {
				colors.add(queryValue[i]);
			} else if (is(LOCATION, queryValue[i])) {
				locations.add(queryValue[i]);
			}
		}
	}
	
	/**
	 * Parses a string by commas and whitespace into an array
	 * @param parseString
	 * @return array of strings
	 */
	public String[] parse(String parseString) {
		// whitespace OR (comma AND whitespace) OR comma
		String regEx = "\\s+|,\\s+|,";
		return parseString.trim().toLowerCase().split(regEx);
	}
	
	/**
	 * Gets private data member colors. All colors are values found in the 
	 * database.
	 * @return String[] of valid colors
	 */
	public String[] getColors() {
		return colors.toArray(new String[colors.size()]);
	}
	
	/**
	 * Gets private data member locations. All locations are values found in
	 * the database.
	 * @return String[] of valid locations
	 */
	public String[] getLocations() {
		return locations.toArray(new String[locations.size()]);
	}
	
	/**
	 * Checks to see if token is a color
	 * @param token is checked against the current color list
	 * @return True if token is in color list
	 */
	public boolean is(String[] library, String token) {
		for(int i = 0; i < library.length; i++) {
			if(token.equals(library[i])) {
				return true;
			}
		}
		return false;
	}
}

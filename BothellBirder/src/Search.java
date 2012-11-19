
public class Search {
	
	private String[] dbQueryValues;
	
	/**Constructor
	 * Parses initial search string, splitting it into an array by non-word
	 * characters followed by whitespace or just splitting it by whitespace.
	 * @pre initialString is not null
	 * @post initialized Search object with database query values in private
	 * data member array
	 * @param initialString:String
	 */
	public Search(String initialString) {
		initialString = initialString.trim();
		dbQueryValues = initialString.split("[(\\W++)(\\s++)](\\s++)");
	}
	
	/**Get
	 * @param index >= 0; int
	 * @return database query value; String
	 */
	public String get(int index) {
		return dbQueryValues[index];
	}
}

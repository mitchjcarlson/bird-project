
public class Search {
	
	private String[] dbQueryValues;
	
	public Search(String initialString) {
		initialString = initialString.trim();
		dbQueryValues = initialString.split("[(\\W++)(\\s++)](\\s++)");
	}
	
	public String get(int index) {
		return dbQueryValues[index];
	}
}

package mysql;

public class TalkToDatabase extends MySQL {
	
	private static String HOST	= "108.59.2.69";	// Database info
	private static String DB 	= "dougwt_bird";
	private static String USER 	= "dougwt_bird";
	private static String PW 	= "31350fd3";
	private static int PORT 	= 3306;
	
	
	
	TalkToDatabase() { 
		
		super( HOST, PORT, DB, USER, PW );
		
	}

	
	public Bird getBirdById( int id ) {
		
	}
	
	public Bird getBirdByName( String name ) {
		
	}
	
	public Bird getBirdByScientificName( String SciName ) {
		
	}
	
	public Bird getBirdsByEndangeredStatus( boolean endangered ) {
		
	}
	
	public Bird getBirdsBySex( boolean sex ) {
		
	}
	
	public boolean addNewBird( Bird bird ) {
		
	}
	
	public boolean deleteBirdById( int id ) {
		
		return false;
		
	}

}

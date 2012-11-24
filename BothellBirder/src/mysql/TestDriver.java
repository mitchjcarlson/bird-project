package mysql;

import java.sql.ResultSet;
import java.io.*;

public class TestDriver {
	
	static String HOST	= "108.59.2.69";
	static String DB 	= "dougwt_bird";
	static String USER 	= "dougwt_bird";
	static String PW 	= "31350fd3";
	static int PORT 	= 3306;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// -------------- CONNECT --------------------//

		MySQL mysql = new MySQL( HOST, PORT, DB, USER, PW );
		
		try {
		
			mysql.connectToDatabase();
			
		} catch( Exception e ) { System.out.println("Error: " + e); }
		
		
		// ------------- PRINT COLUMNS -------------- //
		/* java.lang.NullPointerException error being thrown
		try {
		
			System.out.println("_____bird tabe____");
			mysql.printColumns( "bird" );
			System.out.println();
		
		} catch( Exception e ) { System.out.println("Error: " + e); }
		*/
		
		
		// -------------- INSERT ------------------- //
		
		String name 			= "KanyeWest";
		String scientificName 	= "PerionTombas";
		String description		= "One fat ass bird.  And a dumb motherfucker.";
		
		try {
			
			String query = "INSERT INTO bird "
					+ "(birdId, name, scientificName, description, endangeredStatus, sex) "
					+ "VALUES( default, '" + name + "', '" + scientificName + "', '" + description + "', 0, 0 )";
		
			mysql.insertIntoDatabase( query );
			
		} catch( Exception e ) { System.out.println("Error: " + e); }
		
		
		
		// ------------- GRAB INFO ----------------- //
		
		try {
			
			String query = "SELECT * from bird";
			
			ResultSet result = mysql.grabFromDatabase(query);		// Grab every bird in db	
			
			while( result.next() ) {								// While next set of data exists
				
				System.out.println("birdId: " 			+ result.getInt("birdId") );
				System.out.println("name: " 			+ result.getString("name") );
				System.out.println("scientificName: " 	+ result.getString("scientificName") );
				System.out.println("description: " 		+ result.getString("description") );
				System.out.println("endangeredStatus: " + result.getInt("endangeredStatus") );
				System.out.println("sex: " 				+ result.getInt("sex") );
				System.out.println("_______________________");
				
				mysql.close();
				
			}
			
		} catch( Exception e ) { System.out.println("Error: " + e); }

	}

}

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
		
			mysql.excecuteUpdate( query );
			
		} catch( Exception e ) { System.out.println("Error: " + e); }
		
		
		
		// ------------- GRAB INFO ----------------- //
		
		try {
			
			String query = "SELECT * from bird";
			
			ResultSet result = mysql.grabFromDatabase(query);		// Grab every bird in db	
			
			while( result.next() ) {								// While next set of data exists, print the data
				
				System.out.println("birdId: " 			+ result.getInt("birdId") );				
				System.out.println("name: " 			+ result.getString("name") );
				System.out.println("scientificName: " 	+ result.getString("scientificName") );
				System.out.println("description: " 		+ result.getString("description") );
				System.out.println("endangeredStatus: " + result.getInt("endangeredStatus") );
				System.out.println("sex: " 				+ result.getInt("sex") );
				System.out.println("_______________________________________________");
				
			}

			
		} catch( Exception e ) { System.out.println("Error: " + e); }
		
		
		
		// ----------- REMOVE ROWS (remove birds) --------- // 
		
		try {
			
			String query = "DELETE FROM bird WHERE name='" + name + "'";
			
			mysql.excecuteUpdate(query);
			
		} catch( Exception e ) { System.out.println("Error: " + e); }
		
		
		// ------------- CHECK REMOVED ----------------- //
		
		try {
			
			String query = "SELECT * from bird";
			
			ResultSet result = mysql.grabFromDatabase(query);		// Grab every bird in db	
			
			if( !result.next() )
				
				System.out.println( name + " not found.  (successful removal)");
			
			else
				
				System.out.println("Could not remove");

			
		} catch( Exception e ) { System.out.println("Error: " + e); }
		
		
		
		// --------- CLOSE DATABASE CONNECTION ----- //
		
		
		mysql.close();

	}

}

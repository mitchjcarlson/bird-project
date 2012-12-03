package mysql;

import bird.Bird;
import java.sql.ResultSet;
import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

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
		
		// TestMySQLQueries();
		
		TestQueryConstructing();

	}
	
	// Basic tests for base mysql functions

	public static void TestMySQLQueries() {
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
		
	// Tests for all methods creating and excecuting queries
	
	public static void TestQueryConstructing() {
		
		TalkToDatabase db = new TalkToDatabase();		
		
		// ----------- Test single bird method(s) -------------- //
		
		Bird b = db.getBirdById(21);			// by id that exists
		printBirdInfo(b);
		b = null;
		
		b = db.getBirdById(999999);				// by id that does NOT exist
		printBirdInfo(b);
		b = null;

		b = db.getBirdByName("Big Bird");		// by name that exists
		printBirdInfo(b);
		b = null;
		
		b = db.getBirdByName("Far");			// by name NOT exists
		printBirdInfo(b);
		b = null;
		
		b = db.getBirdByScientificName("crowmofo");		// by sci name exists
		printBirdInfo(b);
		b = null;
		
		b = db.getBirdByScientificName("dfasd");		// by sci name NOT exists
		printBirdInfo(b);
		b = null;
		
		System.out.println("Bird with name 'dfasdfa' Exists? "  + db.birdExists("dfasdfa"));
		System.out.println("Bird with id '43534' exists? " 		+ db.birdExists(43534));
		System.out.println("Bird with name 'Big Bird' Exists? "  + db.birdExists("Big Bird"));
		System.out.println("Bird with id '21' exists? " 		+ db.birdExists(21));
		
		// ---------- Test multiple bird method(s) ------------ //
		
		ArrayList<Bird> list = new ArrayList<Bird>();
		
		list = db.getBirdsByEndangeredStatus(0);
		printBirdList(list);
		list = null;
		
		list = db.getBirdsBySex(0);
		printBirdList(list);
		list = null;
		
		// --------- Add new bird --------------------------- //
		
		Bird newBird = new Bird( 0, "TestInsertBird", "TestSciName", "Test description", 0, 0 );
		
		db.addNewBird( newBird );
		printBirdInfo( db.getBirdByName("TestInsertBird") );
		newBird = null;
		
		// --------- Remove bird ----------------------------- //
		
		System.out.println("\nWas the bird deleted? " + db.deleteBirdByName("TestInsertBird") );
		
		System.out.println("Was a non existant bird deleted? " + db.deleteBirdByName("NoBird") );
		
		db.close();
	}
	
	public static void printBirdInfo( Bird b ) {
		
		System.out.println("--------------------");
		
		if( b == null) { System.out.println("Bird is NULL"); return; }
		
		System.out.println("Bird Name: " 	+ b.getName());
		System.out.println("Sci Name: " 	+ b.getScientificName());
		System.out.println("Description: "  + b.getDescription());
		System.out.println("Endangered: " 	+ b.getEndangeredStatus());
		System.out.println("Sex: " 			+ b.getSex());

	}
	
	public static void printBirdList( ArrayList<Bird> list ) {
		
		ListIterator<Bird> i = list.listIterator();
		
		while( i.hasNext() ) {
			
			printBirdInfo( i.next() );
			
		}
	}
		
}

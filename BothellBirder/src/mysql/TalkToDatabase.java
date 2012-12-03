package mysql;

import java.sql.*;
import java.util.ArrayList;
import bird.Bird;

public class TalkToDatabase extends MySQL {
	
	private static String HOST	= "108.59.2.69";	// Database info
	private static String DB 	= "dougwt_bird";
	private static String USER 	= "dougwt_bird";
	private static String PW 	= "31350fd3";
	private static int PORT 	= 3306;
	
	
	
	TalkToDatabase() { 
		
		super( HOST, PORT, DB, USER, PW );
		
	}

	
	// Retuns a single bird object with the ID passed in
	// Returns null if no match was found
	
	public Bird getBirdById( int id ) {
		
		String query = "SELECT * FROM bird WHERE birdId=" + id;
		
		try {
			
			connectToDatabase();									// Connect to db
		
			ResultSet result = this.grabFromDatabase( query );		// Get bird by id
			
			if( !result.next() ) return null;						// return null if no results found
			
			Bird bird = createBirdObject( result );					// Create new bird object
			
			close();												// Close db connection
			
			return bird;
			
		} catch( Exception e ) {
			logError(e);
			return null; 
		}
		
	}
	
	// Retuns a single bird object with the name name passed in
	// Returns null if no match was found
	
	public Bird getBirdByName( String name ) {
		
		String query = "SELECT * FROM bird WHERE name='" + name + "'";
		
		try {
			
			connectToDatabase();									// Connect to db
		
			ResultSet result = this.grabFromDatabase( query );		// Get bird by id
			
			if( !result.next() ) return null;						// return null if no results found
			
			Bird bird = createBirdObject( result );					// Create new bird object
			
			close();												// Close db connection
			
			return bird;
			
		} catch( Exception e ) { 
			logError(e);
			return null;
		}
		
	}
	
	
	
	// Retuns a single bird object with the scientific name passed in
	// Returns null if no match was found
	
	public Bird getBirdByScientificName( String sciName ) {
		
		String query = "SELECT * FROM bird WHERE scientificName='" + sciName + "'";
		
		try {
			
			connectToDatabase();								// Connect to db
		
			ResultSet result = this.grabFromDatabase( query );		// Get bird by id
			
			if( !result.next() ) return null;						// return null if no results found
			
			Bird bird = createBirdObject( result );					// Create new bird object
			
			close();												// Close db connection
			
			return bird;
			
		} catch( Exception e ) { 
			logError(e);
			return null; 
		}
		
	}
	
	
	
	// Returns an ArrayList of bird objects that match the endangered status passed in
	// Returns null if no match was found
	
	public ArrayList<Bird> getBirdsByEndangeredStatus( int endangered ) {
		
		String query = "SELECT * FROM bird WHERE endangeredStatus=" + endangered;
		
		try {
			
			this.connectToDatabase();								// Connect to db
		
			ResultSet result = this.grabFromDatabase( query );		// Get bird by id
			
			ArrayList<Bird> birdList = createBirdList( result );	// Create list and populate with our bird results
			
			close();												// Close db connection
			
			return birdList;
			
		} catch( Exception e ) { 
			logError(e);
			return null; 
		}
		
	}
	
	
	
	// Returns an ArrayList of bird objects that match the sex passed in
	// Returns null if no match was found
	
	public ArrayList<Bird> getBirdsBySex( int sex ) {
		
		String query = "SELECT * FROM bird WHERE sex=" + sex;
		
		try {
			
			this.connectToDatabase();								// Connect to db
		
			ResultSet result = this.grabFromDatabase( query );		// Get bird by id
			
			ArrayList<Bird> birdList = createBirdList( result );	// Create list and populate with our bird results
			
			close();												// Close db connection
			
			return birdList;
			
		} catch( Exception e ) {
			logError(e);
			return null; 
		}
		
	}
	
	// Adds a bird to the database if it's name does not exist
	
	public boolean addNewBird( Bird bird ) {
		
		// TODO: Implement add locations, colors, image, sound, etc...
		// TODO: Should multiple sciNames be allowed?
		
		try {

			this.connectToDatabase();
			
			if( birdExists( bird.getName() ) ) return false;		// If bird already exists
			
		} catch( Exception e ) {
			logError(e);
			return false;
		}
		
		
		String query 	= "INSERT INTO bird " +
						"(birdId, name, scientificName, description, endangeredStatus, sex) " +
				
						"VALUES( default, '" + bird.getName() + "', '" + bird.getScientificName() + "', " +
						"'" + bird.getDescription() + "', '" + bird.getEndangeredStatus() + "', '" + bird.getSex() + "' )";
		
		try {
			
			return excecuteUpdate( query );
			
		} catch( Exception e ) { 
			logError(e);
			return false; 
		}
		
	}
	
	// Deletes a bird based on the id passed in
	
	public boolean deleteBirdById( int id ) {
		
		try {
			
			if( !birdExists( id ) ) return false;				// Bird does not exist to remove
			
			String query = "DELETE FROM bird WHERE birdId=" + id;	
				
			excecuteUpdate(query);								// Remove the bird
			
			return true;
			
		} catch( Exception e ) { 
			logError(e);
			return false;
		}

	}
	public boolean deleteBirdByName( String name ) {
		
		try {
			
			this.connectToDatabase();
			
			if( !birdExists( name ) ) return false;				// Bird does not exist to remove
			
			String query = "DELETE FROM bird WHERE NAME='" + name + "'";	
				
			excecuteUpdate(query);								// Remove the bird
			
			return true;
			
		} catch( Exception e ) { 
			logError(e);
			return false;
		}

	}
	
	
	// Checks if a bird exists with the passed in ID
	
	public boolean birdExists( int id ) {
		
		String query = "SELECT * FROM bird WHERE birdId=" + id;
		
		try {
		
			ResultSet result = grabFromDatabase( query );
			
			return result.next();
		
		} catch( Exception e ) { 	
			logError(e);	
			return false;	
		}
		
	}
	
	// Checks if a bird exists wit the passed in name
	
	public boolean birdExists( String name ) {
		
		String query = "SELECT * FROM bird WHERE name='" + name + "'";
		
		try {
		
			ResultSet result = grabFromDatabase( query );
			
			return result.next();
		
		} catch( Exception e ) { 	
			logError(e);	
			return false;	
		}
		
	}
	
	
	
	private Bird createBirdObject( ResultSet result ) {
		
		try {
		
			Bird bird = new Bird();		// Setup bird object
			
			bird.setId( 				result.getInt( "birdId" ) );
			bird.setName( 				result.getString( "name" ) );
			bird.setScientificName( 	result.getString( "scientificName" ) );
			bird.setDescription( 		result.getString( "description" ) );
			bird.setEndangeredStatus( 	result.getInt( "endangeredStatus" ) );
			bird.setSex( 				result.getInt( "sex" ) );
			
			// TODO: Set rests of Bird class variables
			
			return bird;
		
		} catch( Exception e ) { 	
			logError(e);	
			return null; 
		}
		
	}
	
	private ArrayList<Bird> createBirdList( ResultSet result ) {
		
		ArrayList<Bird> birdList = new ArrayList<Bird>();	// Create list to hold bird objects in :: ArrayList chosen for fastest indexed access
		
		try {
		
			while( result.next() ) {						// Loop while we have more results
			
				Bird bird = createBirdObject( result );		// Create new bird object
				
				birdList.add( bird );						// Add bird to list
			
			}
		
		} catch( Exception e ) {}
		
		return birdList;
		
	}
	
	private void logError( Exception e ) {
		
		// TODO: implement logging into a file
		
		System.out.println();
		System.out.println("Error: " + e.getMessage());
		System.out.println();
		
	}

}

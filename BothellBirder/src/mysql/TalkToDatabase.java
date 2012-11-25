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
	
	public Bird getBirdByName( String name ) {
		
		String query = "SELECT * FROM bird WHERE name=" + name;
		
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
	
	public Bird getBirdByScientificName( String sciName ) {
		
		String query = "SELECT * FROM bird WHERE scientificName=" + sciName;
		
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
	
	public ArrayList<Bird> getBirdsByEndangeredStatus( boolean endangered ) {
		
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
	
	public ArrayList<Bird> getBirdsBySex( boolean sex ) {
		
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
	
	public boolean addNewBird( Bird bird ) {
		
		// TODO: Implement add locations, colors, image, sound, etc...
		
		if( birdExists( bird.getName() ) ) return false;		// If bird already exists
		
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
	
	public boolean deleteBirdById( int id ) {
		
		try {
			
			if( !birdExists( id ) ) return false;				// Bird does not exist to remove
			
			String query = "DELETE FROM bird WHERE id=" + id;	
				
			excecuteUpdate(query);								// Remove the bird
			
			return true;
			
		} catch( Exception e ) { 
			logError(e);
			return false;
		}

	}
	
	
	public boolean birdExists( int id ) {
		
		String query = "SELECT * FROM bird WHERE id=" + id;
		
		try {
		
			ResultSet result = grabFromDatabase( query );
			
			return result.next();
		
		} catch( Exception e ) { 	
			logError(e);	
			return false;	
		}
		
	}
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
	
	
	// TODO: Set rests of Bird class variables
	
	private Bird createBirdObject( ResultSet result ) {
		
		try {
		
			Bird bird = new Bird();		// Setup bird object
			
			bird.setId( 				result.getInt( "birdId" ) );
			bird.setName( 				result.getString( "name" ) );
			bird.setScientificName( 	result.getString( "scientificName" ) );
			bird.setDescription( 		result.getString( "description" ) );
			bird.setEndangeredStatus( 	result.getBoolean( "endangeredStatus" ) );
			bird.setSex( 				result.getBoolean( "sex" ) );
			
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

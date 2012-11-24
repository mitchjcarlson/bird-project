package mysql;

// Based on Tutorial at:
//http://www.vogella.com/articles/MySQLJava/article.html

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

	protected static boolean 	debug = true;

	protected Connection 		_connect;
	protected Statement 		_statement;
	protected PreparedStatement _preparedStatement;
	protected ResultSet			_resultSet;

	protected String _host;					// Host address
	protected int 	 _port;					// Host address port
	protected String _database;				// Database name
	protected String _username;				// Database username
	protected String _password;				// Username password

	public MySQL( String host, int port, String database, String username, String password ) {

		_host = 	host;
		_port = 	port;
		_database = database;
		_username = username;
		_password = password;

	}
	
	
	// Create a connection to the database

	public void connectToDatabase() throws Exception {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");				// Load the MySql driver
			
			if( debug )
				System.out.println("Debug: JDBC Driver Loaded Successfully!");
			
			_connect = DriverManager.getConnection("jdbc:mysql://" + _host + ":" + _port + "/" + 	// Connect to database
					_database + "?" + "user=" + _username + "&password=" + _password );
			
			if( debug )
				System.out.println("Debug: Connection to Database Successful!");
		
		}
		
		catch( Exception e ) { throw e; } 

	}
		
	
	
	// Grabs information from database 
	
	public ResultSet grabFromDatabase( String query ) throws Exception {
		
		try {
	
			_statement = _connect.createStatement();		// States allow to issue SQL queries
			
			ResultSet infoGrabbed =  _statement.executeQuery( query );	// Gets the result from a query
			
			return infoGrabbed;
		
		} catch( Exception e ){ throw e; }				// I am still confused on exceptions

	}
	
	// Inserts or Updates data in database
	
	public boolean excecuteUpdate( String query ) throws Exception {
		
		try {
			
			_preparedStatement = _connect.prepareStatement( query );
			
			_preparedStatement.executeUpdate();					// Execute the INSERT/DELETE/UPDATE
			
			if( debug )
				System.out.println("Debug: Query Excecuted Successfully");
			
			return true;
			
		} catch( Exception e ) { throw e; }
		
	}
	
	
	// Debug only
	// Prints out the columns from a specific table
		
	public void printColumns( String tableName ) throws Exception {
		
		if( !debug ) return;	// Return if not in debug mode
		
		try {
			
			_resultSet = _statement.executeQuery( "SELECT * FROM " + tableName );
			
			System.out.println( "The columns in " + _database + " : " + tableName + " are: " );
			
			System.out.println( "Table: " + _resultSet.getMetaData().getTableName(1) );
			
			for( int i = 1; i <= _resultSet.getMetaData().getColumnCount(); i++ )
				
					System.out.println( "Column " + i + " " + _resultSet.getMetaData().getColumnName(i) );
		
		} catch( Exception e ) { throw e; }
		
	}
		
	
	protected void close() {

		try {

			// Close all connections

			if (_resultSet != null)

				_resultSet.close();

			if (_statement != null)

				_statement.close();

			if (_connect != null)

				_connect.close();

		} catch (Exception e) {

		}
	}

}

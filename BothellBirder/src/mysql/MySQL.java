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

	protected static boolean 	debug;

	protected Connection 		_connect;
	protected Statement 		_statement;
	protected PreparedStatement _preparedStatement;
	protected ResultSet			_resultSet;

	protected String _host;					// Host address
	protected String _port;					// Host address port
	protected String _database;				// Database name
	protected String _username;				// Database username
	protected String _password;				// Username password

	public MySQL( String host, String port, String database, String username, String password ) {

		_host = 	host;
		_port = 	port;
		_database = database;
		_username = username;
		_password = password;

	}
	
	
	// Prints out the physical layout of the database (tables and column names)
	
	private void writeMetaData( ResultSet resultSet ) throws SQLException {
		
		System.out.println( "The columns in the table are: " );
		
		System.out.println( "Table: " + resultSet.getMetaData().getTableName(1) );
		
		for( int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++ )
			
				System.out.println( "Column " + i + " " + resultSet.getMetaData().getColumnName(i) );
		
	}
	
	
	
	// Grabs information from database
	
	public ResultSet grabFromDatabase( String query ) throws SQLException {
		
		try {
			
			this.connectToDatbase(); 						// Connect to database
	
			_statement = _connect.createStatement();		// States allow to issue SQL queries
			
			ResultSet infoGrabbed =  _statement.executeQuery( query );	// Gets the result from a query
			
			this.close();									// Close database connection
			
			return infoGrabbed;
		
		}
		catch( Exception e ){ return null; }				// I am still confused on exceptions

	}
	
	public boolean insertIntoDatabase( String query ) throws SQLException {
		
		return false;
		
	}
	
	
	// Create a connection to the database

	private void connectToDatbase() throws Exception {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");				// Load the MySql driver
			
			if( debug )
				System.out.println("JDBC Driver Loaded Successfully!");
			
			_connect = DriverManager.getConnection("jdbc:mysql://" + _host + ":" + _port + "/" + 	// Connect to database
					_database + "?" + "user=" + _username + "&password=" + _password );
			
			if( debug )
				System.out.println("Connection to Database Successful!");
		
		}
		
		catch( Exception e ) { throw e; } 

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

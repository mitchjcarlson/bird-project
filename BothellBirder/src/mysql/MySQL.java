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

	protected static boolean debug;

	protected Connection _connect;
	protected Statement _statement;
	protected PreparedStatement _preparedStatement;
	protected ResultSet _resultSet;

	protected String _host;
	protected String _port;
	protected String _database;
	protected String _username;
	protected String _password;

	public MySQL(String host, String port, String database, String username,
			String password) {

		_host = host;
		_port = port;
		_database = database;
		_username = username;
		_password = password;

	}

	public void writeResultSet(ResultSet resultSet) throws SQLException {

		while (resultSet.next()) {

			String user = resultSet.getString("username");
			String email = resultSet.getString("email");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");

			System.out.println("Username: " + user);
			System.out.println("Email: " + email);
			System.out.println("First Name: " + firstName);
			System.out.println("Last Name: " + lastName);

		}

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

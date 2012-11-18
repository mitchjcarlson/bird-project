package mysql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDatabase extends MySQL {

	public TestDatabase(String host, String port, String database,
			String username, String password) {

		super(host, port, database, username, password);

	}

	private void writeMetaData(ResultSet resultSet) throws SQLException {

		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));

		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)

			System.out.println("Column " + i + " "
					+ resultSet.getMetaData().getColumnName(i));

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

	public void runExamples() throws Exception {

		try {

			// ---------------------------- CONNECT
			// ------------------------------ //

			// Load the MySql driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			if (debug)
				System.out.println("JDBC Driver Loaded Successfully!");

			// Connect to DB
			_connect = DriverManager.getConnection("jdbc:mysql://" + _host
					+ ":" + _port + "/" + _database + "?" + "user=" + _username
					+ "&password=" + _password);
			// connect =
			// DriverManager.getConnection("jdbc:mysql://74.54.145.98:3306/nofrobro_javaTest?user=nofrobro_test&password=james1");

			if (debug)
				System.out.println("Connection to Database Successful!");

			// ---------------------------- NORMAL STATEMENTS
			// ------------------------------ //

			// States allow to issue SQL queries
			_statement = _connect.createStatement();

			// Result set gets the result from a query
			_resultSet = _statement.executeQuery("SELECT * FROM users");

			// Print out results (using my own method)
			writeResultSet(_resultSet);

			// ---------------------------- PREPARED STATEMENTS
			// ------------------------------ //

			// PreparedStatements can use variables and are more efficient
			_preparedStatement = _connect
					.prepareStatement("INSERT INTO users VALUES( default, ?, ?, ?, ? )");

			// Skip 0 because we have 'default'. # corresponds with ?
			_preparedStatement.setString(1, "UserNameTest");
			_preparedStatement.setString(2, "EmailTest");
			_preparedStatement.setString(3, "FirstName");
			_preparedStatement.setString(4, "LastName");

			// Execute the INSERT/DELETE/UPDATE
			_preparedStatement.executeUpdate();

			if (debug)
				System.out.println("Added user successful!");

			// Can get results with prepared statements as well
			_preparedStatement = _connect
					.prepareStatement("SELECT * FROM users");
			_resultSet = _preparedStatement.executeQuery();
			writeResultSet(_resultSet);

			// Remove something

			_preparedStatement = _connect
					.prepareStatement("DELETE FROM users WHERE id=? ; ");

			_preparedStatement.setString(1, "2");

			_preparedStatement.executeUpdate();

			// ---------------------------- PRINT DB INFO
			// ------------------------------ //

			_resultSet = _statement.executeQuery("SELECT * FROM users");

			writeMetaData(_resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

}

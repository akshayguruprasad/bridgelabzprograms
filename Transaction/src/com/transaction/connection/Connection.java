package com.transaction.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.transaction.commons.Credentials;

public class Connection extends BaseConnection {
	private static java.sql.Connection connection = null;
	static {
		try {
			Class.forName(Credentials.CLASS);
			connection = DriverManager.getConnection(Credentials.DBURL, Credentials.USER, Credentials.PASSWORD);
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			System.err.println("Exception occured [Connection][static] : " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Exception occured [Connection][static] : " + e.getMessage());
		}

	}

	private Connection() {
		super(connection);
	}

	@SuppressWarnings("static-access")
	public static java.sql.Connection getConnection() {
		
		return new Connection().connection;
	}

}

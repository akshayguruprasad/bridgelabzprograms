package com.transaction.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Utility {
	private static final Scanner scanner = new Scanner(System.in);

	/**
	 * GET CONSOLE INPUTS DOUBLES
	 * 
	 * @return
	 */
	public static double getDouble() {
		double data = scanner.nextDouble();
		scanner.nextLine();
		return data;
	}

	public static long getLong() {

		long data = scanner.nextLong();
		scanner.nextLine();
		return data;

	}

	/**
	 * GET CONSOLE INPUTS STRINGS
	 * 
	 * @return
	 */
	public static String getString() {
		return scanner.nextLine();
	}

	/**
	 * GET CONSOLE INPUTS INTS
	 * 
	 * @return
	 */
	public static int getInt() {
		int data = scanner.nextInt();
		scanner.nextLine();
		return data;
	}

	/**
	 * USED TO FREE ALL THE RESOURCE ASSOCIATED WITH JDBC
	 * 
	 * @param connection
	 * @param statement
	 * @param resultSet
	 */
	public final static void freeResources(Connection connection) {

		if (connection != null) {

			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println("Exception occured [Utility][freeResources] : " + e.getMessage());
			}

		}

	}

	public final static void freeResources(Statement statement, ResultSet... resultSet) {

		for (ResultSet resultSetEach : resultSet) {
			if (resultSetEach != null) {// INSTANCE FOUND
				try {
					resultSetEach.close();
				} catch (SQLException e) {
					System.err.println("Exception occured [Utility][freeResources] : " + e.getMessage());
				}
			}
		}

		if (statement != null) {

			try {
				statement.close();
			} catch (SQLException e) {
				System.err.println("Exception occured [Utility][freeResources] : " + e.getMessage());
			}

		}

	}

}

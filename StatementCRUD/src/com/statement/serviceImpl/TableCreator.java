package com.statement.serviceImpl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {

	public static void generateTables() {
		Statement ps = null;
		Connection connection = null;
		try {
			File inventoryTable = new File("/home/bridgeit/inventory.sql");
			StringBuffer QUERY = null;
			FileReader fileReader = new FileReader(inventoryTable);
			String query1 = readFileContents(QUERY, fileReader);
			File requirementTable = new File("/home/bridgeit/requirementTable.sql");
			fileReader = new FileReader(requirementTable);
			String query2 = readFileContents(QUERY, fileReader);

			connection = ConncetionSupplier.getConnection();
			ps = connection.createStatement();
			ps.addBatch(query1);
			ps.addBatch(query2);

			ps.executeBatch();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
if(connection!=null) {
	try {
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
}
		}

	}

	private static String readFileContents(StringBuffer QUERY, FileReader fileReader) throws IOException {
		int c = -1;
		QUERY = new StringBuffer("");
		while ((c = fileReader.read()) != -1) {

			QUERY.append((char) c);

		}

		return QUERY.toString();
	}

}

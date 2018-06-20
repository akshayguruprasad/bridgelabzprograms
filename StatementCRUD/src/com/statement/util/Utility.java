package com.statement.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.statement.entity.CheckListItem;
import com.statement.entity.Item;

public class Utility {
	private static final Scanner scanner = new Scanner(System.in);
	private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	public static String getUserString() {
		return scanner.nextLine();
	}

	public static int getUserInt() {

		int data = scanner.nextInt();

		scanner.nextLine();

		return data;

	}

	public static double getUserDouble() {

		double data = scanner.nextDouble();

		scanner.nextLine();

		return data;

	}

	public static Item getUserItem() {
		Item item = null;
		item = new Item();
		System.out.println("Enter the name of the item");

		String name = getUserString();
		item.setName(name);

		System.out.println("Enter the price of the item");

		double price = getUserDouble();
		System.out.println("Enter the exp date in dd/mm/yyyy");
		Date date = getUserDate();
		if (date == null) {
			return null;
		}
		item.setExpDate(date);

		item.setPrice(price);
		return item;

	}

	private static Date getUserDate() {

		String date = getUserString();
		try {
			return format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Item getUpdateItem() {
		System.out.println("Enter which attribute u want to change");
		System.out.println("Name");
		System.out.println("Price");
		/* System.out.println("Exit"); */

		String field = null;
		field = getUserString();
		System.out.println("Enter the new  value for the " + field);
		String newValue = getUserString();
		Item item = new Item();

		switch (field.toLowerCase()) {
		case "name":

			item.setName(newValue);

			break;

		case "price":
			item.setPrice(Double.parseDouble(newValue));
			break;

		default:

			System.out.println("Invalid operation ");

			return null;

		}

		return item;

	}

	public static CheckListItem getUserCheckListItem() {
		System.out.println("Enter the entry name");
		String name = getUserString();

		System.out.println("Enter " + name + " quantity.");
		int quantity = getUserInt();
		CheckListItem item = new CheckListItem();
		item.setName(name);
		item.setQuantity(quantity);

		return item;

	}
	
	
	public static  void closeOperations(Connection connection, Statement statement, ResultSet... rs) {

		for (ResultSet resultSet : rs) {

			if (resultSet != null) {

				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		if (statement != null) {

			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public static CheckListItem updateCheckList() {
		System.out.println("Enter the field to update ");
		
		System.out.println("name");
		System.out.println("quantity");
		String field=getUserString();
		
		System.out.println("Enter the new value for "+field);
		
		String newValue=getUserString();
		CheckListItem item=new CheckListItem();
		switch (field.toLowerCase()) {
		case "name":
			item.setName(newValue);
			
			break;
		case "quantity":
			
			item.setQuantity(Integer.parseInt(newValue.trim()));
			
			break;

		default:
			
			System.out.println("Invalid choice");
			return null;
		}
		
return item;		
		
	}

}

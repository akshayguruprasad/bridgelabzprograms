package com.statement.serviceImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.statement.entity.Item;
import com.statement.service.Inventory;
import com.statement.util.Utility;

public class InventoryManage implements Inventory {

	@Override
	public int add(Item item) {
		Statement statement = null;
		ResultSet rs = null;

		Connection connection = null;
		try {
			Date date = new Date(item.getExpDate().getTime());
			String subQuery = "('" + item.getName().toLowerCase() + "','" + item.getPrice() + "','" + date + "')";

			connection = ConncetionSupplier.getConnection();

			statement = connection.createStatement();
			statement.executeUpdate("insert into bigbazar.inventory(name,price,expire_date) values" + subQuery,
					Statement.RETURN_GENERATED_KEYS);
			rs = statement.getGeneratedKeys();
			if (rs.next()) {

				return rs.getInt(1);

			}

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());

		} finally {
			Utility.closeOperations(connection, statement, rs);
		}

		return 0;
	}

	

	@Override
	public boolean remove(int id) {

		Statement statement = null;

		Connection connection = null;
		try {
			connection = ConncetionSupplier.getConnection();

			statement = connection.createStatement();
			int res = statement.executeUpdate("delete from bigbazar.inventory where id=" + id);
			if (res > 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());

		} finally {

			Utility.closeOperations(connection, statement);

		}

		return false;

	}

	@Override
	public List<Item> searchItem(String name) {
		Statement statement = null;
		ResultSet rs = null;
		List<Item> itemList = null;
		Connection connection = null;
		try {
			connection = ConncetionSupplier.getConnection();

			statement = connection.createStatement();

			rs = statement.executeQuery("select * from bigbazar.inventory where name ='" + name.toLowerCase() + "'");
			itemList = new ArrayList<Item>();

			while (rs.next()) {

				Item item = new Item(rs.getInt(1), rs.getString(2), rs.getDouble(3), new Date(rs.getDate(4).getTime()));

				itemList.add(item);
			}

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());

		} finally {
			Utility.closeOperations(connection, statement, rs);

		}
		return itemList;

	}

	@Override
	public Item searchItem(int id) {
		Statement statement = null;
		ResultSet rs = null;

		Item item = null;
		Connection connection = null;
		try {
			connection = ConncetionSupplier.getConnection();

			statement = connection.createStatement();

			rs = statement.executeQuery("select * from bigbazar.inventory where id =" + id);

			while (rs.next()) {

				item = new Item(rs.getInt(1), rs.getString(2), rs.getDouble(3), new Date(rs.getDate(4).getTime()));

			}

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());

		} finally {
			Utility.closeOperations(connection, statement, rs);

		}
		return item;

	}

	@Override
	public boolean update(Item item) {
		Statement statement = null;
		boolean isUpdated = false;

		Connection connection = null;
		try {
			connection = ConncetionSupplier.getConnection();

			statement = connection.createStatement();

			String QUERY = "";
			if (item.getName() == null) {

				QUERY = "update inventory set price=" + item.getPrice();

			} else {
				QUERY = "update inventory set name='" + item.getName() + "'";
			}
			if (statement.executeUpdate(QUERY + " where id=" + item.getId()) > 0) {
				return true;
			}

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());

		} finally {

			Utility.closeOperations(connection, statement);
		}
		return isUpdated;
	}

}

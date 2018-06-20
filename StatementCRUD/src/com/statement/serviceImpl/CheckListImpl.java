package com.statement.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.statement.entity.CheckListItem;
import com.statement.service.CheckList;
import com.statement.util.Utility;

public class CheckListImpl implements CheckList {

	private final static String INSERT = "insert into requirement(name,quantity) values(?,?)";
	private final static String UPDATEQUANTITY = "update requirement set quantity=? where id=?";
	private final static String UPDATENAME = "update requirement set name=? where id=?";
	private final static String DELETE = "delete from requirement where id=?";
	private final static String SELECTBYNAME = "select * from requirement where name=?";
	private final static String SELECTBYID = "select * from requirement where id=?";

	@Override
	public int add(CheckListItem item) {
		Connection connection = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		int id = 0;
		try {
			connection = ConncetionSupplier.getConnection();

			prep = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

			prep.setString(1, item.getName().toLowerCase());
			prep.setInt(2, item.getQuantity());

			prep.executeUpdate();
			rs = prep.getGeneratedKeys();

			if (rs.next()) {

				id = rs.getInt(1);

			}

			return id;

		} catch (Exception e) {

			System.err.println("Exception : " + e.getMessage());

		} finally {

			Utility.closeOperations(connection, prep);

		}
		return 0;
	}

	@Override
	public boolean remove(int id) {
		Connection connection = null;
		PreparedStatement prep = null;
		boolean isRemoved = false;
		try {

			connection = ConncetionSupplier.getConnection();
			prep = connection.prepareStatement(DELETE);
			prep.setInt(1, id);

			if (prep.executeUpdate() > 0) {

				isRemoved = true;

			}

		} catch (Exception e) {
			System.err.println("Exception : " + e.getMessage());

		}

		finally {

			Utility.closeOperations(connection, prep);

		}
		return isRemoved;

	}

	@Override
	public CheckListItem searchItem(int id) {
		Connection connection = null;
		PreparedStatement prep = null;
		CheckListItem item = null;
		ResultSet rs = null;

		try {
			connection = ConncetionSupplier.getConnection();
			prep = connection.prepareStatement(SELECTBYID);
			prep.setInt(1, id);
			rs = prep.executeQuery();
			if (rs.next()) {
				item = new CheckListItem(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"));

			}
		} catch (Exception e) {
			System.err.println("Exception : " + e.getMessage());

		}

		finally {
			Utility.closeOperations(connection, prep, rs);
		}
		return item;

	}

	@Override
	public List<CheckListItem> searchItem(String name) {
		Connection connection = null;
		PreparedStatement prep = null;
		List<CheckListItem> itemList = null;
		CheckListItem item = null;
		ResultSet rs = null;

		try {
			connection = ConncetionSupplier.getConnection();
			prep = connection.prepareStatement(SELECTBYNAME);
			prep.setString(1, name.trim().toLowerCase());
			rs = prep.executeQuery();
			itemList = new ArrayList<CheckListItem>();
			while (rs.next()) {
				item = new CheckListItem(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"));
				itemList.add(item);

			}
		} catch (Exception e) {
			System.err.println("Exception : " + e.getMessage());

		}

		finally {
			Utility.closeOperations(connection, prep, rs);
		}
		return itemList;

	}

	@Override
	public boolean update(CheckListItem item) {
		Connection connection = null;
		PreparedStatement prep = null;
		boolean isUpdated = false;
		try {
			connection = ConncetionSupplier.getConnection();
			if (item.getName() != null) {
				prep = connection.prepareStatement(UPDATENAME);
				prep.setString(1, item.getName().toLowerCase());
				prep.setInt(2, item.getId());
			} else {
				prep = connection.prepareStatement(UPDATEQUANTITY);
				prep.setInt(1, item.getQuantity());
				prep.setInt(2, item.getId());
			}
			if (prep.executeUpdate() > 0) {
				isUpdated = true;
			}
		} catch (Exception e) {
			System.err.println("Exception : " + e.getMessage());
		}
		finally {
			Utility.closeOperations(connection, prep);
		}
		return isUpdated;
	}
}

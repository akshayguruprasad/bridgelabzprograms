package com.application.addressbook.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.application.addressbook.dao.DaoAddressBook;
import com.application.addressbook.db.entity.AddressBookEntity;
import com.application.addressbook.entities.AddressBook;
import com.application.addressbook.util.Utility;

public class DaoAddressBookImp implements DaoAddressBook {

	@Override
	public int insertAddressBook(Connection connection, AddressBook addressBook) {
		PreparedStatement ps = null;
		Integer id = 0;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(CREATEADDRESSBOOK, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, addressBook.getAddressBookName());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {

				id = rs.getInt(1);

			}

		} catch (Exception e) {

			System.err.println("Exception [DaoAddressBookImp][selectAddressBook] : " + e.getMessage());

		} finally {
			Utility.closeResources(ps, rs);
		}
		return id;
	}

	@Override
	public int updateAddressBokk(Connection connection, String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAddressBook(Connection connection, String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int isDeleted = 0;
		try {
			ps = connection.prepareStatement(DELETE_ADDRESS_BOOK);

			ps.setString(1, name);

			isDeleted = ps.executeUpdate();

		} catch (Exception e) {

			System.err.println("Exception [DaoAddressBookImp][deleteAddressBook] : " + e.getMessage());

		} finally {
			Utility.closeResources(ps, rs);
		}
		return isDeleted;
	}

	@Override
	public AddressBookEntity selectAddressBook(Connection connection, String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		AddressBookEntity entity = null;
		try {
			ps = connection.prepareStatement(SELECTADDRSSSBOOK);

			ps.setString(1, name);

			rs = ps.executeQuery();

			if (rs.next()) {
System.out.println("there is some object ");
				entity = new AddressBookEntity();

				entity.setAddressBookName(rs.getString(2));

				entity.setId(rs.getInt(1));

			}

		} catch (Exception e) {

			System.err.println("Exception [DaoAddressBookImp][selectAddressBook] : " + e.getMessage());

		} finally {
			Utility.closeResources(ps, rs);
		}
		return entity;
	}

	@Override
	public List<AddressBookEntity> selectAllAddressbook(Connection connection) {
		List<AddressBookEntity> listAddressBooks = null;
		Statement ps = null;
		ResultSet rs = null;
		AddressBookEntity entity = null;
		try {
			ps = connection.createStatement();
			rs = ps.executeQuery(SELECT_ALL_ADDRESS_BOOKS);
			listAddressBooks = new ArrayList<AddressBookEntity>();
			while (rs.next()) {

				entity = new AddressBookEntity();

				entity.setAddressBookName(rs.getString(2));

				entity.setId(rs.getInt(1));

				listAddressBooks.add(entity);

			}

		} catch (Exception e) {

			System.err.println("Exception [DaoAddressBookImp][selectAllAddressbook] : " + e.getMessage());

		} finally {
			Utility.closeResources(ps, rs);
		}
		return listAddressBooks;

	}

}

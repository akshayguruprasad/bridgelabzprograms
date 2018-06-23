package com.application.addressbook.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.application.addressbook.dao.DaoAddressMapper;
import com.application.addressbook.util.Utility;

public class DaoAddressMapperImpl implements DaoAddressMapper {

	@Override
	public int insertAddressBookMapper(Connection connection, int cid, int addressBookId) {
		PreparedStatement ps = null;
		int insertedId = 0;
		try {
			ps = connection.prepareStatement(INSERT_CONTACT);

			ps.setInt(1, addressBookId);
			ps.setInt(2, cid);// CONTACT ID
			insertedId = ps.executeUpdate();

		} catch (Exception e) {

			System.err.println("Exception [DaoAddressMapperImpl][selectAddressBook] : " + e.getMessage());

		} finally {
			Utility.closeResources(ps);
		}
		return insertedId;

	}

	@Override
	public int deleteAddressBookMapper(Connection connection, int addressBookId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteContact(Connection connection, int id) {
		PreparedStatement ps = null;
		int isDeleted = 0;
		try {
			ps = connection.prepareStatement(DELETE_CONTACT_BY_ID);

			ps.setInt(1, id);

			isDeleted = ps.executeUpdate();

		} catch (Exception e) {

			System.err.println("Exception [DaoAddressMapperImpl][selectAddressBook] : " + e.getMessage());

		} finally {
			Utility.closeResources(ps);
		}
		return isDeleted;

	}

	@Override
	public List<Integer> selectAllContact(Connection connection, int id) {
		PreparedStatement ps = null;
		List<Integer> ids = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(SELECTCONTACTSIDS);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			ids = new ArrayList<Integer>();
			while (rs.next()) {
				ids.add(rs.getInt(2));
			}

		} catch (Exception e) {

			System.err.println("Exception [DaoAddressMapperImpl][selectAddressBook] : " + e.getMessage());

		} finally {
			Utility.closeResources(ps, rs);
		}
		return ids;
	}

}

package com.application.addressbook.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.application.addressbook.dao.DaoAddress;
import com.application.addressbook.db.entity.AddressEntity;
import com.application.addressbook.entities.Address;
import com.application.addressbook.util.Utility;

public class DaoAddressImpl implements DaoAddress {

	@Override
	public int insertAddress(Connection connection, Address address) {
		int generatedId = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(INSERT_ADDRESS_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, address.getCity());
			ps.setString(2, address.getState());
			ps.setString(3, address.getAddressLocation());
			ps.setInt(4, Integer.parseInt(address.getZipCode().trim()));
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {

				generatedId = rs.getInt(1);

			}
		} catch (Exception e) {

			System.err.println("Exception [DaoAddressImpl][insertAddress] : " + e.getMessage());

		} finally {
			Utility.closeResources(ps);
		}
		return generatedId;

	}

	@Override
	public int updateAddress(Connection connection, Address address, int id) {
		// UPDATE ADDRESS SET CITY=?,STATE=?,LOCATION=?,ZIP=? WHERE ID=?
		int isUpdated = 0;
		PreparedStatement ps = null;
		System.out.println(
				"+=======================================================================================================+");

		System.out.println(address);
		System.out.println(id);
		System.out.println(
				"+=======================================================================================================+");

		try {
			ps = connection.prepareStatement(UPDATE_ADDRESS);
			ps.setString(1, address.getCity());
			ps.setString(2, address.getState());
			ps.setString(3, address.getAddressLocation());
			ps.setInt(4, Integer.parseInt(address.getZipCode().trim()));

			ps.setInt(5, id);

			isUpdated = ps.executeUpdate();

		} catch (Exception e) {

			System.err.println("Exception [DaoAddressImpl][updateAddress] : " + e.getMessage());

		} finally {
			Utility.closeResources(ps);
		}
		return isUpdated;

	}

	@Override
	public int deleteAddress(Connection connection, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AddressEntity selectAddress(Connection connection, int id) {

		PreparedStatement ps = null;
		AddressEntity address = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(SELECT_ADDRESS);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				int aId = rs.getInt(1);
				String city = rs.getString(2);
				String state = rs.getString(3);
				String location = rs.getString(4);
				int zipCode = rs.getInt(5);

				address = new AddressEntity();

				address.setId(aId);
				address.setCity(city);
				address.setState(state);
				address.setLocation(location);
				address.setZip(String.valueOf(zipCode));

			}

		} catch (Exception e) {

			System.err.println("Exception [DaoAddressImpl][selectAddress] : " + e.getMessage());

		} finally {
			Utility.closeResources(ps, rs);
		}
		return address;

	}
}

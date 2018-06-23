package com.application.addressbook.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.application.addressbook.dao.DaoContact;
import com.application.addressbook.db.entity.PersonEntity;
import com.application.addressbook.entities.Person;
import com.application.addressbook.util.Utility;

public class DaoContactImpl implements DaoContact {

	@Override
	public int insertContact(Connection connection, Person contact, int index) {

		PreparedStatement ps = null;
		int isInserted = 0;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(INSERT_BY_ADDRESSID, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, contact.getFirstName());
			ps.setString(2, contact.getLastName());
			ps.setString(3, contact.getMobileNumber());
			ps.setInt(4, index);

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();

			if (rs.next()) {

				isInserted = rs.getInt(1);

			}
		} catch (Exception e) {

			System.err.println("Exception [DaoContactImpl][updateContact] : " + e.getMessage());

		} finally {
			Utility.closeResources(ps, rs);
		}
		return isInserted;

	}

	@Override
	public int updateContact(Connection connection, Person contact, int id) {

		PreparedStatement ps = null;
		int isUpdated = 0;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(UPDATE_CONTACT_BY_ID);

			ps.setString(1, contact.getMobileNumber());
			ps.setInt(2, id);

			isUpdated = ps.executeUpdate();

		} catch (Exception e) {

			System.err.println("Exception [DaoContactImpl][updateContact] : " + e.getMessage());

		} finally {
			Utility.closeResources(ps, rs);
		}
		return isUpdated;

	}

	@Override
	public List<PersonEntity> selectAllContacts(Connection connection, List<Integer> validIds) {

		PreparedStatement ps = null;
		List<PersonEntity> personList = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(SELECTCONTACT);
			personList = new ArrayList<PersonEntity>();
			for (Integer integer : validIds) {
				ps.setInt(1, integer);
				rs = ps.executeQuery();

				if (rs.next()) {
					int id = rs.getInt(1);
					String firstName = rs.getString(2);
					String lastname = rs.getString(3);
					String mobile = rs.getString(4);
					int addressId = rs.getInt(5);
					PersonEntity person = new PersonEntity();
					person.setId(id);
					person.setContactFirstName(firstName);
					person.setContactLastName(lastname);
					person.setMobile(mobile);
					person.setAddressId(addressId);
					personList.add(person);
				}
			}
		} catch (Exception e) {

			System.err.println("Exception [DaoContactImpl][selectAllContacts] : " + e.getMessage());

		} finally {
			Utility.closeResources(ps, rs);
		}
		return personList;

	}

}

package com.application.addressbook.dependencyinjector.abstractimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.application.addressbook.dao.DaoAddress;
import com.application.addressbook.dao.DaoAddressBook;
import com.application.addressbook.dao.DaoAddressMapper;
import com.application.addressbook.dao.DaoContact;
import com.application.addressbook.daoImpl.DaoAddressBookImp;
import com.application.addressbook.daoImpl.DaoAddressImpl;
import com.application.addressbook.daoImpl.DaoAddressMapperImpl;
import com.application.addressbook.daoImpl.DaoContactImpl;
import com.application.addressbook.db.entity.AddressBookEntity;
import com.application.addressbook.db.entity.AddressEntity;
import com.application.addressbook.db.entity.PersonEntity;
import com.application.addressbook.dependencyinjector.abstractclass.FactoryStreamSelector;
import com.application.addressbook.entities.Address;
import com.application.addressbook.entities.AddressBook;
import com.application.addressbook.entities.Person;
import com.application.addressbook.util.Utility;

public class JDBCImpl extends FactoryStreamSelector {
	String DBURL = "jdbc:mysql://localhost:3306/addressbook?useSSL=false";
	String PASSWORD = "password";
	String USER = "akshay";
	String CLASS = "com.mysql.cj.jdbc.Driver";

	@Override
	public void writeData(AddressBook book) {
		Connection connection = null;
		String addressBookName = null;
		DaoAddressBook addressBookdao = null;
		DaoContact contactdao = null;
		DaoAddress addressdao = null;
		AddressBookEntity bdAddressBook = null;
		DaoAddressMapper addressMapperdao = null;
		AddressEntity addressEntity = null;
		try {
			connection = getConnection();

			addressBookName = book.getAddressBookName();
			addressBookdao = new DaoAddressBookImp();
			bdAddressBook = addressBookdao.selectAddressBook(connection, addressBookName);
			if (bdAddressBook == null) {
				int addressbookId = addressBookdao.insertAddressBook(connection, book);
				// SAVE THE ADDRESS BOOK AS NEW ENTRY
				// GET THE GENERATED ID
				bdAddressBook = new AddressBookEntity();
				bdAddressBook.setId(addressbookId);

			}

			if (book.getContactList() == null || book.getContactList().size() == 0) {
				connection.commit();
				return;
			} // EMPTY LIST FOUND DONT INSERT

			// ELSE THE CONTACT LIST IS VALID
			System.out.println(book.getContactList());
			contactdao = new DaoContactImpl();
			addressMapperdao = new DaoAddressMapperImpl();
			List<Integer> validIds = addressMapperdao.selectAllContact(connection, bdAddressBook.getId());
			List<PersonEntity> personEntityList = contactdao.selectAllContacts(connection, validIds);
			addressdao = new DaoAddressImpl();
			for (PersonEntity personEntity : personEntityList) {
				addressEntity = addressdao.selectAddress(connection, personEntity.getAddressId());
				personEntity.setAddress(addressEntity);
			}
			List<PersonEntity> bookContacts = Utility.getPersonEntityList(book.getContactList());
			List<PersonEntity> deleteContacts = new ArrayList<PersonEntity>(personEntityList);

			// DELETE CONTACTS
			deleteContacts.removeAll(bookContacts);
			for (PersonEntity personEntity : deleteContacts) {

				// PERFORM DELETE ON MAPPER
				int deletedContact = addressMapperdao.deleteContact(connection, personEntity.getId());

				if (deletedContact < 1) {
					throw new Exception("Failed to delete the contact");
				}
			}

			System.out.println("The value of it is " + bookContacts);
			List<PersonEntity> updateContact = new ArrayList<PersonEntity>(personEntityList);
			System.out.println("before " + updateContact);
			updateContact.retainAll(bookContacts);
			System.out.println("After filteriong for updates " + updateContact);
			for (PersonEntity personEntity : updateContact) {
				// UPDATE THE CONTACTS
				Address address = Utility.personEntityToAddress(personEntity);
				System.out.println("Updating value : " + personEntity);
				int updatedAddress = addressdao.updateAddress(connection, address, personEntity.getAddressId());
				if (updatedAddress < 1) {

					throw new Exception("Failed to update the address");

				}
				int updateContactValue = contactdao.updateContact(connection,
						Utility.personEntityToPerson(personEntity), personEntity.getId());

				if (updateContactValue < 1) {
					throw new Exception("Failed to update the contact");

				}
			}

			// NEW CONTACTS ADD
			bookContacts.removeAll(personEntityList);
			addressdao = new DaoAddressImpl();
			for (PersonEntity personEntity : bookContacts) {
				Person person = Utility.personEntityToPerson(personEntity);
				int addressId = addressdao.insertAddress(connection, person.getAddress());
				if (addressId < 1) {
					throw new Exception("Failed to insert the address");

				}
				int contactId = contactdao.insertContact(connection, person, addressId);
				if (contactId < 1) {

					throw new Exception("Failed to insert the contact");

				}
				System.out.println("address book id : " + bdAddressBook.getId());
				System.out.println("contact " + contactId);
				int mapperId = addressMapperdao.insertAddressBookMapper(connection, contactId, bdAddressBook.getId());
				System.out.println(mapperId + " the value of mapper value !!");
				if (mapperId < 1) {
					throw new Exception("Failed to insert the mapper details");
				}
			}
			System.out.println("Saved success");
			connection.commit();

		} catch (Exception e) {
			System.err.println("Exception occures [JDBCImpl][writeData] :: " + e.getMessage());
			try {
				connection.rollback();
			} catch (Exception e1) {
				System.err.println("Exception occures [JDBCImpl][rollback] :: " + e.getMessage());
			}
		} finally {

			Utility.closeResources(connection);

		}

	}

	@Override
	public AddressBook readData(String addressBookName) {
		AddressBook addressBook = null;
		Connection connection = null;
		DaoAddressBook addressBookdao = null;
		DaoContact contactdao = null;
		DaoAddress addressdao = null;
		AddressBookEntity bdAddressBook = null;
		DaoAddressMapper addressMapperdao = null;
		AddressEntity addressEntity = null;
		List<Integer> validIds = null;
		try {
			connection = getConnection();

			addressBookdao = new DaoAddressBookImp();

			bdAddressBook = addressBookdao.selectAddressBook(connection, addressBookName.trim());
			if (bdAddressBook == null) {
				System.out.println("Invalid address book name entered ");
				return null;
			}
			addressBook = new AddressBook();
			addressBook.setAddressBookName(bdAddressBook.getAddressBookName());
			addressMapperdao = new DaoAddressMapperImpl();
			validIds = addressMapperdao.selectAllContact(connection, bdAddressBook.getId());
			contactdao = new DaoContactImpl();
			List<PersonEntity> personEntityList = contactdao.selectAllContacts(connection, validIds);
			addressdao = new DaoAddressImpl();
			for (PersonEntity personEntity : personEntityList) {
				addressEntity = addressdao.selectAddress(connection, personEntity.getAddressId());
				personEntity.setAddress(addressEntity);
			}
			addressBook.setContactList(Utility.personEntityToPersonList(personEntityList));
		} catch (Exception e) {

			System.err.println("Exception occured [JDBCImpl][readData] : " + e.getMessage());
		} finally {

			Utility.closeResources(connection);

		}
		return addressBook;

	}

	@Override
	public boolean isAddressBookNamePresent(String addressBookName) {
		Connection connection = null;
		DaoAddressBook addressBookdao = null;
		AddressBookEntity bdAddressBook = null;
		try {
			connection = getConnection();

			System.out.println("Connection success");
			addressBookdao = new DaoAddressBookImp();
			bdAddressBook = addressBookdao.selectAddressBook(connection, addressBookName);
			System.out.println("selected adddress book");
			if (bdAddressBook != null) {
				return true;
			}
			System.out.println("Invalid address book name entered ");

		} catch (Exception e) {
			System.err.println("Exception occured [JDBCImpl][isAddressBookNamePresent] : " + e.getMessage());
		} finally {

			Utility.closeResources(connection);

		}
		return false;
	}

	@Override
	public String[] getListAddressBook() {
		Connection connection = null;
		DaoAddressBook addressBookdao = null;
		String[] holder = {};
		try {
			connection = getConnection();

			System.out.println("Connection success");
			addressBookdao = new DaoAddressBookImp();
			List<AddressBookEntity> bdAddressBook = addressBookdao.selectAllAddressbook(connection);
			System.out.println("selected adddress book");
			if (bdAddressBook != null) {

				List<String> addressBookNames = bdAddressBook.stream().map(p -> p.getAddressBookName())
						.collect(Collectors.toList());

				return addressBookNames.toArray(holder);
			}
			System.out.println("no address books available");

		} catch (Exception e) {
			System.err.println("Exception occured [JDBCImpl][getListAddressBook] : " + e.getMessage());
		} finally {

			Utility.closeResources(connection);

		}
		return null;
	}

	@Override
	public boolean deleteAddressBook(String bookName) {
		Connection connection = null;
		DaoAddressBook addressBookdao = null;

		int isDeleted = 0;
		try {
			connection = getConnection();
			System.out.println("Connection success");
			addressBookdao = new DaoAddressBookImp();
			isDeleted = addressBookdao.deleteAddressBook(connection, bookName);
			if (isDeleted < 1) {
				throw new Exception("Failed to delete the address book");
			}
			return true;
		} catch (Exception e) {
			System.err.println("Exception occured [JDBCImpl][deleteAddressBook] : " + e.getMessage());
		} finally {

			Utility.closeResources(connection);

		}
		return false;
	}

	private Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(CLASS);
			connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			System.err.println("Exception occured [Connection][static] : " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Exception occured [Connection][static] : " + e.getMessage());
		}

		return connection;
	}

}

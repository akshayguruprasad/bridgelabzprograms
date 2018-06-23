package com.application.addressbook.util;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.application.addressbook.db.entity.AddressEntity;
import com.application.addressbook.db.entity.PersonEntity;
import com.application.addressbook.entities.Address;
import com.application.addressbook.entities.AddressBook;
import com.application.addressbook.entities.Person;
import com.application.addressbook.interfaces.VariableHolder;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 05-Jun-2018
 */

public class Utility implements VariableHolder{
	private static final Scanner scanner = new Scanner(System.in);

	/**
	 * @param person
	 * @return
	 * 
	 */
	public static Person matchPerson(Person person, List<Person> contactList) {

		Iterator<Person> iterate = null;
		iterate = contactList.iterator();
		while (iterate.hasNext()) {
			Person temp = iterate.next();

			if ((temp.getFirstName().compareTo(person.getFirstName())) == 0) {

				if ((temp.getLastName().compareTo(person.getLastName())) == 0) {
					System.out.println(temp);
					return temp;
				}
			}
		}
		return null;
	}

	/**
	 * @param number
	 * @param contactList
	 * @return
	 * 
	 */
	public static Person matchNumber(String number, List<Person> contactList) {
		Iterator<Person> iterate = null;
		iterate = contactList.iterator();
		while (iterate.hasNext()) {
			Person temp = iterate.next();

			if ((temp.getMobileNumber().compareTo(number)) == 0) {

				return temp;

			}

		}
		return null;
	}

	/**
	 * @return
	 * 
	 */
	public static Character getUserCharacter() {
		String input = scanner.nextLine();

		return input.charAt(0);
	}

	/**
	 * @return
	 * 
	 */
	public static String getUserString() {
		return scanner.nextLine();
	}

	/**
	 * @param bookName
	 * @return
	 * 
	 */
	public static Boolean fileAlreadyExists(String bookName) {
		File file = new File(FILEPATH + PATHSEPERATOR + bookName);
		if (!file.exists()) {
			return false;
		}
		return true;

	}

	/**
	 * @param object
	 * 
	 */
	public static void displayAllFiles(File[] object) {
		System.out.println("Address Books are");
		System.out.println("--------------------------------");
		for (File file : object) {

			System.out.println(file.getName().split(EXT)[0]);

		}
		System.out.println("--------------------------------");

	}

	public static void displayAddressBooks(String[] names) {

		for (String string : names) {

			System.out.println(string);

		}

	}

	/**
	 * @param writer
	 * @throws IOException
	 * 
	 */
	public static <T extends Serializable> void writeFile(ObjectOutputStream writer, T object) throws IOException {
		writer.writeObject(object);
	}

	/**
	 * @param reader
	 * @param class1
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 */
	public static AddressBook readFile(ObjectInputStream reader) throws ClassNotFoundException, IOException {

		return (AddressBook) reader.readObject();
	}

	/**
	 * @return
	 * 
	 */
	public static int getUserInteger() {
		int data = scanner.nextInt();
		scanner.nextLine();
		return data;

	}

	public static Person getPerson(boolean insert) {

		String firstName = null, lastName = null, phone, address, city, state, zipCode;
		if (insert) {
			Utility.printMessages("enter first name");
			firstName = Utility.getUserString().trim();
			Utility.printMessages("enter last name");
			lastName = Utility.getUserString().trim();

		}

		Utility.printMessages("enter phone number");
		phone = Utility.getUserString().trim();
		Utility.printMessages("enter Address city");
		city = Utility.getUserString().trim();
		Utility.printMessages("enter Address state");
		state = Utility.getUserString().trim();
		Utility.printMessages("enter Address zip code");
		zipCode = Utility.getUserString().trim();
		Utility.printMessages("enter Address ");
		address = Utility.getUserString().trim();

		Address personAddress = new Address(address, city, state, zipCode);
		Person person = new Person(firstName, lastName, personAddress, phone);

		return person;

	}

	/**
	 * @param string
	 * 
	 */
	public static void printMessages(String string) {
		System.out.println(string);
	}

	/**
	 * @param mapper
	 * @param addressBookLocation
	 * 
	 */
	public static void write(ObjectMapper mapper, File addressBookLocation, AddressBook book) {
		try {
			mapper.writeValue(addressBookLocation, book);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static AddressBook read(ObjectMapper mapper, File addressBookLocation) {
		try {
			return mapper.readValue(addressBookLocation, AddressBook.class);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static PersonEntity personToPersonEntity(Person person) {

		PersonEntity personEntity = new PersonEntity();
		AddressEntity addressEntity = new AddressEntity();

		Address address = person.getAddress();
		addressEntity.setCity(address.getCity());
		addressEntity.setZip(address.getZipCode());
		addressEntity.setState(address.getState());
		addressEntity.setLocation(address.getAddressLocation());
		personEntity.setAddress(addressEntity);
		personEntity.setContactFirstName(person.getFirstName());
		personEntity.setContactLastName(person.getLastName());
		personEntity.setMobile(person.getMobileNumber());

		return personEntity;

	}

	public static List<PersonEntity> getPersonEntityList(List<Person> personList) {

		List<PersonEntity> personEntityList = new ArrayList<PersonEntity>();

		for (Person person : personList) {

			personEntityList.add(personToPersonEntity(person));

		}
		return personEntityList;
	}

	public static Person personEntityToPerson(PersonEntity personEntity) {
		Person person = new Person();
		Address address = new Address();
		AddressEntity addressEntity = personEntity.getAddress();
		address.setAddressLocation(addressEntity.getLocation());
		address.setCity(addressEntity.getCity());
		address.setState(addressEntity.getState());

		address.setZipCode(addressEntity.getZip());

		person.setFirstName(personEntity.getContactFirstName());
		person.setLastName(personEntity.getContactLastName());
		person.setMobileNumber(personEntity.getMobile());
		person.setAddress(address);

		return person;

	}

	public static Address personEntityToAddress(PersonEntity personEntity) {
		AddressEntity addressEntity = personEntity.getAddress();
		Address address = new Address();
		address.setAddressLocation(addressEntity.getLocation());
		address.setCity(addressEntity.getCity());
		address.setZipCode(addressEntity.getZip());
		address.setState(addressEntity.getState());
		return address;
	}

	public static void closeResources(Statement statement, ResultSet... resultSet) {

		for (ResultSet resultSet2 : resultSet) {

			if (resultSet2 != null) {
				try {
					resultSet2.close();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static List<Person> personEntityListToPersonList(List<PersonEntity> personEntityList) {
		List<Person> listPersons = new ArrayList<Person>();
		for (PersonEntity personEntity : personEntityList) {
			listPersons.add(personEntityToPerson(personEntity));
		}
		return listPersons;
	}

	public static void closeResources(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public static List<PersonEntity> mapAllPersonValues(List<PersonEntity> updateContact,
			List<PersonEntity> bookContacts) {

		for (int i = 0; i < updateContact.size(); i++) {

			updateContact.get(i).setAddress(bookContacts.get(i).getAddress());

			updateContact.get(i).setMobile(bookContacts.get(i).getMobile());

		}

		return updateContact;
	}

}

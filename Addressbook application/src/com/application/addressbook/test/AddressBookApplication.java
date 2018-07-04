package com.application.addressbook.test;


import java.io.FileNotFoundException;
import java.io.IOException;

import com.application.addressbook.dependencyinjector.abstractimpl.JDBCImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.codehaus.jackson.map.ser.std.StdJdkSerializers.ClassSerializer;

import com.application.addressbook.annotations.ServiceImpl;
import com.application.addressbook.annotations.Wired;

import com.application.addressbook.entities.AddressBook;
import com.application.addressbook.interfaces.ManagerInterface;
import com.application.addressbook.interfaces.VariableHolder;
import com.application.addressbook.manager.BookManager;
import com.application.addressbook.util.Utility;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 05-Jun-2018
 */

public class AddressBookApplication implements VariableHolder {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * 
	 */
	static ManagerInterface manager;



	static {

		try {

			Map<String, Class<?>> mapper = new HashMap<String, Class<?>>();

			for (Class<?> clazz : AddressBookApplication.findClasses(

					"com.application.addressbook.dependencyinjector.abstractimpl")) {

				ServiceImpl[] value = clazz.getAnnotationsByType(ServiceImpl.class);

				mapper.put(value[0].implementedValue(), clazz);

				String operationValue = null;

				Field field = BookManager.class.getField("factory");
				Wired primaryAnnotation = field.getAnnotation(Wired.class);

				operationValue = primaryAnnotation.value();

				for (int i = 0; i < value.length; i++) {

					if (value[i].implementedValue().equalsIgnoreCase(operationValue)) {

						if (manager == null) {
							manager = new BookManager();
						}
						Class<?> x1 = mapper.get(value[i].implementedValue());

						field.set(manager, x1.newInstance());

					}

				}

			}
		} catch (Exception e) {

			String data = e.getMessage();
			System.out.println(data);
			e.printStackTrace();
		}

	}

	public static void main(String... args) throws FileNotFoundException, IOException, ClassNotFoundException {

		System.out.println("---------------------------------------");
		System.out.println("  WELCOME PAGE   ");
		System.out.println("---------------------------------------");
		System.out.println("1.Manager mode");
		System.out.println("2.User mode");

		int choice = Utility.getUserInteger();
		if (args.length > 0) {
			choice = Integer.parseInt(args[0]);
		}
		switch (choice) {
		case 1:
			dashBoard(manager);
			break;
		default:
			if (manager.getBook() == null) {
				System.out.println("Create book first");
				main();
				return;
			}
			dashBoard(manager.getBook());
			break;
		}
	}


	private static List<Class<?>> findClasses(String string) {
		List<Class<?>> allClasses = null;
		try {

			allClasses = new ArrayList<Class<?>>();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();

			String path = string.replace(".", "/");
			URL x = loader.getResource(path);

			try {
				File file = new File(x.toURI());

				for (File f : file.listFiles()) {

					Class<?> classObject = Class.forName(string+"."+f.getName().substring(0, f.getName().length() - 6));
					allClasses.add(classObject);
				}
			} catch (URISyntaxException e) {
				throw new Exception(e);
			} catch (ClassNotFoundException e) {
				throw new Exception(e);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return allClasses;
	}


	/**
	 * @param worker
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * 
	 */
	@SuppressWarnings("static-access")
	private static void dashBoard(Object worker) throws FileNotFoundException, IOException, ClassNotFoundException {

		if (worker instanceof BookManager) {
			BookManager manager = (BookManager) worker;
			System.out.println("manager mode set");
			while (true) {

				System.out.println("_______________________________________");
				System.out.println("1.create new address book");
				System.out.println("2.list all address books");
				System.out.println("3.delete address  book");
				System.out.println("4.Save address books");
				System.out.println("5.Read address books");
				System.out.println("6.close address book withou saving");
				System.out.println("7.main menu");
				System.out.println("_______________________________________");
				int managerChoice = Utility.getUserInteger();
				switch (managerChoice) {
				case 1:
					manager.createNewAddressBook();
					System.out.println("Created a new address book");
					break;
				case 2:
					manager.listAllAddressBook();
					break;
				case 3:
					System.out.println("Enter the address book name to delete");
					String bookName = Utility.getUserString();
					manager.deleteAddressBook(bookName);
					break;
				case 4:

					System.out.println("printing the contenrts for book");
					System.out.println(manager.getBook());
					manager.saveAddressBook(manager.getBook());
					break;
				case 5:
					System.out.println("Enter the address book name to read");
					bookName = Utility.getUserString();
					manager.readAddressbook(bookName);
					break;
				case 6:
					manager.closeAllAddressBooks();
					System.out.println("Closed success");
					break;
				default:
					AddressBookApplication.manager.getManager().setBook(manager.getBook());
					main();
					break;
				}

			}

		} else {

			AddressBook user = (AddressBook) worker;
			System.out.println("You are in address book : " + user.getAddressBookName());
			while (true) {
				System.out.println("_______________________________________");
				System.out.println("1.Add person");
				System.out.println("2.remove person");
				System.out.println("3.edit person");
				System.out.println("4.display person taking number");
				System.out.println("5.display person taking fn and ln");
				System.out.println("6.sort the address by zip code");
				System.out.println("7.sort the address by name");
				System.out.println("8.display all persons");
				System.out.println("9.main menu");
				System.out.println("_______________________________________");
				int userChoice = Utility.getUserInteger();
				switch (userChoice) {
				case 1:
					user.addPerson(Utility.getPerson(true));
					System.out.println("Person added success");
					break;
				case 2:
					Utility.printMessages("Phone number");
					user.deletePerson(Utility.getUserString());
					break;

				case 3:
					System.out.println("Enter the index of the person in address book");
					int index = Utility.getUserInteger();
					user.editPersonFromAddressBook(index - 1);
					break;
				case 4:
					Utility.printMessages("Phone number");
					user.displayPerson(Utility.getUserString());
					break;
				case 5:
					Utility.printMessages("First name and last name ");
					user.displayPerson(Utility.getUserString(), Utility.getUserString());
					break;
				case 6:
					user.sortByZipCode();
					break;
				case 7:
					user.sortByName();
					break;
				case 8:
					user.displayAllPersons();
					break;
				default:
					main();
					break;
				}
				// PERFORM ALL SAVE OPERATIONS AFTER THE USER MODE
				if (user.getIsChangedSinceLastSave()) {
					if (user.getAddressBookName().equalsIgnoreCase(UNTITLED)) {
						System.out.println("Save the Address book with a name ");
						AddressBookApplication.manager.getManager().setBook(user);
						System.out.println("redirecting to manager mode");
						main("1");
						return;
					}
					AddressBookApplication.manager.getManager().getFactory().writeData(user);
					System.out.println("Auto save complete");
					System.out.println("\n");
				}
			}

		}

	}


}

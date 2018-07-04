package com.application.addressbook.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import com.application.addressbook.annotations.Wired;
import com.application.addressbook.dependencyinjector.abstractclass.FactoryStreamSelector;
import com.application.addressbook.entities.AddressBook;
import com.application.addressbook.interfaces.ManagerInterface;
import com.application.addressbook.interfaces.VariableHolder;
import com.application.addressbook.util.Utility;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 05-Jun-2018
 */

public class BookManager implements VariableHolder, ManagerInterface {

	static AddressBook book;

	
	@Wired(value="jdbc")
	public static FactoryStreamSelector factory;


	@Override
	public String toString() {
		return book.getAddressBookName();
	}

	public static void setBook(AddressBook book) {
		BookManager.book = book;
	}

	@Override
	@SuppressWarnings("static-access")
	public void closeAllAddressBooks() {
		BookManager.book = null;
		System.gc();
	}

	@Override
	public AddressBook createNewAddressBook() throws FileNotFoundException, IOException {
		try {
			System.out.println("Enter the method create");
			if (book != null) {
				if (book.getIsChangedSinceLastSave()) {
					System.out.println("book is existing here");

					System.out.println("Address book : " + book.getAddressBookName() + " already opend save [Y|N] ?");
					Character save = Utility.getUserCharacter();
					if (save == 'Y') {
						saveAddressBook(book);
					}
				}
			}
			closeAllAddressBooks();
			book = new AddressBook();
			System.out.println(book);
			book.setIsChangedSinceLastSave(true);
			book.setAddressBookName(UNTITLED);
			System.out.println("creadted book");
			return book;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteAddressBook(String bookName) {

		boolean isDeleted = factory.deleteAddressBook(bookName);
		if (isDeleted) {

			System.out.println("Address book : " + bookName + " deleted success");
		}

	}

	@Override
	public AddressBook getBook() {

		return book;

	}

	@Override
	public BookManager getManager() {
		return this;
	}

	@Override
	public void listAllAddressBook() {
		Arrays.asList(factory.getListAddressBook()).forEach(System.out::println);

	}

	@Override
	public void readAddressbook(String bookName) {

		try {

			if (book != null) {

				if (book.getIsChangedSinceLastSave()) {

					System.out.println("Address book : " + book.getAddressBookName() + " already opend save [Y|N] ?");
					Character save = Utility.getUserCharacter();
					if (save == 'Y') {
						saveAddressBook(book);
					}
				}

				this.closeAllAddressBooks();

			}
			
			
			book = factory.readData(bookName);

			System.out.println("book list");
			System.out.println(book.getContactList());
			System.out.println("Book name");
			System.out.println(book.getAddressBookName());

			System.out.println("Address book loaded success .");
		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void saveAddressBook(AddressBook book) throws FileNotFoundException, IOException {
		if (book == null) {
			System.out.println("Create a Address Book to save");
			return;
		}

		if (book.getAddressBookName().equalsIgnoreCase(UNTITLED)) {
			System.out.println("Enter Address Book name to be saved as ");
			String bookName = Utility.getUserString();
			this.saveAsAddressBook(bookName);
		} else {
			// SAVE LOGIC GOES HERE
			factory.writeData(book);
			this.closeAllAddressBooks();
			System.out.println("Address book : " + book.getAddressBookName() + " saved success");

		}

	}

	@Override
	public void saveAsAddressBook(String bookName) throws FileNotFoundException, IOException {
		System.out.println("Enter the save address book");
		if (factory.isAddressBookNamePresent(bookName)) {
			String[] addressBookName = factory.getListAddressBook();
			Utility.displayAddressBooks(addressBookName);
			saveAddressBook(book);
			return;
		}
		System.out.println("saving address book");
		book.setAddressBookName(bookName);
		saveAddressBook(book);
	}

	@Override
	public void setFactory(FactoryStreamSelector factory) {
		BookManager.factory = factory;
	}

	@Override
	public FactoryStreamSelector getFactory() {
		return factory;
	}

}

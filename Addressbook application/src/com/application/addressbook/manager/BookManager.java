package com.application.addressbook.manager;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.codehaus.jackson.map.ObjectMapper;

import com.application.addressbook.entities.AddressBook;
import com.application.addressbook.interfaces.ManagerInterface;
import com.application.addressbook.interfaces.VariableHolder;
import com.application.addressbook.io.AddressBookIO;
import com.application.addressbook.util.Utility;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 05-Jun-2018
 */

public class BookManager implements VariableHolder, ManagerInterface {

    public static void setBook(AddressBook book) {
	BookManager.book = book;
    }

    static AddressBook book;

    @Override
    public AddressBook getBook() {

	return book;

    }

    @Override
    public BookManager getManager() {
	return this;
    }

    @Override
    public AddressBook createNewAddressBook() throws FileNotFoundException, IOException {
	if (book != null) {
	    if (book.getIsChangedSinceLastSave()) {
		System.out.println("Address book : " + book.getAddressBookName() + " already opend save [Y|N] ?");
		Character save = Utility.getUserBoolean();
		if (save == 'Y') {
		    saveAddressBook(book);

		}

	    }
	}

	closeAllAddressBooks();
	book = new AddressBook();
	book.setIsChangedSinceLastSave(true);
	File file = new File(FILEPATH + PATHSEPERATOR + UNTITLED + EXT);
	book.setAddressBookLocation(file);
	return book;
    }

    @SuppressWarnings("static-access")
    public void closeAllAddressBooks() {
	this.book = null;
    }

    @Override
    public void deleteAddressBook(String bookName) {
	File file = new File(FILEPATH + PATHSEPERATOR + bookName + EXT);
	if (!file.exists()) {
	    System.out.println("Invalid Address Book name entered ");
	    return;
	}
	file.delete();
	System.out.println("Address book : " + bookName + " deleted success");

    }

    @Override
    public void readAddressbook(String bookName) {
	try {
	    File file = new File(FILEPATH + PATHSEPERATOR + bookName + EXT);
	    if (!file.exists()) {

		System.out.println("Invalid Address Book name entered ");
		return;
	    }
	    if (book != null) {

		if (book.getIsChangedSinceLastSave()) {

		    System.out.println("Address book : " + book.getAddressBookName() + " already opend save [Y|N] ?");
		    Character save = Utility.getUserBoolean();
		    if (save == 'Y') {
			saveAddressBook(book);
		    }
		}

		this.closeAllAddressBooks();

	    }

	    ObjectMapper mapper = AddressBookIO.getObjectmapper();

	    book = Utility.read(mapper, file);
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

	if (book.getAddressBookLocation().getName().equalsIgnoreCase(UNTITLED + EXT)) {
	    System.out.println("Enter Address Book name to be saved as ");
	    String bookName = Utility.getUserString();
	    this.saveAsAddressBook(bookName);
	} else {
	    // SAVE LOGIC GOES HERE

	    ObjectMapper mapper = AddressBookIO.getObjectmapper();
	    Utility.write(mapper, book.getAddressBookLocation(), book);
	    this.closeAllAddressBooks();
	    System.out.println("Address book : " + book.getAddressBookName() + " saved success");

	}

    }

    @Override
    public void saveAsAddressBook(String bookName) throws FileNotFoundException, IOException {
	if (Utility.fileAlreadyExists(bookName)) {
	    File[] files = listAllAddressBook();
	    Utility.displayAllFiles(files);
	    saveAddressBook(book);
	    return;
	}
	File file = new File(FILEPATH + PATHSEPERATOR + bookName + EXT);
	book.setAddressBookLocation(file);
	saveAddressBook(book);
    }

    @Override
    public File[] listAllAddressBook() {

/*	FileFilter onlyAddressBooks = (File fileName) -> {
	    if (fileName.getName().endsWith(EXT)) {
		return true;
	    }
	    return false;
	};*/
	File file = new File(FILEPATH + PATHSEPERATOR);

	return file.listFiles(p->p.getName().endsWith(EXT));

    }

}

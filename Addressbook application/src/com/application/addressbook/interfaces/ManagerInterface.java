package com.application.addressbook.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.application.addressbook.dependencyinjector.abstractclass.FactoryStreamSelector;
import com.application.addressbook.entities.AddressBook;
import com.application.addressbook.manager.BookManager;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 09-Jun-2018
 */

public interface ManagerInterface {

	public AddressBook getBook();

	public AddressBook createNewAddressBook() throws FileNotFoundException, IOException;

	public void closeAllAddressBooks();

	public void listAllAddressBook();

	public void deleteAddressBook(String bookName);

	public BookManager getManager();

	/*
	 * public void saveAddressBook(FactoryStreamSelector factory); public void
	 * saveAsAddressBook(FactoryStreamSelector factory); public void
	 * readAddressBook(FactoryStreamSelector factory);
	 */
	
	
	
	public FactoryStreamSelector getFactory() ;
	
	public void readAddressbook(String bookName);

	public void saveAddressBook(AddressBook book) throws FileNotFoundException, IOException;

	public void saveAsAddressBook(String bookName) throws FileNotFoundException, IOException;

	void setFactory(FactoryStreamSelector factory);

}

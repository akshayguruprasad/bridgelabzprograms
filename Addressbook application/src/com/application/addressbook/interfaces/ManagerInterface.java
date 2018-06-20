package com.application.addressbook.interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.application.addressbook.entities.AddressBook;
import com.application.addressbook.manager.BookManager;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 09-Jun-2018
 */

public interface ManagerInterface {

    public AddressBook getBook() ;
    public AddressBook createNewAddressBook() throws FileNotFoundException, IOException ;
    public void closeAllAddressBooks();
    public void readAddressbook(String bookName);
    public void saveAddressBook(AddressBook book) throws FileNotFoundException, IOException;
    public void saveAsAddressBook(String bookName) throws FileNotFoundException, IOException;
    public File[] listAllAddressBook();
    public void deleteAddressBook(String bookName);
    public BookManager getManager();
    
}

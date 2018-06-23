package com.application.addressbook.dependencyinjector.abstractclass;

import com.application.addressbook.entities.AddressBook;

public abstract class FactoryStreamSelector {
	public abstract void writeData(AddressBook book);

	public abstract AddressBook readData(String addressBookName);

	public abstract boolean isAddressBookNamePresent(String addressBookName);

	public abstract String[] getListAddressBook();

	public abstract boolean deleteAddressBook(String bookName);
}

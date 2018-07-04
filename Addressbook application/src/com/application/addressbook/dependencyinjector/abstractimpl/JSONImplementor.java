package com.application.addressbook.dependencyinjector.abstractimpl;


import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.ObjectMapper;

import com.application.addressbook.annotations.ServiceImpl;

import com.application.addressbook.dependencyinjector.abstractclass.FactoryStreamSelector;
import com.application.addressbook.entities.AddressBook;
import com.application.addressbook.interfaces.VariableHolder;
import com.application.addressbook.io.AddressBookIO;
import com.application.addressbook.util.Utility;


@ServiceImpl(implementedValue="json")

public class JSONImplementor extends FactoryStreamSelector implements VariableHolder {

	FileFilter fileFilter = (file) -> {
		if (file.getName().endsWith(EXT)) {
			return true;
		}

		return false;
	};

	@Override
	public void writeData(AddressBook book) {
		System.out.println("Writing the address book to json file");
		ObjectMapper mapper = AddressBookIO.getObjectmapper();
		Utility.write(mapper, new File(FILEPATH + PATHSEPERATOR + book.getAddressBookName() + EXT), book);
	}

	@Override
	public AddressBook readData(String addressBookName) {
		boolean isPresent = this.isAddressBookNamePresent(addressBookName);
		if (!isPresent) {
			return null;
		}

		ObjectMapper mapper = AddressBookIO.getObjectmapper();
		File file = this.getFile(addressBookName);
		return Utility.read(mapper, file);

	}

	@Override
	public boolean isAddressBookNamePresent(String addressBookName) {
		File file = this.getFile(addressBookName);
		if (!file.exists()) {
			return false;
		}
		return true;
	}

	@Override
	public String[] getListAddressBook() {

		String[] fileNames = {};
		File file = new File(FILEPATH + PATHSEPERATOR);

		if (file.isDirectory()) {

			List<String> fileNameHolder = Arrays.asList(file.listFiles()).stream()
					.filter(p -> this.fileFilter.accept(p)).map(p -> p.getName().split(EXT)[0])
					.collect(Collectors.toList());

			fileNames = fileNameHolder.toArray(fileNames);

		}

		return fileNames;
	}

	@Override
	public boolean deleteAddressBook(String bookName) {

		File file = new File(FILEPATH + PATHSEPERATOR + bookName + EXT);
		if (!file.exists()) {
			System.out.println("Invalid Address Book name entered ");
			return false;
		}
		return file.delete();
	}

	private File getFile(String addressBookName) {

		File file = new File(FILEPATH + PATHSEPERATOR + addressBookName + EXT);
		return file;
	}

}

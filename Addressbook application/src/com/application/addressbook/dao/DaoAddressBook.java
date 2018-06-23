package com.application.addressbook.dao;

import java.sql.Connection;
import java.util.List;

import com.application.addressbook.db.entity.AddressBookEntity;
import com.application.addressbook.entities.AddressBook;

public interface DaoAddressBook {
	String SELECTADDRSSSBOOK = "select * from addressbook where name=? and  isDeleted=0";
String SELECT_ALL_ADDRESS_BOOKS="select * from addressbook where isDeleted=0";
	String CREATEADDRESSBOOK = "insert into addressbook(name) values(?) ";
String DELETE_ADDRESS_BOOK="update addressbook set isDeleted=1 where name=?";
	int insertAddressBook(Connection connection, AddressBook addressBook);

	int updateAddressBokk(Connection connection, String name);

	int deleteAddressBook(Connection connection, String name);

	AddressBookEntity selectAddressBook(Connection connection, String name);

	List<AddressBookEntity> selectAllAddressbook(Connection connection);
}

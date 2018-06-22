package com.application.addressbook.dao;

import java.sql.Connection;
import java.util.List;

public interface DaoAddressMapper {

	String SELECTCONTACTSIDS = "select * from addressMapper where abid=? and isDeleted=0";
	String INSERT_CONTACT = "insert into addressMapper(abid,cid) values(?,?)";
	String DELETE_CONTACT_BY_ID = "update  addressMapper set isDeleted=1 where cid=?";

	int insertAddressBookMapper(Connection connection, int persons, int addressBookId);

	int deleteAddressBookMapper(Connection connection, int addressBookId);

	int deleteContact(Connection connection, int id);

	List<Integer> selectAllContact(Connection connection, int id);
}

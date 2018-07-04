package com.application.addressbook.dao;

import java.sql.Connection;
import java.util.List;

import com.application.addressbook.db.entity.PersonEntity;
import com.application.addressbook.entities.Person;

public interface DaoContact {
String SELECTCONTACT="select * from contact where id=?";
String INSERT_BY_ADDRESSID="insert into contact(contactfirstname,contactlastname,mobile,addressid) values(?,?,?,?)";
String UPDATE_CONTACT_BY_ID="update contact set mobile=? where id=?";

	int insertContact(Connection connection, Person contact, int index);

	int updateContact(Connection connection, Person contact,int id);

//	int deleteContact(Connection connection, String mobile);

//	Person selectContact(Connection connection, String firstname, String lastname);

//	Map<String, Object> selectContact(Connection connection, String mobile);

//	List<PersonEntity> selectAllContacts(Connection connection, int id);

	List<PersonEntity> selectAllContacts(Connection connection, List<Integer> validIds);

}

package com.application.addressbook.dao;

import java.sql.Connection;

import com.application.addressbook.db.entity.AddressEntity;
import com.application.addressbook.entities.Address;

public interface DaoAddress {
	
	
	String SELECT_ADDRESS="select * from address where id=?";
	String UPDATE_ADDRESS="update address set city=?,state=?,location=?,zip=? where id=?";
	String INSERT_ADDRESS_BY_ID="insert into address(city,state,location,zip) values(?,?,?,?) ";
	
	int insertAddress(Connection connection, Address address);

	int updateAddress(Connection connection, Address address,int id);

	int deleteAddress(Connection connection, int id);

	AddressEntity selectAddress(Connection connection, int id);

}

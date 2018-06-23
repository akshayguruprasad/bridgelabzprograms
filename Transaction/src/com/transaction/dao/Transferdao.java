package com.transaction.dao;

import java.sql.Connection;

public interface Transferdao {

	int updateBalance(Connection connection, long number,double amount);
	public double getBalance(Connection connection, long accountNumber);
	
}

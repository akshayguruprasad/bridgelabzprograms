package com.transaction.commons;

public interface Credentials {

	String DBURL="jdbc:mysql://localhost:3306/banking?useSSL=false";
	String PASSWORD="password";
	String USER="akshay";
	String CLASS="com.mysql.cj.jdbc.Driver";
	String SEARCHSENDER="select * from bankholder where accountnumber=?";
	String DEBITSENDER="update bankholder set balance=? where accountnumber=?";
}

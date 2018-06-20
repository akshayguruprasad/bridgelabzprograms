package com.transaction.connection;

import java.sql.Connection;

public abstract class BaseConnection {

	protected Connection connectionBase;

	public BaseConnection(Connection connectionBase) {
		this.connectionBase = connectionBase;
	}


	
	
	
}

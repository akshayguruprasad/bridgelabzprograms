package com.transaction.service;

import com.transaction.commons.Observable;
import com.transaction.commons.Observer;

public interface TransferService {
boolean transact(Observable sender,Observer reciver,double amount);
	
	
	
	
}

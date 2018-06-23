package com.transaction.commons;

import com.transaction.entity.BankAccount;
import com.transaction.entity.Transaction;

public interface Observable {

	void registerObserver(Observer observer);

	void deRegisterObserver(Observer observer);


	void notifyObserver(Observer observer);

	long getAccountNumber();

	void setBank(BankAccount bank);

	void setTranscation(Transaction transcation);
	

}

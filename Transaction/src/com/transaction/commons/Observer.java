package com.transaction.commons;

import com.transaction.entity.Transaction;

public interface Observer {
	void getNotify(Transaction transaction);

	void addObservable(Observable subject);

	long getAccount();
}

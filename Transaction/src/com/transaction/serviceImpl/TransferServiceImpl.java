package com.transaction.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;

import com.transaction.commons.Observable;
import com.transaction.commons.Observer;
import com.transaction.dao.Transferdao;
import com.transaction.daoImpl.TransferdaoImpl;
import com.transaction.service.TransferService;
import com.transaction.util.Utility;

public class TransferServiceImpl implements TransferService {

	@Override
	public boolean transact(Observable sender, Observer reciver, double amount) {
		Connection connection = null;
		Transferdao daoOperations = new TransferdaoImpl();
		double balance = 0;
		int debitedValue = 0;
		try {
			connection = com.transaction.connection.Connection.getConnection();
			balance = daoOperations.getBalance(connection, sender.getAccountNumber());
			if (balance < amount) {
				throw new Exception("low balance");
			}

			debitedValue = daoOperations.updateBalance(connection, sender.getAccountNumber(), balance - amount);

			if (debitedValue < 1) {
				throw new Exception("failed to debit  from ur account");
			}

			balance = daoOperations.getBalance(connection, reciver.getAccount());

			int res = daoOperations.updateBalance(connection, reciver.getAccount(), balance + amount);

			if (res < 1) {
				throw new Exception("could not be credited");
			}

			System.out.println("transcation success");
			connection.commit();
			return true;
		} catch (Exception e) {
			System.err.println("Exception occured [TransferServiceImpl][debitAmount] : " + e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.err.println(
						"Exception occured [TransferServiceImpl][debitAmount]  failed to rollback : " + e.getMessage());

			} finally {
				Utility.freeResources(connection);

			}
			return false;
		}
	}

}

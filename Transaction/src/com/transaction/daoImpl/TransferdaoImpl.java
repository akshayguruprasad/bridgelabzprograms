package com.transaction.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.transaction.commons.Credentials;
import com.transaction.dao.Transferdao;
import com.transaction.util.Utility;

public class TransferdaoImpl implements Transferdao {

	@Override
	public int updateBalance(Connection connection, long accountNumber, double amount) {
		PreparedStatement ps = null;
		try {

			ps = connection.prepareStatement(Credentials.DEBITSENDER);
			ps.setDouble(1, amount);
			ps.setLong(2, accountNumber);
			return ps.executeUpdate();

		} catch (Exception e) {

			System.err.println("Could not perform debit");
		}finally {
			Utility.freeResources(ps);
			
		}
		return 0;
	}

	@Override
	public double getBalance(Connection connection, long accountNumber) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = connection.prepareStatement(Credentials.SEARCHSENDER);
			ps.setLong(1, accountNumber);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getDouble(3);
			}

		} catch (Exception e) {
			System.err.println("Invalid account number ");

		}
		finally {
			Utility.freeResources(ps,rs);
			
		}
		return 0;
	}

}

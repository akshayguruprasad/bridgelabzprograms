package com.transaction.entity;

import java.util.List;

public class BankAccount {
	private List<Transaction> transactionList;
	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	private String IFSC;
	private long accountNumber;
	private String branchDetails;

	public String getIFSC() {
		return IFSC;
	}

	public void setIFSC(String iFSC) {
		IFSC = iFSC;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBranchDetails() {
		return branchDetails;
	}

	public void setBranchDetails(String branchDetails) {
		this.branchDetails = branchDetails;
	}

}

package com.transaction.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.transaction.commons.Observable;
import com.transaction.commons.Observer;

public class Sender implements Observable {
	private List<Observer> allObservers;
	private String companyName;
	private String address;
	private Transaction transcation;
	private BankAccount bank;

	public Sender(String companyName, String address, Transaction transcation, BankAccount bank) {
		this.companyName = companyName;
		this.address = address;
		this.transcation = transcation;
		this.bank = bank;

	}

	public Sender(String name, String address) {
		this.companyName = name;
		this.address = address;
	}

	public BankAccount getBank() {
		return bank;
	}

	@Override
	public void setBank(BankAccount bank) {
		this.bank = bank;
	}

	public Transaction getTranscation() {
		return transcation;
	}

	@Override
	public void setTranscation(Transaction transcation) {
		this.transcation = transcation;
	}

	private Map<Observer, Long> benifactors = new HashMap<Observer, Long>();

	public Map<Observer, Long> getBenifactors() {
		return benifactors;
	}

	public void setBenifactors(Map<Observer, Long> benifactors) {
		this.benifactors = benifactors;
	}

	public List<Observer> getAllObservers() {
		return allObservers;
	}

	public void setAllObservers(List<Observer> allObservers) {
		this.allObservers = allObservers;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public void registerObserver(Observer observer) {

		if (this.allObservers == null) {
			this.allObservers = new ArrayList<Observer>();
		}

		if (!this.allObservers.contains(observer)) {

			this.allObservers.add(observer);
			this.benifactors.put(observer, observer.getAccount());

		}
	}

	@Override
	public void deRegisterObserver(Observer observer) {
		if (this.allObservers == null) {
			System.out.println("No observers found");
			return;
		}
		if (this.allObservers.contains(observer)) {
			System.out.println("Removing the observer");

			this.allObservers.remove(observer);
			this.benifactors.remove(observer);

			return;
		}
		System.out.println("No observer found");

	}



	@Override
	public void notifyObserver(Observer observer) {

		this.transcation.setSender(companyName + " from " + address);
		observer.getNotify(this.transcation);
		this.transcation = null;

	}

	

	@Override
	public long getAccountNumber() {
		return this.bank.getBankAccount();
	}

}

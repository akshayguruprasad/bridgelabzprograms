package com.transaction.main;

import java.util.Date;

import com.transaction.commons.Observable;
import com.transaction.commons.Observer;
import com.transaction.entity.BankAccount;
import com.transaction.entity.Reciever;
import com.transaction.entity.Sender;
import com.transaction.entity.Transaction;
import com.transaction.service.TransferService;
import com.transaction.serviceImpl.TransferServiceImpl;
import com.transaction.util.Utility;

public class MainClass {

	public static void main(String[] args) {

		Observable sender = new Sender("Bridgelabz", "govandi");
		TransferService transfer = new TransferServiceImpl();
		BankAccount bankSender = new BankAccount();
		System.out.println("Enter your bank account number");
		bankSender.setBankAccount(Utility.getLong());
		System.out.println("Enter the amount to transfer");
		double amount = Utility.getDouble();
		// 1020202020
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDate(new Date());
		sender.setBank(bankSender);
		sender.setTranscation(transaction);
		System.out.println("Enter the account number to send money to");
		BankAccount bankReciver = new BankAccount();
		// 1020202021
		bankReciver.setBankAccount(Utility.getLong());
		Observer reciver = new Reciever(bankReciver, "kishan", "895554114", "akshayasd@hsdjfksdk.com");
		sender.registerObserver(reciver);
		reciver.addObservable(sender);
		boolean value = transfer.transact(sender, reciver, amount);
		System.out.println(value);
		if (value) {
			sender.notifyObserver(reciver);
		}

	}

}

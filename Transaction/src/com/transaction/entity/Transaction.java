package com.transaction.entity;

import java.util.Date;

public class Transaction {
private String sender;
private double amount;
private Date date;
public String getSender() {
	return sender;
}
public void setSender(String sender) {
	this.sender = sender;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public Transaction(String sender, double amount, Date date) {
	super();
	this.sender = sender;
	this.amount = amount;
	this.date = date;
}
public Transaction() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Transaction [sender=" + sender + ", amount=" + amount + ", date=" + date + "]";
}

}

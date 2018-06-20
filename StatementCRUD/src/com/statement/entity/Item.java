package com.statement.entity;

import java.util.Date;

public class Item {

	private int id;
	private String name;
	private double price;
	private Date expDate;

	public Item(int id, String name, double price, Date expDate) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.expDate = expDate;
	}

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", expDate=" + expDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

}

package com.statement.entity;

public class CheckListItem {

	@Override
	public String toString() {
		return "CheckListItem [id=" + id + ", name=" + name + ", quantity=" + quantity + "]";
	}

	private int id;

	private String name;

	private int quantity;

	public CheckListItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckListItem(int id, String name, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}

package com.application.addressbook.db.entity;

public class AddressBookEntity {

	@Override
	public String toString() {
		return "AddressBookEntity [id=" + id + ", addressBookName=" + addressBookName + ", isDeleted=" + isDeleted
				+ "]";
	}
	int id;
	String addressBookName;
	boolean isDeleted;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddressBookName() {
		return addressBookName;
	}
	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(int isDeleted) {
		this.isDeleted = isDeleted==0?false:true;
	}
	
	
	
	
	
}

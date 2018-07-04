package com.application.addressbook.db.entity;

import com.application.addressbook.entities.Person;

public class PersonEntity  {

	@Override
	public String toString() {
		return "PersonEntity [id=" + id + ", contactFirstName=" + contactFirstName + ", contactLastName="
				+ contactLastName + ", mobile=" + mobile + ", addressId=" + addressId + ", address=" + address + "]";
	}

	int id;
	String contactFirstName;
	String contactLastName;
	String mobile;
	int addressId;
	AddressEntity address;

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	@Override
	public boolean equals(Object obj) {

		Person p = new Person();
		PersonEntity p1 = (PersonEntity) obj;
		p.setFirstName(p1.getContactFirstName());
		p.setLastName(p1.getContactLastName());
		p.setMobileNumber(p1.getMobile());

		if (this.contactFirstName.equals(p.getFirstName()) && this.contactLastName.equals(p.getLastName())
			/*	&& this.mobile.equals(p.getMobileNumber())*/) {

			return true;

		}
		return false;

	}

}

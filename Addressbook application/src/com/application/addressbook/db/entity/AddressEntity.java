package com.application.addressbook.db.entity;

public class AddressEntity {
@Override
	public String toString() {
		return "AddressEntity [city=" + city + ", state=" + state + ", zip=" + zip + ", location=" + location + ", id="
				+ id + "]";
	}
String city;
String state;
String zip;
String location;
int id;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getZip() {
	return zip;
}
public void setZip(String zip) {
	this.zip = zip;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}

	
	
	
	
}

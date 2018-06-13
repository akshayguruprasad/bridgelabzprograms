package com.bridgelabz.designpattern.prototype;

import com.bridgelabz.designpattern.util.Utility;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public class Person implements Cloneable {

    String name;
    Address address;

    
    public Person() {
	
	this.address=new Address();
	
    }
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#clone()
     */
    @Override
    protected Person clone() throws CloneNotSupportedException {
	// PERFORM HYBRID CLONING

	Person person = new Person();

	person.setName(Utility.getPersonName());

	person.setAddress(this.address);
	return person;
    }

    @Override
    public String toString() {
	return "Person [name=" + name + ", address=" + address + "]";
    }
}

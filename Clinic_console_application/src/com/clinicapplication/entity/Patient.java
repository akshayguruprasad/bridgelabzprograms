package com.clinicapplication.entity;

import java.io.Serializable;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 09-Jun-2018
 */

public class Patient implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Patient() {
    }// DEFAULT CONSTRUCTOR

    private String name;
    private int id;
    private String mobile;
    private byte age;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public byte getAge() {
	return age;
    }

    public void setAge(byte age) {
	this.age = age;
    }

    @Override
    public String toString() {

	Object[] data = { Integer.valueOf(this.id).toString(), this.name, this.mobile, this.age };
	String format = "id          : %-10s" + "\n" + "name        : %-10s" + "\n" + "mobile      : %-10s" + "\n"
		+ "age         : %-10s";
	return String.format(format, data);

    }

}

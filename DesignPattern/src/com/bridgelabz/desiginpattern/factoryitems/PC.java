package com.bridgelabz.desiginpattern.factoryitems;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public class PC extends Computer{
    protected String type;
    protected int ram;
    protected int hdd;

    public PC(String type, int ram, int hdd) {
	this.type = type;
	this.ram = ram;
	this.hdd = hdd;
    }

    @Override
    public String toString() {
	return "PC [type=" + type + ", ram=" + ram +"GB"+ ", hdd=" + hdd +"TB"+ "]";
    }

}

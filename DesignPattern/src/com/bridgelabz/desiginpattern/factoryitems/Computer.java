package com.bridgelabz.desiginpattern.factoryitems;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public abstract class Computer {

    protected String type;
    protected int ram;
    protected int hdd;
    @Override
    public String toString() {
	return "Computer [type=" + type + ", ram=" + ram + ", hdd=" + hdd + "]";
    }

}

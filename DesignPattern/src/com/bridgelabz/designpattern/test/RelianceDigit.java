package com.bridgelabz.designpattern.test;

import javax.swing.plaf.synth.SynthScrollBarUI;
import javax.swing.text.Utilities;

import com.bridgelabz.desiginpattern.factory.Factory;
import com.bridgelabz.desiginpattern.factoryitems.Computer;
import com.bridgelabz.designpattern.util.Utility;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public class RelianceDigit {

    /**
     * @param args
     * 
     */
    public static void main(String[] args) {

	System.out.println("------Welcome to relianceDigit------");

	System.out.println("Enter the type of computer");

	System.out.println("laptop,server,pc");

	String type = Utility.getUserString();

	System.out.println("great..");
	System.out.println("Enter the ram configuration now (GB)");
	int ram = Utility.getUserInt();

	System.out.println("you have chosen " + ram + " RAM");

	System.out.println("Provide the capacity of the HDD (TB)");

	int hdd = Utility.getUserInt();
	System.out.println("Your order has been placed");

	Computer computer = Factory.getComputer(type, ram, hdd);

	System.out.println("Your computer is ready");

	System.out.println(computer);

    }

}

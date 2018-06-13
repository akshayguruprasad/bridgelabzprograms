package com.bridgelabz.desiginpattern.adapter.trial;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public class Adapter  {

    public static void main(String[] args) {
	
	AdapterInterface adapter1=new EuropeanSockets();
	AdapterInterface adapter2=new AmericanSockets();
	
	System.out.println("Voltage  european"+adapter1.get120Voltage());
	System.out.println("Voltage american"+adapter2.get12Voltage());
	
	
	
    }
    
    
}

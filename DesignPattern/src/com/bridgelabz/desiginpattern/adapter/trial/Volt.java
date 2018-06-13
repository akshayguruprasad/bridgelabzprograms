package com.bridgelabz.desiginpattern.adapter.trial;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public class Volt {

    @Override
    public String toString() {
	return "Volt [voltage=" + voltage + "]";
    }

    private Integer voltage;

    public Volt() {

	this.voltage = 1000;// raw voltage producer

    }
    
    

    public Integer getVoltage() {
	return voltage;
    }

    public void setVoltage(Integer voltage) {
	this.voltage = voltage;
    }

}

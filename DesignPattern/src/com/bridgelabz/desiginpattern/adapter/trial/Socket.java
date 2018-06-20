package com.bridgelabz.desiginpattern.adapter.trial;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public abstract class Socket {

    public Volt getVoltage() {

	return new Volt();

    }
    
    
    abstract Volt voltageRegulator(Volt volt, int value);

}

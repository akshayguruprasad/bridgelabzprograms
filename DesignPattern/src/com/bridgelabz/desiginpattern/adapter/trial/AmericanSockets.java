package com.bridgelabz.desiginpattern.adapter.trial;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public class AmericanSockets extends Socket implements AdapterInterface {

    @Override
    public Volt voltageRegulator(Volt volt, int value) {
	volt.setVoltage(value);

	return volt;

    }

    /* (non-Javadoc)
     * @see com.bridgelabz.desiginpattern.adapter.trial.AdapterInterface#get120Voltage()
     */
    @Override
    public Volt get120Voltage() {
	return voltageRegulator(this.getVoltage(), 110);

    }

    /* (non-Javadoc)
     * @see com.bridgelabz.desiginpattern.adapter.trial.AdapterInterface#get12Voltage()
     */
    @Override
    public Volt get12Voltage() {
	return voltageRegulator(this.getVoltage(), 10);

    }

    /* (non-Javadoc)
     * @see com.bridgelabz.desiginpattern.adapter.trial.AdapterInterface#get3Voltage()
     */
    @Override
    public Volt get3Voltage() {
	return voltageRegulator(this.getVoltage(), 2);

    }


}

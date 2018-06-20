package com.bridgelabz.desiginpattern.factory;

import com.bridgelabz.desiginpattern.factoryitems.Computer;
import com.bridgelabz.desiginpattern.factoryitems.Laptops;
import com.bridgelabz.desiginpattern.factoryitems.PC;
import com.bridgelabz.desiginpattern.factoryitems.Server;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public class Factory {

    public static Computer getComputer(String type, int ram, int hdd) {

	if (type.equalsIgnoreCase("PC")) {

	    return new PC(type, ram, hdd);

	} else if (type.equalsIgnoreCase("Server")) {
	    return new Server(type, ram, hdd);
	} else if (type.equalsIgnoreCase("laptop")) {
	    return new Laptops(type, ram, hdd);
	} else {

	    return null;
	}

    }

}

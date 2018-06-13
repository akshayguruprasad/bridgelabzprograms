package com.bridgelabz.designpattern.singletons;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public class DoubleLocking {
    private static DoubleLocking ref = null;

    private DoubleLocking() {
    }

    public static DoubleLocking getDoubleLocking() {// SOULD THIS BE PROVIDED
	if (ref == null) {

	    synchronized (DoubleLocking.class) {

		ref = new DoubleLocking();
	    }

	}
	return ref;
    }

}

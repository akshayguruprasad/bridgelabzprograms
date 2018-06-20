package com.bridgelabz.designpattern.singletons;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public class InnerClass {

    private InnerClass() {
    }

    private static final class Singleton {

	private static final Singleton ref = new Singleton();
    }

    public static Singleton getInstance() {
	return Singleton.ref;
    }

}

package com.bridgelabz.designpattern.singletons;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public class ThreadSingleton {
    private static ThreadSingleton ref = new ThreadSingleton();

    private ThreadSingleton() {
    }

    public static synchronized ThreadSingleton getThreadSingleton() {
	return ref;
    }
}

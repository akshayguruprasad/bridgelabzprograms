package com.bridgelabz.designpattern.singletons;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public enum EnumSingleton {
    CREATED;
    public static EnumSingleton getEnumSingleton() {
	return EnumSingleton.CREATED;
    }
}

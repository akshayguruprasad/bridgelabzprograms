package com.bridgelabz.designpattern.singletons;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public class LazySingleton {
private static LazySingleton ref=null;

    
private LazySingleton(){}


public static LazySingleton getRef() {

    if(ref==null) {
	ref=new LazySingleton();
    }
    return ref;
}
}

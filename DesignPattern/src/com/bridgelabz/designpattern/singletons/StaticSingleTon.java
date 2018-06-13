package com.bridgelabz.designpattern.singletons;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public class StaticSingleTon {

    private static StaticSingleTon ref;

    private StaticSingleTon() throws Exception {}

    static {

	try {

	    ref = new StaticSingleTon();

	} catch (Exception e) {
	    System.out.println("Exception : " + e.getMessage());
	    e.printStackTrace();
	}

    }

    public static StaticSingleTon getStaticSingleTon() throws Exception {
	
	if(ref==null) {
	    
	    ref=new StaticSingleTon();
	}
	return ref;
	
    }

}

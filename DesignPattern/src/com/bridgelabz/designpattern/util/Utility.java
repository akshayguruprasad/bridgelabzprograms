package com.bridgelabz.designpattern.util;

import java.util.Scanner;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public class Utility {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * @return
     * 
     */
    public static String getUserString() {
	return scanner.nextLine();
    }

    /**
     * @return
     * 
     */
    public static int getUserInt() {
	int data = scanner.nextInt();
	scanner.nextLine();
	return data;
    }

    /**
     * @return
     * 
     */
    public static String getPersonName() {
	return scanner.nextLine();
	
    }

}

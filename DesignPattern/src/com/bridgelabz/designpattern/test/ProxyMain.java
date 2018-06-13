package com.bridgelabz.designpattern.test;

import java.io.IOException;

import com.bridgelabz.designpattern.proxy.CommandExecutor;
import com.bridgelabz.designpattern.proxy.CommandPrompt;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public class ProxyMain {

    /**
     * @param args
     * 
     */
    public static void main(String[] args) {

	CommandPrompt prompt = new CommandExecutor();
	try {
	    prompt.executeCommand("root", "root");
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }

}

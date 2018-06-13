package com.bridgelabz.designpattern.proxy;

import java.io.IOException;
import java.io.InputStream;

import com.bridgelabz.designpattern.util.Utility;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public class CommandExecutor implements CommandPrompt {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bridgelabz.designpattern.proxy.CommandPrompt#executeCommand(java.lang.
     * String, java.lang.String)
     */
    @Override
    public void executeCommand(String name, String password) throws IOException {

try {
	// hard coded the value for the admin to be user =root abd password =root

	System.out.println("Enter the command to exec");

	String command = Utility.getUserString();

	// saving the file in file system from the delete operations by any random user
	if (name.trim().equalsIgnoreCase("root") && password.equals("root")) {
	    System.out.println("Welcome admin ");

	} else {
	    if (command.trim().toLowerCase().startsWith("rm")) {

		System.out.println("You dont have the admin rights .");
		return;

	    }

	}
Process variable = Runtime.getRuntime().exec(command.trim());
InputStream is= variable.getInputStream();
int c=-1;
while((c=is.read())!=-1) {
    
    System.out.print((char)c);
    
}

System.out.println("Command executed success ");
    
}
catch(Exception e) {
    throw new IOException("Exception during executing the commands");
}
    }

}

package com.bridgelabz.designpattern.singletons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public class SerialSingleton {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
	
	EagerSingleton eagerObject1=EagerSingleton.getEagerSingleton();
	
	ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File("/home/bridgeit/sample.txt")));
	
	
	oos.writeObject(eagerObject1);
	
	System.out.println("Writing completed");
	
	
	
	System.out.println("The original value of the singleton value is "+eagerObject1.hashCode());
	
	
	
	
	
	ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File("/home/bridgeit/sample.txt")));
	EagerSingleton eagerObject2=null;

	eagerObject2=EagerSingleton.getEagerSingleton();
//	eagerObject2=(EagerSingleton)ois.readObject();  
	
	System.out.println("The original value of the singleton value for the scond is "+eagerObject2.hashCode());

	
    }
    
    
    
}

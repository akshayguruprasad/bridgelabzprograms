package com.annoatation.main;

import com.annoatation.annotation.ISINTEGER;
import com.annoatation.annotationexec.AnnotationHandler;

public class MainClass {

	@ISINTEGER
	String input = "123";

	public static void main(String[] args) {

		MainClass main = new MainClass();

		System.out.println("Value of input is : " + main.input);

		if (main.input != null) {
			boolean value = AnnotationHandler.processAnnotation(main);
			System.out.println(value);
			
			if(!value) {main.input="0";}

		System.out.println(main.input);
		}

	}
}

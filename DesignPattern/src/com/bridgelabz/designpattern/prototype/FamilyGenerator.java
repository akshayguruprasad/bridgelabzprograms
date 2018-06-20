package com.bridgelabz.designpattern.prototype;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public class FamilyGenerator  {

   Person person=new Person();
public static void main(String[] args) throws CloneNotSupportedException {
    
    String name="Enter your name .";
    Person person=new Person();
    person.setName("Akshay");
    
    Address address=person.getAddress();
    
    address.setHouseNumber(105);
    address.setStreet("Vijaynagar");
    address.setCity("Bangalore");
    address.setCountry("India");
    address.setZipCode(105122);
    

    
    
    
    
    
    
Person brother=    ProtoType.generatePerson(name, person);
    
    
System.out.println(person);
System.out.println(brother);
    
    
}

}
class ProtoType{
    
    
    public static Person generatePerson(String name,Person person) throws CloneNotSupportedException {
	
	System.out.println(name);
	return person.clone();
	
    }
    
    
    
    
}
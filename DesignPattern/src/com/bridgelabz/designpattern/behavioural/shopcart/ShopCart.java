package com.bridgelabz.designpattern.behavioural.shopcart;


/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public interface ShopCart {

    void add(Book book);
    void add(Fruit fruit);

     
     
     int getSum();
     
}

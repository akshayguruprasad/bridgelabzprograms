package com.bridgelabz.designpattern.behavioural.shopcart;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public class Book implements RackItems {

    int price;
    String serialNumber;

    public Book(int price, String serialNumber) {
	this.price = price;
	this.serialNumber = serialNumber;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bridgelabz.designpattern.behavioural.shopcart.RackItems#allowShopCart(com
     * .bridgelabz.designpattern.behavioural.shopcart.ShopCart)
     */
    @Override
    public void allowShopCart(ShopCart cart) {
	cart.add(this);
    }

}

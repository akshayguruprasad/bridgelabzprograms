package com.bridgelabz.designpattern.behavioural.shopcart;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public class Fruit implements RackItems{

    
    int pricePerKg;
    String name;
    int quantity;
    
    public Fruit(int pricePerKg, String name, int quantity) {
	this.pricePerKg = pricePerKg;
	this.name = name;
	this.quantity = quantity;
    }

    /* (non-Javadoc)
     * @see com.bridgelabz.designpattern.behavioural.shopcart.RackItems#allowShopCart(com.bridgelabz.designpattern.behavioural.shopcart.ShopCart)
     */
    @Override
    public void allowShopCart(ShopCart cart) {
	cart.add(this);
    }

}

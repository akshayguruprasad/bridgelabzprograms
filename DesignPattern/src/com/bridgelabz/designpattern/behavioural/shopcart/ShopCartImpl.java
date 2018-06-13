package com.bridgelabz.designpattern.behavioural.shopcart;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public class ShopCartImpl implements ShopCart {
    public int sum = 0;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bridgelabz.designpattern.behavioural.shopcart.ShopCart#add(com.bridgelabz
     * .designpattern.behavioural.shopcart.Book)
     */
    @Override
    public void add(Book book) {
	sum += book.price;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bridgelabz.designpattern.behavioural.shopcart.ShopCart#add(com.bridgelabz
     * .designpattern.behavioural.shopcart.Fruit)
     */
    @Override
    public void add(Fruit fruit) {

	sum += fruit.pricePerKg * fruit.quantity;
    }

    public int getSum() {

	return sum;

    }

}

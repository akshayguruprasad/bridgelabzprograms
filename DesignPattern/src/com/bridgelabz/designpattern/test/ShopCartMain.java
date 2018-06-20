package com.bridgelabz.designpattern.test;

import com.bridgelabz.designpattern.behavioural.shopcart.Book;
import com.bridgelabz.designpattern.behavioural.shopcart.Fruit;
import com.bridgelabz.designpattern.behavioural.shopcart.RackItems;
import com.bridgelabz.designpattern.behavioural.shopcart.ShopCart;
import com.bridgelabz.designpattern.behavioural.shopcart.ShopCartImpl;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public class ShopCartMain {
    public static void main(String[] args) {

	Book item1 = new Book(100, "1000112");
	Book item2 = new Book(299, "10001HJJ");
	Book item3 = new Book(100, "1BBNDF112");
	Book item4 = new Book(300, "10066DD2");
	Book item5 = new Book(200, "1012KLL2");
	Book item6 = new Book(100, "10LD10012");
	Book item7 = new Book(1000, "AB1000112");
	Book item8 = new Book(10, "112");
	Book item9 = new Book(99, "10AB123D2");
	Book item10 = new Book(999, "10SDSF2");
	Book item11 = new Book(100, "1UJRN12");
	Book item12 = new Book(566, "10001GD");
	Book item13 = new Book(100, "10001FFS");
	Book item14 = new Book(100, "1006GHJ");

	Fruit item15 = new Fruit(110, "Apple", 2);
	Book item16 = new Book(1990, "16363006GHJ");

	ShopCart cart = new ShopCartImpl();
	item1.allowShopCart(cart);
	item2.allowShopCart(cart);
	item3.allowShopCart(cart);
	item4.allowShopCart(cart);
	item5.allowShopCart(cart);
	item6.allowShopCart(cart);
	item7.allowShopCart(cart);
	item8.allowShopCart(cart);
	item9.allowShopCart(cart);
	item10.allowShopCart(cart);
	item11.allowShopCart(cart);
	item12.allowShopCart(cart);
	item13.allowShopCart(cart);
	item14.allowShopCart(cart);
	item15.allowShopCart(cart);
	item16.allowShopCart(cart);

	System.out.println("Total cost is : " + cart.getSum());

    }
}

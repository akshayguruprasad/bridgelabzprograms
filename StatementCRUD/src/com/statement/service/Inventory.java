package com.statement.service;

import java.util.List;

import com.statement.entity.Item;

public interface Inventory {

	public int add(Item item);

	boolean remove(int id);

	List<Item> searchItem(String name);

	boolean update(Item item);
	
	Item searchItem(int id);
	
	

}

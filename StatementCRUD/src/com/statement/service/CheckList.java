package com.statement.service;

import java.util.List;

import com.statement.entity.CheckListItem;

public interface CheckList {
	public int add(CheckListItem item);

	boolean remove(int id);

	CheckListItem searchItem(int id);

	List<CheckListItem> searchItem(String name);
	
	boolean update(CheckListItem item);
	

	
	
	
}

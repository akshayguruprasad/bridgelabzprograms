package com.statement.main;

import java.util.List;

import com.statement.entity.CheckListItem;
import com.statement.entity.Item;
import com.statement.service.CheckList;
import com.statement.service.Inventory;
import com.statement.serviceImpl.CheckListImpl;
import com.statement.serviceImpl.InventoryManage;
import com.statement.serviceImpl.TableCreator;
import com.statement.util.Utility;

public class MainClass {
	static {

		TableCreator.generateTables();

	}

	public static void main(String[] args) {

		Inventory inventorymanager = new InventoryManage();
		CheckList checkList = new CheckListImpl();
		while (true) {

			System.out.println("1.Enter Checklist");
			System.out.println("2.Enter item operation");

			int choice = Utility.getUserInt();
			switch (choice) {
			case 1:
				checkListOperate(checkList);

				break;
			case 2:
				itemOperations(inventorymanager);
				break;

			default:
				break;
			}

		}
	}

	private static void itemOperations(Inventory inventorymanager) {
		while (true) {

			System.out.println("1.add item");
			System.out.println("2.remove item");
			System.out.println("3.update item");
			System.out.println("4.display item");
			System.out.println("5.exit");
			int choice = Utility.getUserInt();

			switch (choice) {
			case 1:
				addItem(inventorymanager);
				break;
			case 2:
				removeItem(inventorymanager);
				break;
			case 3:

				updateItem(inventorymanager);

				break;
			case 4:

				displayItem(inventorymanager);

				break;
			case 5:

			default:
				System.out.println("Exiting .....");
				return;

			}

		}
	}

	private static void checkListOperate(CheckList checkListManager) {

		while (true) {

			System.out.println("1.add checklist entry");
			System.out.println("2.delete checklist entry");
			System.out.println("3.update checklist entry");
			System.out.println("4.search checklist entry");
			System.out.println("5.exit");
			int choice = Utility.getUserInt();

			switch (choice) {
			case 1:

				checkListItemAdd(checkListManager);
				break;
			case 3:

				checkListUpdate(checkListManager);
				break;
			case 2:

				checkListItemRemove(checkListManager);
				break;
			case 4:
				searchCheckList(checkListManager);

				break;

			default:
				System.out.println("Exiting ..");

				return;
			}

		}
	}

	private static void searchCheckList(CheckList checkListManager) {
		System.out.println("1.search by name");
		System.out.println("2.search by id");

		int choice = Utility.getUserInt();
		System.out.println("Enter  value");
		String searchQueryValue = Utility.getUserString();
		switch (choice) {
		case 1:

			checkListManager.searchItem(searchQueryValue).forEach(System.out::println);

			break;
		case 2:
			System.out.println(checkListManager.searchItem(Integer.parseInt(searchQueryValue.trim())).toString());
			break;

		default:
			System.out.println("invalid choice");
			break;
		}
	}

	private static void checkListItemRemove(CheckList checkListManager) {
		System.out.println("Enter the checklist id");
		int id = Utility.getUserInt();

		if (checkListManager.remove(id)) {
			System.out.println("Removed item from checklist");
			return;
		}
		System.out.println("failed to remove");
	}

	private static void checkListUpdate(CheckList checkListManager) {
		System.out.println("Enter the  item  id to remove");
		int id = Utility.getUserInt();
		CheckListItem item = checkListManager.searchItem(id);
		if (item != null) {

			System.out.println("The requirement item is ");
			System.out.println(item);

			item = Utility.updateCheckList();
			item.setId(id);
			boolean success = checkListManager.update(item);

			if (success) {
				System.out.println("updated success");
				return;
			}
			System.out.println("Could not update ");
			return;

		}
	}

	private static void checkListItemAdd(CheckList checkListManager) {
		System.out.println("Enter the requirement details");
		CheckListItem item = Utility.getUserCheckListItem();
		int id = checkListManager.add(item);

		System.out.println("Id generated is " + id);
	}

	private static void displayItem(Inventory inventorymanager) {

		System.out.println("Enter the name of the item to display ");

		String name = Utility.getUserString();

		List<Item> items = inventorymanager.searchItem(name);

		System.out.println(items);

	}

	private static void updateItem(Inventory inventorymanager) {
		System.out.println("Enter the item id");
		int id = Utility.getUserInt();

		Item item = Utility.getUpdateItem();
		item.setId(id);
		if (inventorymanager.update(item)) {

			System.out.println("Item updated success");
			return;
		}

		System.out.println("Item failed to update");

	}

	private static void removeItem(Inventory inventorymanager) {
		System.out.println("Enter the id of item to remove");
		int id = Utility.getUserInt();

		boolean value = inventorymanager.remove(id);

		if (value) {
			System.out.println("Item removed success");
			return;

		}
		System.out.println("No item found");

	}

	private static void addItem(Inventory checkListManager) {

		Item item = Utility.getUserItem();
		if (item == null) {
			return;
		}
		int id = checkListManager.add(item);

		if (id < 1) {

			System.out.println("Item failed to  add....");
			return;
		}

		System.out.println("Item added success with id : " + id);

	}
}

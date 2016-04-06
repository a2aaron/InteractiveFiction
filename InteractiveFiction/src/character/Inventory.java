package character;

import java.util.ArrayList;

import items.AbstractItem;

public class Inventory {
	ArrayList<AbstractItem> inventoryList = new ArrayList<AbstractItem>();
	
	public Inventory(AbstractItem item) {
		inventoryList.add(item);
	}
	
	public Inventory() {  }

	public ArrayList<AbstractItem> getInventory() {
		return inventoryList;
	}
	
	public boolean hasItem(AbstractItem item) {
		return inventoryList.contains(item);
	}
	
	public int numberOfItems() {
		return inventoryList.size();
	}
	
	public boolean hasItemByName(String itemName) {
		for(int i = 0; i < inventoryList.size(); i++) {
			AbstractItem item = inventoryList.get(i);
			if (item.getName().equals(itemName)) {
				return true;
			}
		}
		return false;
	}
	
	public AbstractItem getItemByName(String itemName) {
		for(int i = 0; i < inventoryList.size(); i++) {
			AbstractItem item = inventoryList.get(i);
			if (item.getName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}
	
	public void removeItem(AbstractItem item) {
		inventoryList.remove(item);
	}
	
	public void addItem(AbstractItem item) {
		inventoryList.add(item);
	}
	
	public void moveItem(AbstractItem item, Inventory destination) {
		removeItem(item);
		destination.addItem(item);
	}
	
	public void takeItem(AbstractItem item, Inventory takeFrom) {
		addItem(item);
		takeFrom.removeItem(item);
	}
	
	public ArrayList<AbstractItem> getInventoryList() {
		return inventoryList;
	}
	
}

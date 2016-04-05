package character;

import java.util.ArrayList;

import types.AbstractItem;

public class Inventory {
	ArrayList<AbstractItem> inventoryList = new ArrayList<AbstractItem>();
	
	public Inventory(AbstractItem item) {
		inventoryList.add(item);
	}

	public ArrayList<AbstractItem> getInventory() {
		return inventoryList;
	}
	
	public void dropItem(AbstractItem item) {
		inventoryList.remove(item);
	}
	
	public void addItem(AbstractItem item) {
		inventoryList.add(item);
	}
	
	public void moveItem(AbstractItem item, Inventory destination) {
		inventoryList.remove(item);
		destination.addItem(item);
	}
	
	public ArrayList<AbstractItem> getInventoryList() {
		return inventoryList;
	}
	
}

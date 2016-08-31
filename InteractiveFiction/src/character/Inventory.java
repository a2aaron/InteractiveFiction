package character;

import java.util.concurrent.CopyOnWriteArrayList;

import items.GenericItem;

public class Inventory {
	CopyOnWriteArrayList<GenericItem> inventoryList = new CopyOnWriteArrayList<GenericItem>();
	
	public Inventory(GenericItem item) {
		inventoryList.add(item);
	}
	
	public Inventory() {  }

	public Inventory(Inventory... inventories) {
		for (Inventory inventory : inventories) {
			inventoryList.addAll(inventory.getInventoryList());
		}
	}

	public CopyOnWriteArrayList<GenericItem> getInventory() {
		return inventoryList;
	}
	
	public boolean hasItem(GenericItem item) {
		return inventoryList.contains(item);
	}
	
	public int numberOfItems() {
		return inventoryList.size();
	}

	public GenericItem getItemByName(String itemName) {
		for(int i = 0; i < inventoryList.size(); i++) {
			GenericItem item = inventoryList.get(i);
			//System.out.println(item.getName().toLowerCase() + " | " + itemName); 
			if (item.getName().toLowerCase().equals(itemName.toLowerCase())) {
				return item;
			}
		}
		return null;
	}
	
	public void removeItem(GenericItem item) {
		inventoryList.remove(item);
	}
	
	public void addItem(GenericItem item) {
		inventoryList.add(item);
	}
	
	public void moveItem(GenericItem item, Inventory destination) {
		removeItem(item);
		destination.addItem(item);
	}
	
	public void takeItem(GenericItem item, Inventory takeFrom) {
		addItem(item);
		takeFrom.removeItem(item);
	}
	
	public CopyOnWriteArrayList<GenericItem> getInventoryList() {
		return inventoryList;
	}
	
	public void printItems() {
		if (inventoryList.size() != 0) {
			for (GenericItem item : inventoryList) {
				System.out.println("[" + item.getName() + "]");
				System.out.println(item.getDescription());
			} 
		}
	}
	
}

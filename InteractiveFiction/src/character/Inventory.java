package character;

import java.util.concurrent.CopyOnWriteArrayList;

import items.AbstractItem;

public class Inventory {
	CopyOnWriteArrayList<AbstractItem> inventoryList = new CopyOnWriteArrayList<AbstractItem>();
	
	public Inventory(AbstractItem item) {
		inventoryList.add(item);
	}
	
	public Inventory() {  }

	public CopyOnWriteArrayList<AbstractItem> getInventory() {
		return inventoryList;
	}
	
	public boolean hasItem(AbstractItem item) {
		return inventoryList.contains(item);
	}
	
	public int numberOfItems() {
		return inventoryList.size();
	}
	
	public boolean hasItemByName(String itemName) {
	    if (numberOfItems() == 0) {
	        return false;
	    }

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
	
	public CopyOnWriteArrayList<AbstractItem> getInventoryList() {
		return inventoryList;
	}
	
}

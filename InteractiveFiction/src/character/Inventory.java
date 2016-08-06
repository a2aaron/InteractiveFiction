package character;

import java.util.concurrent.CopyOnWriteArrayList;

import items.AbstractItem;

public class Inventory {
	CopyOnWriteArrayList<AbstractItem> inventoryList = new CopyOnWriteArrayList<AbstractItem>();
	
	public Inventory(AbstractItem item) {
		inventoryList.add(item);
	}
	
	public Inventory() {  }

	public Inventory(Inventory... inventories) {
		for (Inventory inventory : inventories) {
			inventoryList.addAll(inventory.getInventoryList());
		}
	}

	public CopyOnWriteArrayList<AbstractItem> getInventory() {
		return inventoryList;
	}
	
	public boolean hasItem(AbstractItem item) {
		return inventoryList.contains(item);
	}
	
	public int numberOfItems() {
		return inventoryList.size();
	}

	public AbstractItem getItemByName(String itemName) {
		for(int i = 0; i < inventoryList.size(); i++) {
			AbstractItem item = inventoryList.get(i);
			//System.out.println(item.getName().toLowerCase() + " | " + itemName); 
			if (item.getName().toLowerCase().equals(itemName.toLowerCase())) {
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
	
	public void printItems() {
		if (inventoryList.size() != 0) {
			for (AbstractItem item : inventoryList) {
				System.out.println("[" + item.getName() + "]");
				System.out.println(item.getDescription());
			} 
		}
	}
	
}

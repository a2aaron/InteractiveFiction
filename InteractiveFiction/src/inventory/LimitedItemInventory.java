package inventory;

import java.util.concurrent.CopyOnWriteArrayList;

import items.utility.GenericItem;

public class LimitedItemInventory extends Inventory {
	CopyOnWriteArrayList<GenericItem> allowedList = new CopyOnWriteArrayList<GenericItem>();
	
	public LimitedItemInventory(GenericItem allowedItem) {
		allowedList.add(allowedItem);
	}
	
	@Override
	public void addItem(GenericItem item) {
		if (itemAllowed(item)) {
			inventoryList.add(item);
		}
	}
	
	public boolean itemAllowed(GenericItem item) {
		return allowedList.contains(item);
	}
	
	public CopyOnWriteArrayList<GenericItem> getAllowedList() {
		return allowedList;
	}
}

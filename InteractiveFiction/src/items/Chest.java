package items;

import inventory.Inventory;
import items.utility.GenericItem;

public class Chest extends GenericItem {
	Inventory inventory = new Inventory();
	
	public Chest() {   }
	
	public Chest(Inventory inv) {
		inventory = inv;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
}

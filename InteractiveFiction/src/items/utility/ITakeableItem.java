package items.utility;

import inventory.Inventory;

public interface ITakeableItem {
	public void takeItem(Inventory destination);
	public boolean isTakeable();
}

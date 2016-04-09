package items;

import character.Inventory;

public interface ITakeableItem {
	public void takeItem(Inventory destination);
	public boolean isTakeable();
}

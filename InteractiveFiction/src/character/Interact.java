package character;

import items.AbstractItem;
import items.IBreakableItem;
import items.ITakeableItem;
import rooms.GenericRoom;
import rooms.RoomInventory;

public class Interact {
	Inventory playerInventory;
	
	public Interact(Inventory playerInventory) {
		this.playerInventory = playerInventory;
	}
	
	public String examine(AbstractItem item) {
		return item.getDescription(); 
	}
	
	public void useItem(AbstractItem item) {
		item.useItem();
	}
	
	public void takeItem(AbstractItem item, RoomInventory inventory) {
		playerInventory.takeItem(item, inventory);
	}
	
	public void takeItem(AbstractItem item, GenericRoom room) {
		playerInventory.takeItem(item, room.getRoomInventory());
	}

	public boolean canTakeItem(AbstractItem item) {
		if (item instanceof ITakeableItem) {
			if (((ITakeableItem) item).isTakeable()) {
				return true;
			}
		}
		return false;
	}
	
	public void breakItem(IBreakableItem item) {
		item.breakItem();
	}
	/**
	 * 
	 * @param item
	 * @return true if it succeeds, false if it does not
	 */
	public boolean tryBreakItem(AbstractItem item) {
		if (item instanceof IBreakableItem) {
			if (!((IBreakableItem) item).isBroken()) {
				((IBreakableItem) item).breakItem();
				return true;
			}
		}
		return false;
	}
}

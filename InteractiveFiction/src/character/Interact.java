package character;

import items.AbstractItem;
import items.BreakableItem;
import rooms.GenericRoom;

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
	
	public void takeItem(GenericRoom currentRoom, AbstractItem item) {
		playerInventory.takeItem(item, currentRoom.getRoomInventory());
	}
	
	public void breakItem(BreakableItem item) {
		item.breakItem();
	}
}

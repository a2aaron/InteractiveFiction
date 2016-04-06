package character;

import rooms.GenericRoom;
import types.AbstractItem;
import types.Vace;

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
	
	public void breakItem(AbstractItem item) {
		item.breakItem();
	}
}

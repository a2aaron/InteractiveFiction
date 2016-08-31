package rooms;

import java.util.ArrayList;

import character.Inventory;
import items.GenericItem;

public class RoomInventory extends Inventory {
	
	ArrayList<GenericItem> roomList = new ArrayList<GenericItem>(); 
	
	public RoomInventory(GenericItem item) {
		super(item);
		roomList.add(item);
	}

	public RoomInventory() {
		super();
	}
}

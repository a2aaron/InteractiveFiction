package inventory;

import java.util.ArrayList;

import items.utility.GenericItem;

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

package rooms;

import java.util.ArrayList;

import character.Inventory;
import types.AbstractItem;

public class RoomInventory extends Inventory {
	
	ArrayList<AbstractItem> roomList = new ArrayList<AbstractItem>(); 
	
	public RoomInventory(AbstractItem item) {
		super(item);
		roomList.add(item);
	}
}

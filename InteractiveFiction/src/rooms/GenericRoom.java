package rooms;

import java.util.concurrent.CopyOnWriteArrayList;

import items.AbstractItem;
import types.Action.MovementAdverb;

public class GenericRoom {
	String roomName;
	String roomDescription;
	String extendedRoomDescription;
	RoomInventory roomInventory = new RoomInventory();
	CopyOnWriteArrayList<MovementAdverb> exits = new CopyOnWriteArrayList<MovementAdverb>();
	CopyOnWriteArrayList<MovementAdverb> entrances = new CopyOnWriteArrayList<MovementAdverb>();
	
	public GenericRoom() {
		
	}
	
	public GenericRoom(String name, String description) {
		roomName = name;
		roomDescription = description;
	} 
	
	public GenericRoom(RoomInventory items) {
		roomInventory = items;
	}
	
	public void addItem(AbstractItem item) {
		roomInventory.addItem(item);
	}
	
	public void addExit(MovementAdverb exit) {
		exits.add(exit);
	}
	
	public void addEntrance(MovementAdverb entrance) {
		exits.add(entrance);
	}
	
	public void linkRoomTo(MovementAdverb direction, GenericRoom room) {
		exits.add(direction);
		entrances.add(MovementAdverb.getOpposite(direction));
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public String getRoomDescription() {
		return roomDescription;
	}
	
	public String getExtendedRoomDescription() {
	    return extendedRoomDescription;
	}
	
	public CopyOnWriteArrayList<AbstractItem> getRoomInventoryList() {
		return roomInventory.getInventoryList();
	}
	
	public RoomInventory getRoomInventory() {
		return roomInventory;
	}

	public void setRoomName(String newName) {
		roomName = newName;
	}
	
	public void setRoomDescription(String newDescription) {
		roomDescription = newDescription;
	}
}

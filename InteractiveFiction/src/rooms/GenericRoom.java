package rooms;

import java.util.concurrent.CopyOnWriteArrayList;

import items.AbstractItem;
import types.Directions.CompassDirections;

public class GenericRoom {
	String roomName;
	String roomDescription;
	RoomInventory roomInventory;
	CopyOnWriteArrayList<CompassDirections> exits = new CopyOnWriteArrayList<CompassDirections>();
	CopyOnWriteArrayList<CompassDirections> entrances = new CopyOnWriteArrayList<CompassDirections>();
	
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
	
	public void addExit(CompassDirections exit) {
		exits.add(exit);
	}
	
	public void addEntrance(CompassDirections entrance) {
		exits.add(entrance);
	}
	
	public void linkRoomTo(CompassDirections direction, GenericRoom room) {
		exits.add(direction);
		entrances.add(direction.getOpposite(direction));
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public String getRoomDescription() {
		return roomDescription;
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

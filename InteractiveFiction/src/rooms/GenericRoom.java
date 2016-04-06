package rooms;

import java.util.ArrayList;

import types.AbstractItem;
import types.Directions.CompassDirections;

public class GenericRoom {
	String roomName;
	String roomDescription;
	RoomInventory roomInventory;
	ArrayList<CompassDirections> exits = new ArrayList<CompassDirections>();
	ArrayList<CompassDirections> entrances = new ArrayList<CompassDirections>();
	
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
	
	public ArrayList<AbstractItem> getRoomInventoryList() {
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

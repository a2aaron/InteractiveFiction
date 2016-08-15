package rooms;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import items.AbstractItem;
import types.Action.MovementAdverb;

public class GenericRoom {
	String roomName;
	String roomDescription;
	String extendedRoomDescription;
	RoomInventory roomInventory = new RoomInventory();
	Exit exits = new Exit();
	
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
	
	public void addExit(GenericRoom exit) {
		exits.addExit(exit);
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

	public Exit getExits() {
		return exits;
	}
	
	public void setRoomName(String newName) {
		roomName = newName;
	}
	
	public void setRoomDescription(String newDescription) {
		roomDescription = newDescription;
	}
}

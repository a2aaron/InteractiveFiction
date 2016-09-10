package rooms;

import inventory.RoomInventory;
import items.utility.GenericItem;
import types.Action.MovementAdverb;

public class GenericRoom {
	String roomName = "";
	String roomDescription = "";
	String extendedRoomDescription = "";
	RoomInventory roomInventory = new RoomInventory();
	Exit<MovementAdverb, GenericRoom> exits = new CompassExit();
	
	public GenericRoom(String roomName, String roomDescription, String extendedRoomDescription, RoomInventory roomInventory, CompassExit exits) {
		this.roomName = roomName;
		this.roomDescription = roomDescription;
		this.extendedRoomDescription = extendedRoomDescription;
		this.roomInventory = roomInventory;
		this.exits = exits;
	}
	
	public GenericRoom(String roomName, String roomDescription, RoomInventory roomInventory, CompassExit exits) {
		this.roomName = roomName;
		this.roomDescription = roomDescription;
		this.roomInventory = roomInventory;
		this.exits = exits;
	}

	public GenericRoom(String name, String description) {
		roomName = name;
		roomDescription = description;
	} 
	
	public GenericRoom() {
		
	}

	public void addItem(GenericItem item) {
		roomInventory.addItem(item);
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
	
	public RoomInventory getRoomInventory() {
		return roomInventory;
	}

	public Exit<MovementAdverb, GenericRoom> getExits() {
		return exits;
	}
	
	public void setRoomName(String newName) {
		roomName = newName;
	}
	
	public void setRoomDescription(String newDescription) {
		roomDescription = newDescription;
	}
	
	public void setExtendedRoomDescription(String newDescription) {
		extendedRoomDescription = newDescription;
	}
	
	public void appendRoomDescription(String text) {
		roomDescription += text;
	}
	
	public void appendExtendedRoomDescription(String text) {
		extendedRoomDescription += text;
	}
}

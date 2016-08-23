package rooms;

import java.util.concurrent.CopyOnWriteArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import items.AbstractItem;
import types.Action.MovementAdverb;

public class GenericRoom {
	String roomName = "";
	String roomDescription = "";
	String extendedRoomDescription = "";
	RoomInventory roomInventory = new RoomInventory();
	Exit<MovementAdverb, GenericRoom> exits = new CompassExit();
	
	public GenericRoom() {
		
	}
	
	public GenericRoom(String name, String description) {
		roomName = name;
		roomDescription = description;
	} 
	
	public GenericRoom(RoomInventory items) {
		roomInventory = items;
	}
	
	public GenericRoom(JSONObject room) throws Exception {
		roomName = room.getString("roomName");
		roomDescription = room.getString("roomDescription");
		exits = new CompassExit(room.getJSONObject("exits"));
	}
	
	public GenericRoom(String roomName, String roomDescription, RoomInventory roomInventory, CompassExit exits) {
		this.roomName = roomName;
		this.roomDescription = roomDescription;
		this.roomInventory = roomInventory;
		this.exits = exits;
	}

	public void addItem(AbstractItem item) {
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
	
	public CopyOnWriteArrayList<AbstractItem> getRoomInventoryList() {
		return roomInventory.getInventoryList();
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
}

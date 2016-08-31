package rooms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import items.GenericItem;
import types.Action.MovementAdverb;
import world.Interpreter;

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
	
	public GenericRoom(String roomName, String roomDescription, RoomInventory roomInventory, CompassExit exits) {
		this.roomName = roomName;
		this.roomDescription = roomDescription;
		this.roomInventory = roomInventory;
		this.exits = exits;
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
	
	public CopyOnWriteArrayList<GenericItem> getRoomInventoryList() {
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
	
	public void appendRoomDescription(String text) {
		roomDescription += text;
	}
	
	public void appendExtendedRoomDescription(String text) {
		extendedRoomDescription += text;
	}
}

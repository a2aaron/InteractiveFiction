package rooms;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import items.AbstractItem;
import types.Action.MovementAdverb;

public class GenericRoom {
	String roomName = "";
	String roomDescription = "";
	String extendedRoomDescription = "";
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
	//fucking terrible idea
	public GenericRoom(JSONObject room) throws ClassNotFoundException, JSONException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		roomName = room.getString("roomName");
		roomDescription = room.getString("roomDescription");
		JSONArray itemList = room.getJSONArray("items");
		for (int i = 0; i < itemList.length(); i++) {
			JSONObject item = itemList.getJSONObject(i);
			String itemName = item.getString("itemName");
			String itemDescription = item.getString("itemName");
			Constructor construct = Class.forName(item.getString("itemClass")).getConstructor(String.class);
			addItem((AbstractItem) construct.newInstance(itemName, itemDescription));
		}
	}
	
	public GenericRoom(String roomName, String roomDescription, RoomInventory roomInventory, Exit exits) {
		this.roomName = roomName;
		this.roomDescription = roomDescription;
		this.roomInventory = roomInventory;
		this.exits = exits;
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

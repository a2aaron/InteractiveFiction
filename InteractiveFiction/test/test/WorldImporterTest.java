package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.*;
import org.junit.Test;

import items.AbstractItem;
import items.Vace;
import rooms.CompassRoom;
import rooms.Exit;
import rooms.GenericRoom;
import rooms.RoomInventory;

public class WorldImporterTest {	
	File roomFile = new File("test/test/testRoom.json");
	File itemFile = new File("test/test/testItem.json");
	 /*
	@Test 
	public void createGenericRoomTest() throws ClassNotFoundException, JSONException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		JSONObject reader = new JSONObject(roomFile);	
		GenericRoom room = new GenericRoom(reader);
		assertFalse(room == null);
	}*/
	
	@Test
	public void createItemFromJSONTest() throws Exception {
		JSONTokener tokener = new JSONTokener(new FileInputStream(itemFile));
		JSONObject reader = new JSONObject(tokener);
		AbstractItem item = createItemFromJSON(reader);
		assertTrue(item instanceof Vace);
		assertTrue(item.getName() != null);
		assertTrue(item.getDescription() != null);

		((Vace) item).breakItem();;
		
		assertTrue(((Vace) item).isBroken());
	}
	
	
	public AbstractItem createItemFromJSON(JSONObject item) throws Exception {
		String itemName = item.getString("itemName");
		String itemDescription = item.getString("itemDescription");
		Constructor construct = Class.forName(item.getString("itemClass")).getConstructor(String.class, String.class);
		return (AbstractItem) construct.newInstance(itemName, itemDescription);
	}
	
	@Test
	public void createRoomFromJSONTest() throws Exception {
		JSONTokener tokener = new JSONTokener(new FileInputStream(roomFile));
		JSONObject reader = new JSONObject(tokener);
		GenericRoom room = createRoomFromJSON(reader);
		assertTrue(room.getRoomName() != null);
		assertTrue(room.getRoomDescription() != null);
		assertTrue(room.getExits().getExitSet().size() != 0);
		assertTrue(room.getRoomInventory().getItemByName("Test Vace") != null);
	}
	
	public GenericRoom createRoomFromJSON(JSONObject room) throws Exception {
		String roomName = room.getString("roomName");
		String roomDescription = room.getString("roomDescription");
		JSONArray itemList = room.getJSONArray("items");
		RoomInventory roomInventory = new RoomInventory();
		for (int i = 0; i < itemList.length(); i++) {
			roomInventory.addItem(createItemFromJSON(itemList.getJSONObject(i)));
		}
		
		JSONArray exitList = room.getJSONArray("exits");
		Exit exits = new Exit();
		for (int i = 0; i < exitList.length(); i++) {
			JSONObject exitRoom = exitList.getJSONObject(i);
			exits.addExit(new GenericRoom()); //TODO: actually link the rooms together
		}
		return new GenericRoom(roomName, roomDescription, roomInventory, exits);
	}
}

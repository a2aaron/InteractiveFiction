package world;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import org.json.*;

import character.Interact;
import character.Inventory;
import character.PlayerState;
import items.GenericItem;
import rooms.CompassExit;
import rooms.GenericRoom;
import rooms.RoomInventory;
import types.Action.MovementAdverb;

public class WorldImporter {	
	public static GenericRoom createWorld(File file) throws JSONException, FileNotFoundException {
		JSONObject exits = new JSONObject(new JSONTokener(new FileInputStream(file)));
		JSONArray nameList = exits.getJSONArray("nameList");
		HashMap<String, GenericRoom> rooms = new HashMap<String, GenericRoom>();
		
		GenericRoom startingRoom = null;
		for (int i = 0; i < nameList.length(); i++) {
			String internalRoomName = nameList.getString(i);
			File roomFile = new File("sub1/rooms/" + internalRoomName + ".json");
			GenericRoom room = null;
			try {
				JSONObject roomData = WorldImporter.JSONObjectFromFile(roomFile);
				room = WorldImporter.genericRoomFromJSON(roomData);
			} catch (FileNotFoundException e) {
				System.err.println(roomFile.getName() + " missing. Creating blank room instead");
				room = new GenericRoom(internalRoomName, "");
			} catch (JSONException e) {
				System.err.println("Syntax error in file " + roomFile.getName());
				room = new GenericRoom(internalRoomName, "");
				e.printStackTrace();
			}
			
			rooms.put(internalRoomName, room);
			if (nameList.getString(i).equals("tileWheel")) {
				startingRoom = room; 
			}
		}
		// TODO: It's nicer if you only have the exits per room instead of a seperate list
		JSONArray exitList = exits.getJSONArray("exitList");
		for (int i = 0; i < exitList.length(); i++) {
			JSONObject exit = exitList.getJSONObject(i);
			String roomName = exit.getString("roomName");
			GenericRoom currentRoom = rooms.get(roomName);
			StringBuilder sb = new StringBuilder();
			for (String key : exit.keySet()) {
				MovementAdverb direction = MovementAdverb.stringToAdverb(key);
				if (direction != null) {
					GenericRoom exitRoom = rooms.get(exit.getString(key));
					currentRoom.getExits().addExit(direction, exitRoom);
					sb.append(key + ", ");
				}
				
			}
			currentRoom.appendExtendedRoomDescription("\nYou may go: " + sb);
		}
		return startingRoom;
	}
	
	public static GenericRoom genericRoomFromJSON(JSONObject file) throws JSONException {
		String roomName = file.getString("roomName");
		String roomDescription = file.getString("roomDescription");
		String extendedRoomDescription = roomDescription;
		CompassExit exits = new CompassExit();
		// CompassExit creation. Falls back to blank exit if it can't find any exit object
		try {
			exits = new CompassExit(file.getJSONObject("exits"));
		} catch (JSONException e) {
			System.out.println("No exits object found for " + roomName + ", creating empty exit instead");
		}
		GenericRoom room = new GenericRoom(roomName, roomDescription, extendedRoomDescription, new RoomInventory(), exits);
		// Items creation. Falls back to no items if it can't create them.
		try{
			JSONArray itemList = file.getJSONArray("itemList");
			for (int i = 0; i < itemList.length(); i++) {
				File itemFile = new File("sub1/items/" + itemList.getString(i)); // BAD: Hard coded file path
				try {
					JSONObject item = JSONObjectFromFile(itemFile);
					room.addItem(ItemImporter.createItem(item));
				} catch (FileNotFoundException e) {
					System.out.println("File " + itemFile.getName() + " missing for " + roomName);
				} catch (JSONException e) {
					System.err.println("Syntax error for file " + itemFile.getName());
					e.printStackTrace();
				}
			}
		} catch (JSONException e) {
			System.out.println("No itemList found for " + roomName + ", adding no items instead");
		} 
		
		return room;
	}
	
	public static JSONObject JSONObjectFromFile(File file) throws JSONException, FileNotFoundException  {
		return new JSONObject(new JSONTokener(new FileInputStream(file)));
	}
}

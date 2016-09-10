package world;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import org.json.*;

import character.Interact;
import character.PlayerState;
import inventory.Inventory;
import inventory.RoomInventory;
import items.utility.GenericItem;
import rooms.CompassExit;
import rooms.GenericRoom;
import types.Action.MovementAdverb;

public class WorldImporter {
	GenericRoom startingRoom;
	HashMap<String, GenericRoom> roomSet = new HashMap<String, GenericRoom>();
	
	public WorldImporter() {   }
	
	public WorldImporter(File rooms) throws IOException {
		createRoomSet(rooms);
	}
	
	public void createRoomSet(File rooms) throws IOException {
		File[] files = rooms.listFiles();
		for (File file : files) {
			if (!file.isFile() || !getExtension(file).equals("json"))  {
				continue;
			}
			try {
				GenericRoom room = genericRoomFromJSON(JSONObjectFromFile(file));
				roomSet.put(file.getName(), room);
			} catch (JSONException e) {
				e.printStackTrace();
				System.err.println(file.toString() + " has a syntax error, adding blank room instead");
				roomSet.put("", new GenericRoom());
			} catch (FileNotFoundException e) {
				System.err.println(file.toString() + " does not exist, adding blank room instead");
				roomSet.put("", new GenericRoom());
			}
		}		
		linkRoomSet(rooms);
		setStartingRoom();
	}
	
	private void linkRoomSet(File rooms) throws IOException {
		File[] files = rooms.listFiles();
		for (File file : files) {
			if (!file.isFile() || !getExtension(file).equals("json")) {
				continue;
			}
			try {
				JSONObject roomData = JSONObjectFromFile(file);
				JSONObject exits = roomData.getJSONObject("exits");
				for (String key : exits.keySet()) {
					MovementAdverb direction = MovementAdverb.stringToAdverb(key);
					if (direction != null) {
						String fromName = file.getName();
						String toName = exits.getString(key) + ".json";
						GenericRoom roomLinkFrom = roomSet.get(fromName);
						GenericRoom roomLinkTo = roomSet.get(toName);
						roomLinkFrom.getExits().addExit(direction, roomLinkTo);
					}
				}
			} catch (FileNotFoundException e) {
				System.err.println(file.toString() + " not found! No exit added.");
			} catch (JSONException e) {
				System.err.println(file.toString() + " has no exit object! No exit added.");
			} 
		}
	}
	
	public void setStartingRoom() {
		roomSet.forEach((name, room) -> {
			if (name.equals("tileWheel.json")) {
				startingRoom = room;
			}
		});
	}
	
	
	public static GenericRoom genericRoomFromJSON(JSONObject file) throws JSONException {
		String roomName = file.getString("roomName");
		String roomDescription = file.getString("roomDescription");
		String extendedRoomDescription = roomDescription;
		CompassExit exits = new CompassExit();
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
	
	public GenericRoom getStartingRoom() {
		return startingRoom;
	}
	
	public HashMap<String, GenericRoom> getRoomSet() {
		return roomSet;
	}
	
	public static String getExtension(File file) {
		String extension = "";
		int dotIndex = file.getName().lastIndexOf(".");
		if (dotIndex >= 0) {
			extension = file.getName().substring(dotIndex + 1);
		}
		return extension;
	}
}

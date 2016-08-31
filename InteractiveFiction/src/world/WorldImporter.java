package world;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.json.*;

import character.Interact;
import character.Inventory;
import character.PlayerState;
import items.GenericItem;
import rooms.CompassExit;
import rooms.GenericRoom;
import rooms.RoomInventory;

public class WorldImporter {
	JSONObject reader;
	
	static GenericRoom startingRoom;
	static JSONArray roomList; 
	static PlayerState playerState;
	static Scanner playerInput = new Scanner(System.in);
	static Parser playerParser;
	static Interact playerInteractor;
	
	public WorldImporter(File file) {
		reader = new JSONObject(file);
		roomList = reader.getJSONArray("roomList");
		for (int i = 0; i < roomList.length(); i++) {
			JSONObject room = roomList.getJSONObject(i);
			if (room.has("startingRoom")) {
				try {
					startingRoom = genericRoomFromJSON(room);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		playerState = new PlayerState(new Inventory(), startingRoom);
		playerInteractor = new Interact(playerState);
		playerParser = new Parser(playerState);
	}
	
	
	public static GenericRoom genericRoomFromJSON(JSONObject file) throws JSONException {
		String roomName = file.getString("roomName");
		String roomDescription = file.getString("roomDescription");
		String extendedRoomDescription = roomDescription;
		CompassExit exits = new CompassExit();
		try {
			exits = new CompassExit(file.getJSONObject("exits"));
		} catch (JSONException e) {
			System.out.println("No exits object found for " + roomName + ", creating empty exit instead");
		}
		GenericRoom room = new GenericRoom(roomName, roomDescription, new RoomInventory(), exits);
		room.setRoomDescription(extendedRoomDescription);
		try{
			JSONArray itemList = file.getJSONArray("itemList");
			for (int i = 0; i < itemList.length(); i++) {
				File itemFile = new File("sub1/items/" + itemList.getString(i));
				try {
					JSONObject item = Interpreter.JSONObjectFromFile(itemFile);
					room.addItem(new GenericItem(item));
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
}

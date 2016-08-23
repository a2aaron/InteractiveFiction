package world;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.*;

import character.Interact;
import character.Inventory;
import character.PlayerState;
import items.AbstractItem;
import rooms.GenericRoom;

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
					startingRoom = new GenericRoom(room);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		playerState = new PlayerState(new Inventory(), startingRoom);
		playerInteractor = new Interact(playerState);
		playerParser = new Parser(playerState);
	}
}

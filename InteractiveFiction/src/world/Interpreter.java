package world;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import character.Interact;
import character.Inventory;
import character.PlayerState;
import rooms.GenericRoom;
import types.Action;
import types.Action.MovementAdverb;

public class Interpreter {
	static PlayerState playerState;
	static Scanner playerInput;
	static Interact playerInteractor;

	public Interpreter() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		File file = new File("sub1/exits.json");
		JSONObject exits = null;
		try {
			exits = new JSONObject(new JSONTokener(new FileInputStream(file)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray nameList = exits.getJSONArray("nameList");
		HashMap<String, GenericRoom> rooms = new HashMap<String, GenericRoom>();
		
		GenericRoom startingRoom = null;
		for (int i = 0; i < nameList.length(); i++) {
			String internalRoomName = nameList.getString(i);
			File roomFile = new File("sub1/rooms/" + internalRoomName + ".json");
			GenericRoom room = null;
			try {
				JSONObject roomData = JSONObjectFromFile(roomFile);
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
		
		playerInput = new Scanner(System.in);
		playerState = new PlayerState(new Inventory(), startingRoom);
		playerInteractor = new Interact(playerState);
		Parser playerParser = new Parser(playerState);
		System.out.println("[GAME START]");
		while(playerInteractor.isPlaying()) {
			System.out.print("[" + playerState.getCurrentRoom().getRoomName() + "] > ");
			String input = playerInput.nextLine();
			Action action = playerParser.parseInput(input);
			playerInteractor.doAction(action);
		}
		
		System.out.println("Goodbye");
		playerInput.close();
	}
	
	public static JSONObject JSONObjectFromFile(File file) throws JSONException, FileNotFoundException  {
		return new JSONObject(new JSONTokener(new FileInputStream(file)));
	}
}

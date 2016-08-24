package world;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArraySet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import character.Interact;
import character.Inventory;
import character.PlayerState;
import items.Key;
import items.Lever;
import items.Lever.LeverPosition;
import items.LockedDoor;
import items.Vace;
import rooms.CompassExit;
import rooms.CompassRoom;
import rooms.GenericRoom;
import rooms.LockedDoorRoom;
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
//		CompassRoom startingRoom = new CompassRoom(
//				"Starting Room", "This is the room you start in!");
//		startingRoom.addItem(new Vace("TEST", "HELP"));
//		startingRoom.addItem(new Lever("Lever", LeverPosition.up));
//		
//		CompassRoom northRoom = new CompassRoom(
//				"North Room","It's the north pole!");
//		CompassRoom eastRoom = new CompassRoom(
//				"East Room", "Easter.");
//		CompassRoom westRoom = new CompassRoom(
//				"West Room", "Wester.");
//		
//		Key key = new Key("Key", "It's a key");
//		LockedDoor lockedDoor = new LockedDoor("Locked Door", key);
//
//		LockedDoorRoom southRoom = new LockedDoorRoom(
//				"South Room", "A southern farm.", lockedDoor, MovementAdverb.DOWN);
//		southRoom.addItem(key);
//		southRoom.addItem(lockedDoor);
//
//		CompassRoom lockedRoom = new CompassRoom("Locked Room", "Locks");
//		CompassExit.twoWayLink(MovementAdverb.DOWN, southRoom, lockedRoom);
//		
//		CompassExit.twoWayLink(MovementAdverb.UP, startingRoom, northRoom);
//		CompassExit.twoWayLink(MovementAdverb.RIGHT, startingRoom, eastRoom);
//		CompassExit.twoWayLink(MovementAdverb.LEFT, startingRoom, westRoom);
//		CompassExit.twoWayLink(MovementAdverb.DOWN, startingRoom, southRoom);
//		
////		startingRoom.twoSidedLink(MovementAdverb.north, northRoom);
////		startingRoom.twoSidedLink(MovementAdverb.east, eastRoom);
////		startingRoom.twoSidedLink(MovementAdverb.west, westRoom);
////		startingRoom.twoSidedLink(MovementAdverb.south, southRoom);
//		
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
				room = new GenericRoom(roomData);
			} catch (FileNotFoundException e) {
				System.out.println(roomFile.getName() + " missing. Creating blank room instead");
				room = new GenericRoom(internalRoomName, "");
			} catch (JSONException e) {
				e.printStackTrace();
			} finally {
				
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
			for (String key : exit.keySet()) {
				MovementAdverb direction = MovementAdverb.stringToAdverb(key);
				if (direction != null) {
					GenericRoom currentRoom = rooms.get(roomName); 
					GenericRoom exitRoom = rooms.get(exit.getString(key));
					currentRoom.getExits().addExit(direction, exitRoom);
					currentRoom.appendRoomDescription(key + "\n");
				}
			}
		}
		
		playerInput = new Scanner(System.in);
		playerState = new PlayerState(new Inventory(), startingRoom);
		playerInteractor = new Interact(playerState);
		Parser playerParser = new Parser(playerState);
		
		System.out.println(playerState.getCurrentRoom().getExtendedRoomDescription());
		
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

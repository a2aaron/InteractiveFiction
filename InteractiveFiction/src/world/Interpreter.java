package world;

import java.util.Scanner;
import character.Interact;
import character.Inventory;
import character.PlayerState;
import items.Key;
import items.Lever;
import items.Vace;
import items.Door.DoorState;
import items.Lever.LeverPosition;
import items.LockedDoor;
import rooms.CompassRoom;
import rooms.LockedDoorRoom;
import types.Action;
import types.Action.MovementAdverb;
import types.Directions.CompassDirections;

public class Interpreter {
	static PlayerState playerState;
	static Scanner playerInput;
	static Interact playerInteractor;

	public Interpreter() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		CompassRoom startingRoom = new CompassRoom(
				"Starting Room", "This is the room you start in!");
		startingRoom.addItem(new Vace("TEST", "HELP"));
		startingRoom.addItem(new Lever("Lever", "This is a lever!", LeverPosition.Up));
		
		CompassRoom northRoom = new CompassRoom(
				"North Room","It's the north pole!");
		CompassRoom eastRoom = new CompassRoom(
				"East Room", "Easter.");
		CompassRoom westRoom = new CompassRoom(
				"West Room", "Wester.");
		
		Key key = new Key("Key", "It's a key");
		LockedDoor lockedDoor = new LockedDoor("Locked Door", "Locked Door Description", 
				DoorState.Closed, key);

		LockedDoorRoom southRoom = new LockedDoorRoom(
				"South Room", "A southern farm.", lockedDoor, MovementAdverb.south);
		southRoom.addItem(key);
		southRoom.addItem(lockedDoor);

		CompassRoom lockedRoom = new CompassRoom("Locked Room", "Locks");
		southRoom.twoSidedLink(CompassDirections.South, lockedRoom);
		
		startingRoom.twoSidedLink(CompassDirections.North, northRoom);
		startingRoom.twoSidedLink(CompassDirections.East, eastRoom);
		startingRoom.twoSidedLink(CompassDirections.West, westRoom);
		startingRoom.twoSidedLink(CompassDirections.South, southRoom);
		
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
}

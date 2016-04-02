package world;

import java.util.Scanner;

import character.Movement;
import rooms.CompassRoom;
import types.Directions;
import types.Directions.CompassDirections;

public class Interpreter {
	public Interpreter() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Scanner playerInput = new Scanner(System.in);
		
		CompassRoom startingRoom = new CompassRoom(
				"Starting Room", "This is the room you start in!");
		CompassRoom northRoom = new CompassRoom(
				"North Room","It's the north pole!");
		CompassRoom eastRoom = new CompassRoom(
				"East Room", "Easter.");
		CompassRoom westRoom = new CompassRoom(
				"West Room", "Wester.");
		CompassRoom southRoom = new CompassRoom(
				"South Room", "A southern farm.");
		startingRoom.twoSidedLink(CompassDirections.North, northRoom);
		startingRoom.twoSidedLink(CompassDirections.East, eastRoom);
		startingRoom.twoSidedLink(CompassDirections.West, westRoom);
		startingRoom.twoSidedLink(CompassDirections.South, southRoom);
		
		Movement player = new Movement(startingRoom);
		Directions directionHelper = new Directions();
		boolean playing = true;
		while(playing) {
			System.out.println("You are in room: " + player.getCurrentRoomName());
			System.out.println("Enter a movement command.");
			String command = playerInput.nextLine().toLowerCase();
			if(directionHelper.isCompassDirection(command)) {
				CompassDirections direction = directionHelper.StringToDirection(command);
				if (player.canMove(direction)) {
					player.move(direction);	
				} else {
					System.out.println("Can't move there.");
				}
			} else {
				if (command.equals("quit")) {
					playing = false;
					playerInput.close();
					System.out.println("Quit. Goodbye");
				} else if (command.equals("help")) {
					System.out.println("Help? What help?");
				} else if (command.equals("look")) {
					String description = player.getCurrentRoom().getRoomDescription();
					System.out.println(description);
				} else {
					System.out.println("Invalid Command: [" + command +"]");
				}
			} 
		}		
	}
}

package world;

import java.util.ArrayList;
import java.util.Scanner;

import character.Inventory;
import character.Movement;
import rooms.CompassRoom;
import rooms.RoomInventory;
import types.AbstractItem;
import types.Directions;
import types.Directions.CompassDirections;
import types.Vace;

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
		Inventory playerInventory = new Inventory(null);
		Directions directionHelper = new Directions();
		boolean playing = true;
		while(playing) {
			System.out.print("[" + player.getCurrentRoomName() + "] > ");
			String command = playerInput.nextLine().toLowerCase();
			if(directionHelper.isCompassDirection(command)) {
				CompassDirections direction = directionHelper.StringToDirection(command);
				if (player.canMove(direction)) {
					player.move(direction);
				} else {
					System.out.println("Can't move there.");
				}
			} else {
				switch(command) {
				case "quit":
					playing = false;
					playerInput.close();
					System.out.println("Quit. Goodbye");
					break;
				case "help":
					System.out.println("Help? What help?");
					break;
				case "look":
					String description = player.getCurrentRoom().getRoomDescription();
					System.out.println("---[DESCRIPTION]---");
					System.out.println(description);
					System.out.println("-------------");
					System.out.println("---[ITEMS]---");
					ArrayList<AbstractItem> roomItems =
						player.getCurrentRoom()
						.getRoomInventory()
						.getInventoryList();
					
					for(AbstractItem item : roomItems) {
							System.out.println("[" + item.getName() + "]"); 
							System.out.println(item.getDescription());
					}
					System.out.println("-------------");
					break;
				case "i":
				case "inventory":
					System.out.println(playerInventory.getInventory());
					break;
				case "rename":
				case "rename room":
					System.out.println("Type a new name for the room.");
					String newName = playerInput.nextLine();
					player.getCurrentRoom().setRoomName(newName);
					break;
				case "add vace":
					System.out.println("Type color of vace");
					String color = playerInput.nextLine();
					player.getCurrentRoom().addItem(
							new Vace("Debug Vace", "Hello World!\n", color));
					break;
				case "break all":
					ArrayList<AbstractItem> breakItems = player.getCurrentRoom()
					.getRoomInventory()
					.getInventoryList();

					for(AbstractItem item : breakItems) {
						if (item instanceof Vace) {
							((Vace) item).breakVace();
							System.out.println("Broke: " + item.getName());
						}
					}
					break;
				default:
					System.out.println("Invalid Command: [" + command +"]");

				}
			} 
		}
	}
}

package world;

import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import character.Interact;
import character.Inventory;
import character.Movement;
import items.AbstractItem;
import items.IBreakableItem;
import items.Vace;
import rooms.CompassRoom;
import rooms.GenericRoom;
import rooms.RoomInventory;
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
		
		startingRoom.addItem(new Vace("TEST", "HELP"));
		
		Inventory playerInventory = new Inventory();
		Directions directionHelper = new Directions();
		Movement player = new Movement(startingRoom);
		Interact playerInteractor = new Interact(playerInventory);
		
		boolean playing = true;
		while(playing) {
			System.out.print("[" + player.getCurrentRoom().getRoomName() + "] > ");
			String command = playerInput.nextLine().toLowerCase();
			Inventory currentRoomInventory = player.getCurrentRoomInventory();
			GenericRoom currentRoom = player.getCurrentRoom();
			if(directionHelper.isCompassDirection(command)) {
				CompassDirections direction = directionHelper.StringToDirection(command);
				if (player.canMove(direction)) {
					player.move(direction);
				} else {
					System.out.println("Can't move there.");
				}
			} else {
				switch(command) {
				case "q":
				case "quit":
					playing = false;
					playerInput.close();
					System.out.println("Quit. Goodbye");
					break;
				case "h":
				case "help":
					System.out.println("Help? What help?");
					break;
				case "l":
				case "look":
					String description = player.getCurrentRoomDescription();
					System.out.println("---[DESCRIPTION]---");
					System.out.println(description);
					System.out.println("-------------\n");
					printItems(currentRoomInventory);
					break;
				case "i":
				case "inventory":
					printItems(playerInventory.getInventory());
					break;
				case "take":
				{
					printItems(currentRoom);
					String itemName = askForInput("Take what?", playerInput);
					if (currentRoomInventory.hasItemByName(itemName)) {
						AbstractItem item = currentRoomInventory.getItemByName(itemName);
						if(playerInteractor.canTakeItem(item)) {
							playerInteractor.takeItem(item, currentRoom);
							System.out.println("Took the " + itemName);
						}
					} else {
						System.out.println("Can't find " + itemName);
					}
				}
				break;
				case "take all":
				{
					CopyOnWriteArrayList<AbstractItem> inventoryList = currentRoomInventory.getInventoryList();
					if (currentRoomInventory.numberOfItems() != 0) {
						Iterator<AbstractItem> iter = inventoryList.iterator(); 
						while (iter.hasNext()) {
							AbstractItem item = iter.next();
							if (playerInteractor.canTakeItem(item)) {
								playerInteractor.takeItem(item, currentRoom);
								System.out.println("Took: " + item.getName());
							}
						}
					} else {
						System.out.println("No items to take.");
					}
					break;

				}
				case "break":
				{
					printItems(currentRoom);
					String itemName = askForInput("Break what?", playerInput);
					if (currentRoomInventory.hasItemByName(itemName)) {
						AbstractItem item = currentRoomInventory.getItemByName(itemName);
						playerInteractor.tryBreakItem(item);
					}
				}
				break;
				case "break all":
				{
					CopyOnWriteArrayList<AbstractItem> breakItems = currentRoomInventory.getInventoryList();
					for(AbstractItem item : breakItems) {
						if (item instanceof IBreakableItem) {
							playerInteractor.tryBreakItem(item);
							System.out.println("Broke: " + item.getName());
						}
					}
				}
				break;
				case "use":
				{
					if (playerInventory.numberOfItems() != 0) {
						printItems(playerInventory);
						String itemName = askForInput("Use what?", playerInput);
						if (playerInventory.hasItemByName(itemName)) {
							AbstractItem item = playerInventory.getItemByName(itemName);
							playerInteractor.useItem(item);
						} else {
							System.out.println("Can't find " + itemName);
						} 
					}
					break;
				}
				case "add":
				case "add vace":
				{
					System.out.println("Type color of vace");
					String color = playerInput.nextLine();
					currentRoom.addItem(new Vace("Debug Vace", color));
				}
				break;

				case "rename":
				case "rename room":
				{
					System.out.println("Type a new name for the room.");
					String newName = playerInput.nextLine();
					currentRoom.setRoomName(newName);
				}
				break;
				default:
					System.out.println("Invalid Command: [" + command +"]");
				}
			} 
		}
	}

	public static void printItems(GenericRoom room) {
		CopyOnWriteArrayList<AbstractItem> inventoryList = room.getRoomInventory().getInventoryList();
		printItems(inventoryList);
	}

	public static void printItems(Inventory inventory) {
		CopyOnWriteArrayList<AbstractItem> inventoryList = inventory.getInventoryList();
		printItems(inventoryList);
	}

	public static void printItems(CopyOnWriteArrayList<AbstractItem> inventoryList) {
		System.out.println("---[ITEMS]---");
		if (inventoryList.size() != 0) {
			for (AbstractItem item : inventoryList) {
				System.out.println("[" + item.getName() + "]");
				System.out.println(item.getDescription());
			} 
		} else {
			System.out.println("No items.");
		}
		System.out.println("-------------");		
	}

	public static String askForInput(String question, Scanner player) {
		System.out.println(question);
		return player.nextLine();
	}
}

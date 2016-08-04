package world;

import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import character.Interact;
import character.Inventory;
import character.Movement;
import items.AbstractItem;
import items.IBreakableItem;
import items.IItemUseableOn;
import items.Key;
import items.Lever;
import items.Vace;
import items.Door.DoorState;
import items.Lever.LeverPosition;
import items.LockedDoor;
import rooms.CompassRoom;
import rooms.GenericRoom;
import rooms.LockedDoorRoom;
import rooms.RoomInventory;
import types.Directions;
import types.Directions.CompassDirections;

public class Interpreter {
	static Scanner playerInput;
	static Inventory playerInventory;
	static Directions directionHelper;
	static Movement player;
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
				"South Room", "A southern farm.", lockedDoor, CompassDirections.South);
		southRoom.addItem(key);
		southRoom.addItem(lockedDoor);

		CompassRoom lockedRoom = new CompassRoom("Locked Room", "Locks");
		southRoom.twoSidedLink(CompassDirections.South, lockedRoom);
		
		startingRoom.twoSidedLink(CompassDirections.North, northRoom);
		startingRoom.twoSidedLink(CompassDirections.East, eastRoom);
		startingRoom.twoSidedLink(CompassDirections.West, westRoom);
		startingRoom.twoSidedLink(CompassDirections.South, southRoom);
		
		playerInput = new Scanner(System.in);
		playerInventory = new Inventory();
		directionHelper = new Directions();
		player = new Movement(startingRoom);
		playerInteractor = new Interact(playerInventory);
		
		
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
					currentRoom = player.getCurrentRoom();
					System.out.println(currentRoom.getRoomDescription());
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
					String description = currentRoom.getExtendedRoomDescription();
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
						} else {
						    System.out.println("Can't take the " + itemName);
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
						if (item instanceof IBreakableItem) {
						    if(((IBreakableItem) item).isBroken()) {
						        System.out.println(itemName + " is already broken!");
						    } else {
						        playerInteractor.tryBreakItem(item);
						    }
						} else {
						    System.out.println("Can't break the " + itemName);
						};
					}
				}
				break;
				case "break all":
				{
					CopyOnWriteArrayList<AbstractItem> breakItems = currentRoomInventory.getInventoryList();
					boolean allBroken = true; //Check if any item was broken
					for(AbstractItem item : breakItems) {
						if (item instanceof IBreakableItem && playerInteractor.tryBreakItem(item)) {
						    allBroken = false;
						}
					}
					if (allBroken) {
					    System.out.println("You already broke everything you could!");
					}
				}
				break;
				case "use":
				{
				    printItems(playerInventory);
				    printItems(currentRoomInventory);
				    String itemName = askForInput("Use what?", playerInput);
				    // Search player inventory, then room inventory for the item to use.
					if (playerInventory.hasItemByName(itemName)) {
						AbstractItem item = playerInventory.getItemByName(itemName);
						if (item instanceof IItemUseableOn) { // Item can be used on something else
							tryUseItemUseableOn(currentRoomInventory, item);
						} else {
							playerInteractor.useItem(item);
						}
					} else if (currentRoomInventory.hasItemByName(itemName)) {
					    AbstractItem item = currentRoomInventory.getItemByName(itemName);
						if (item instanceof IItemUseableOn) { // Item can be used on something else
							tryUseItemUseableOn(currentRoomInventory, item);
						} else {
							item.useItem();
						}
					} else {
					    System.out.println("Can't find that item");
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

	public static void tryUseItemUseableOn(Inventory currentRoomInventory, AbstractItem itemToUse) {
		if (itemToUse instanceof IItemUseableOn) { // Item can be used on something else
			String itemUsedOnName = askForInput("Use " + itemToUse.getName() + " on what?", playerInput);
			// Search player inventory, then search room inventory for the item to be used on. 
			if (playerInventory.hasItemByName(itemUsedOnName)) {
				AbstractItem itemUsedOn = playerInventory.getItemByName(itemUsedOnName);
				((IItemUseableOn) itemToUse).useItemOn(itemUsedOn);
			} else if (currentRoomInventory.hasItemByName(itemUsedOnName)) {
				AbstractItem itemUsedOn = currentRoomInventory.getItemByName(itemUsedOnName);
				((IItemUseableOn) itemToUse).useItemOn(itemUsedOn);
			} else {
				System.out.println("Can't find that item.");
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

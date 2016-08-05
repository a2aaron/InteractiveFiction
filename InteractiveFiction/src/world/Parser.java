package world;

import java.util.Scanner;

import character.Interact;
import character.Inventory;
import character.Movement;
import character.PlayerState;
import items.AbstractItem;
import items.IItemUseableOn;
import types.Directions;
import types.Action;
import types.Action.MovementAdverb;
import types.Action.Verb;

public class Parser {
	
	PlayerState playerState;
	Scanner playerInput = new Scanner(System.in);
	Directions directionHelper = new Directions();
	
	public Parser(PlayerState playerState) {
		this.playerState = playerState;
	}
	
	public Action parseInput(String input) {
		switch (input.toLowerCase()) {
		case "q":
		case "quit":
			return new Action(Verb.quit);
		case "i":
		case "inventory":
			return new Action(Verb.inventory);
		case "use":
		{
			playerState.getPlayerInventory().printItems();
			playerState.getCurrentRoomInventory().printItems();
			System.out.println("Which item?");
			String itemName = nextPlayerInput();
			return createActionWithDirectObjectItem(Verb.use, itemName);
		}
		case "take":
		{
			playerState.getCurrentRoomInventory().printItems();
			System.out.println("Which item?");
			String itemName = nextPlayerInput();
			AbstractItem item = playerState.getCurrentRoomInventory().getItemByName(itemName);
			return new Action(Verb.take, item); // item could be null!
		}
		case "break":
		case "destroy":
		{
			playerState.getPlayerInventory().printItems();
			playerState.getCurrentRoomInventory().printItems();
			System.out.println("Which item?");
			String itemName = nextPlayerInput();
			return createActionWithDirectObjectItem(Verb.destroy, itemName);
		}
		case "l":
		case "look":
			Action action = new Action(Verb.examineRoom, playerState.getCurrentRoom());
			return action;
		case "lookat":
		case "look at":
			playerState.getPlayerInventory().printItems();
			playerState.getCurrentRoomInventory().printItems();
			System.out.println("Which item?");
			String itemName = nextPlayerInput();
			return createActionWithDirectObjectItem(Verb.examineObject, itemName);
		case "n":
		case "north":
			return new Action(Verb.move, MovementAdverb.north);
		case "s":
		case "south":
			return new Action(Verb.move, MovementAdverb.south);
		case "w":
		case "west":
			return new Action(Verb.move, MovementAdverb.west);
		case "e":
		case "east":
			return new Action(Verb.move, MovementAdverb.east);
		default:
			return null;
		}
	}

	/* 
	 * Takes a verb and a string of an item and searches the player inventory
	 * and current room inventory and returns an action consisting of the verb
	 * and the item as the direct object. Used for "take", "use", and other 
	 * verbs which need a single direct object item.
	 */
	public Action createActionWithDirectObjectItem(Verb verb, String itemName) {
		AbstractItem itemPlayer = playerState.getPlayerInventory().getItemByName(itemName);
		AbstractItem itemRoom = playerState.getCurrentRoomInventory().getItemByName(itemName);
		if (itemPlayer != null) {
			return new Action(verb, itemPlayer);
		} else if (itemRoom != null) {
			return new Action(verb, itemRoom);
		} else {
			return new Action(verb); //directObject (the item) will be null
		}
	}
	
	public AbstractItem askForItem(Inventory inventory) {
		String itemName = playerInput.nextLine();
		return inventory.getItemByName(itemName);
	}
	
	public String nextPlayerInput() {
		return playerInput.nextLine().toLowerCase();
	}
}

package world;

import java.util.Scanner;

import character.Inventory;
import character.PlayerState;
import items.AbstractItem;
import types.Action;
import types.Action.MovementAdverb;
import types.Action.Verb;
import types.Directions;
import types.ItemAction;

public class Parser {
	
	PlayerState playerState;
	Scanner playerInput = new Scanner(System.in);
	Directions directionHelper = new Directions();
	
	public Parser(PlayerState playerState) {
		this.playerState = playerState;
	}
	
	public Action parseInput(String input) {
		switch (input.toLowerCase()) {
		// No Direct Object
		case "q":
		case "quit":
		case "i":
		case "inv":
		case "inventory":
			return new Action(Verb.stringToVerb(input));
		// Current Room as Direct Object
		case "l":
		case "look":
			return new Action(Verb.examineRoom, playerState.getCurrentRoom());
		// Item as Direct Object (Room and Player)
		case "use":
		case "break":
		case "destroy":
		case "lookat":
		case "look at":
		{
			playerState.getPlayerInventory().printItems();
			playerState.getCurrentRoomInventory().printItems();
			System.out.println("Which item?");
			String itemName = nextPlayerInput();
			return makeItemActionFromItemName(Verb.stringToVerb(input), itemName);
		}
		// Item as Direct Object (Room Only)
		case "take":
		{
			playerState.getCurrentRoomInventory().printItems();
			System.out.println("Which item?");
			String itemName = nextPlayerInput();
			AbstractItem item = playerState.getCurrentRoomInventory().getItemByName(itemName);
			return new ItemAction(Verb.take, item); // item could be null!
		}
		// Movement Verbs
		case "n":
		case "north":
		case "s":
		case "south":
		case "w":
		case "west":
		case "e":
		case "east":
			return new Action(Verb.move, MovementAdverb.stringToAdverb(input));
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
	public ItemAction makeItemActionFromItemName(Verb verb, String itemName) {
		AbstractItem itemPlayer = playerState.getPlayerInventory().getItemByName(itemName);
		AbstractItem itemRoom = playerState.getCurrentRoomInventory().getItemByName(itemName);
		if (itemPlayer != null) {
			return new ItemAction(verb, itemPlayer);
		} else if (itemRoom != null) {
			return new ItemAction(verb, itemRoom);
		} else {
			return new ItemAction(verb); //directObject (the item) will be null
		}
	}
	

	
	public String nextPlayerInput() {
		return playerInput.nextLine().toLowerCase();
	}
}

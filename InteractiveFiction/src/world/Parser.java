package world;

import java.util.Scanner;

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
			System.out.println("---[ITEMS]---");
			playerState.getPlayerInventory().printItems();
			playerState.getCurrentRoomInventory().printItems();
			System.out.println("-------------");
			System.out.println("Which item?");
			String itemName = nextInput();
			return new ItemAction(Verb.stringToVerb(input), playerState.searchPlayerAndRoomInventory(itemName));
		}
		// Item as Direct Object (Room Only)
		case "take":
		{
			System.out.println("---[ITEMS]---");
			playerState.getCurrentRoomInventory().printItems();
			System.out.println("Which item?");
			System.out.println("-------------");
			String itemName = nextInput();
			AbstractItem item = playerState.getCurrentRoomInventory().getItemByName(itemName);
			return new ItemAction(Verb.take, item); // item could be null!
		}
		// Two items, Direct and Indirect (Room and/or Player)
		case "useon":
		case "use on":
		{
			System.out.println("---[ITEMS]---");
			playerState.getPlayerInventory().printItems();
			playerState.getCurrentRoomInventory().printItems();
			System.out.println("-------------");
			System.out.println("Use which item?");
			AbstractItem itemUse = playerState.searchPlayerAndRoomInventory(nextInput());
			System.out.println("Use item on what?");
			AbstractItem itemUseOn = playerState.searchPlayerAndRoomInventory(nextInput());
			return new ItemAction(Verb.useOn, itemUse, itemUseOn);
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
	
	public String nextInput() {
		return playerInput.nextLine().toLowerCase();
	}
}

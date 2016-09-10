package world;

import java.util.Arrays;
import java.util.Scanner;

import character.PlayerState;
import inventory.Inventory;
import items.utility.GenericItem;
import types.Action;
import types.Action.MovementAdverb;
import types.Action.Verb;
import types.ItemAction;

public class Parser {
	
	PlayerState playerState;
	Scanner playerInput = new Scanner(System.in);
	
	
	public Parser() {
		this.playerState = new PlayerState();
	}
	
	public Parser(PlayerState playerState) {
		this.playerState = playerState;
	}
	
	public Action parseInput(String input) {
		// Split the input string into an array of words
		// "   take red ball   " -> ["take", "red", "ball"]
		// Assumed that the format is "[verb] [directObject] [indirectObject]"
		String[] splitInput = input.trim().toLowerCase().split(" ");

		// Handles "", " ", and other pure white space
		if (splitInput.length == 0) {
			return null;
		}
		
		// Only one should be not null
		Verb verb = Verb.stringToVerb(splitInput[0]);
		MovementAdverb direction = MovementAdverb.stringToAdverb(splitInput[0]);
		
		// Drop first element
		splitInput = Arrays.copyOfRange(splitInput, 1, splitInput.length);
				
		if (verb == null) {
			// Matches "north", "south", etc
			if (direction != null) {
				return new Action(Verb.move, direction);
			}
			// Complete gibberish
			else {
				return null;
			}
		}
		
		// I'm lazy and don't want to type as much
		Inventory playerAndRoom = playerState.getPlayerAndRoomInventory();

		switch (verb) {
		// No Direct Object
		case quit:
		case inventory:
			return new Action(verb);
		// Current Room as Direct Object
		case examineRoom:
			return new Action(verb, playerState.getCurrentRoom());
		// Item as Direct Object (Room and Player)
		case use:
		case destroy:
		case examineObject:
		{
			GenericItem item = scanStringForItem(splitInput, playerAndRoom);
			return new ItemAction(verb, item);
		}
		// Item as Direct Object (Room Only)
		case take:
		{
			GenericItem item = scanStringForItem(splitInput, playerState.getCurrentRoomInventory());
			return new ItemAction(verb, item);
		}
		// Two items, Direct and Indirect (Room and/or Player)
		case useOn:
		{
			try {
				GenericItem itemUse = scanStringForItem(splitInput, playerAndRoom);
				
				// Drop the words of the first item
				int lastIndex = getFirstIndexOfNextItem(splitInput, playerAndRoom);
				// No item found
				if (lastIndex == -1) {
					return new ItemAction(verb, null, null);
				}
				// Item found but no next word
				else if (lastIndex == -2){
					return new ItemAction(verb, itemUse, null);
				}
				splitInput = Arrays.copyOfRange(splitInput, lastIndex, splitInput.length);
				GenericItem itemUseOn = scanStringForItem(splitInput, playerAndRoom);
				return new ItemAction(verb, itemUse, itemUseOn);
			} catch (ArrayIndexOutOfBoundsException e) {
				return new ItemAction(verb, null, null);
			}
		}
		// Movement Verbs
		case move:
			direction = MovementAdverb.stringToAdverb(splitInput[0]);
			return new Action(verb, direction);
		// Invalid
		default:
			return null;
		}
	}
	
	public GenericItem scanStringForItem(String string, Inventory inventory) {
		return scanStringForItem(string.split(" "), inventory);
	}
	
	/* *
	 * This funciton searches for the first item in an array and returns
	 * the item.
	 * Thus, "take small vace" should return a vace whose name is "small vace"
	 * 
	 * 
	 */
	
	public GenericItem scanStringForItem(String[] splitString, Inventory inventory) {
		// This for loop searches [1, 2, 3] as so:
		// [1], [1, 2], [1, 2, 3]
		// [2], [2, 3]
		// [3]
		// This matches the first and shortest possible instance of an item
		for (int i = 0; i <= splitString.length; i++) { 
			for (int itemLength = 1; itemLength <= splitString.length - i; itemLength++) {
				// From a slice of the array, join the elements with spaces in between and search for the item
				String itemName = String.join(" ", Arrays.copyOfRange(splitString, i, i+itemLength));
				GenericItem item = inventory.getItemByName(itemName);
				if (item != null) {
					return item;
				}
			}
		}
		return null;
	}
	/* *
	 * This function searches for the first item in an array and
	 * returns the index that is just after the last word of the item.
	 * 
	 * Thus, "small vace from shelf" should return 3, which is the index of
	 * the first word that is NOT the item
	 * 
	 * Returns -1 on failure due to no item found.
	 * Returns -2 on failure due to no next index
	 * (for example, "small vace" returns -2 because there is no next word)
	 * 
	 */
	public Integer getFirstIndexOfNextItem(String[] splitString, Inventory inventory) {
		// This for loop searches [1, 2, 3] as so:
		// [1], [1, 2], [1, 2, 3]
		// [2], [2, 3]
		// [3]
		// This matches the first and shortest possible instance of an item
		for (int i = 0; i <= splitString.length; i++) { 
			for (int itemLength = 1; itemLength <= splitString.length - i; itemLength++) {
				// From a slice of the array, join the elements with spaces in between and search for the item
				String itemName = String.join(" ", Arrays.copyOfRange(splitString, i, i+itemLength));
				GenericItem item = inventory.getItemByName(itemName);
				if (item != null) {
					if (i + itemLength > splitString.length) {
						return -2;
					} else {
						return i + itemLength;
					}
				}
			}
		}
		return -1;
	}
	
	public String nextInput() {
		return playerInput.nextLine().toLowerCase();
	}
}

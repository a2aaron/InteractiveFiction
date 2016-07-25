package items;

import character.Inventory;

public class Vace extends AbstractItem implements IBreakableItem, ITakeableItem {
	boolean isBroken = false;
	boolean isTakeable = true;
	String color;
	String description;
	//String name;
	
	public Vace(String name, String description, String color) {
		super(name, description);
		this.color = color;
		this.description = description 
				+ "This vace is the color " + color;
	}
	
	public Vace(String name, String color) {
		this(name, "", color);
	}
	
	public void useItem(String command) { // TODO: actually use this
		command = command.toLowerCase();
		if (command == "examine " + name ||
			command == "look " + name) {
			getDescription();
		} else if (command == "break " + name && isBroken == false) {
			breakItem();
		} else {
			System.out.println("Invalid command: " + command);
		}
	}
	
	public void breakItem() {
		isBroken = true;
		System.out.println("You break the " + name + ".");
		setDescription("This vace is broken");
	}

	@Override
	public void useItem() {
		// TODO: Should this do anything else?
		breakItem();
	}
	
	public void takeItem(Inventory destination) {
		// TODO: create an event based system where
		// calling this method returns some event (look up Java events?)
		// and we do stuff with that (ex: return success event, failure event, trap event).
	}
	
	public boolean isTakeable() {
		return isTakeable;
	}

	public boolean isBroken() {
		return isBroken;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String itemDescription) {
		this.description = itemDescription;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}

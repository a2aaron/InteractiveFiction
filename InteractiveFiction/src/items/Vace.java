package items;

public class Vace extends BreakableItem {
	boolean isBroken = false;
	String color;
	String itemDescription;
	
	public Vace(String name, String description, String color) {
		super(name, description);
		this.color = color;
		itemDescription = description 
				+ "This vace is the color " + color;
		itemName = name;
	}
	
	public Vace(String name, String color) {
		this(name, "", color);
	}
	
	public String getColor() {
		return color;
	}
		
	public void useItem(String command) {
		command = command.toLowerCase();
		if (command == "examine " + itemName ||
			command == "look " + itemName) {
			getDescription();
		} else if (command == "break " + itemName && isBroken == false) {
			breakItem();
		} else {
			System.out.println("Invalid command: " + command);
		}
	}
	
	@Override
	public void breakItem() {
		isBroken = true;
		setDescription("This vace is broken");
	}

	@Override
	public void useItem() {
		// TODO: Should this do anything else?
		breakItem();		
	}

	public String getDescription() {
		return itemDescription;
	}
	
	public void setDescription(String newDescription) {
		itemDescription = newDescription;
	}

}

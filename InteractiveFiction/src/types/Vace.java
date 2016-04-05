package types;

public class Vace extends AbstractItem {

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
	
	public String getColor() {
		return color;
	}
	
	public String getDescription() {
		return itemDescription;
	}

	public void setDescription(String newDescription) {
		itemDescription = newDescription;
	}
	
	public void useItem(String command) {
		command = command.toLowerCase();
		if (command == "examine " + itemName ||
			command == "look " + itemName) {
			getDescription();
		} else if (command == "break " + itemName && isBroken == false) {
			breakVace();
		} else {
			System.out.println("Invalid command: " + command);
		}
	}
	
	public void breakVace() {
		isBroken = true;
		setDescription("This vace is broken");
	}

	@Override
	public void useItem() {
		// TODO: Should this do anything else?
		breakVace();
	}
}

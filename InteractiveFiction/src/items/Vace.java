package items;

import org.json.JSONObject;

import character.Inventory;

public class Vace extends GenericTakeableItem implements IBreakableItem {
	boolean isBroken = false;
	boolean isTakeable = true;
	String color = "";
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
	
	public Vace() {
		super();
	}
	
	public Vace(JSONObject item) throws Exception {
		super(item);
	}

	public void useItem() { // TODO: actually use this
		isBroken = true;
		//System.out.println("You break the " + name + ".");
		setDescription("This vace is broken");
	}

	public boolean isBroken() {
		return isBroken;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void breakItem() {
		useItem();
	}
}

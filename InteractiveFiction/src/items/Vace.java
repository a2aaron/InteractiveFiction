package items;

import org.json.JSONObject;

import inventory.Inventory;
import items.utility.GenericTakeableItem;
import items.utility.IBreakableItem;

public class Vace extends GenericTakeableItem implements IBreakableItem {
	boolean isBroken = false;
	boolean isTakeable = true;
	String color = "";
	//String name;
	
	public Vace(String name, String description, String color) {
		super(name, description + "This vace is the color " + color);
		this.color = color;
	}
	
	public Vace(String name, String color) {
		this(name, "", color);
	}
	
	public Vace() {
		super();
	}

	@Override
	public void breakItem() {
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
}

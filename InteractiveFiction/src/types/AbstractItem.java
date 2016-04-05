package types;

public abstract class AbstractItem {
	String itemName;
	String itemDescription;
	
	public AbstractItem(String name, String description) {
		itemName = name;
		itemDescription = description;
	}
	
	
	public String getDescription() { return itemDescription; }
	public String getName() { return itemName; }
	abstract void useItem();
}

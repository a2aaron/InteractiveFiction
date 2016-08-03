package items;

public abstract class AbstractItem {
	String name;
	String description;
	
	public AbstractItem(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getDescription() { return description; }
	public String getName() { return name; }
	public abstract void useItem();
	
	public void setName(String itemName) {
		this.name = itemName;
	}
	
	public void setDescription(String itemDescription) {
		this.description = itemDescription;
	}
}

package items;


import org.json.JSONException;
import org.json.JSONObject;

public class GenericItem {
	String name = "";
	String description = "";
	
	public GenericItem(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public GenericItem(JSONObject item) throws JSONException {
		name = item.getString("itemName");
		description = item.getString("itemDescription");
	}
	
	public GenericItem() {   }

	public String getDescription() { return description; }
	public String getName() { return name; }
	
	public void setName(String itemName) {
		this.name = itemName;
	}
	
	public void setDescription(String itemDescription) {
		this.description = itemDescription;
	}
}

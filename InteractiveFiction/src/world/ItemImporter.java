package world;

import org.json.JSONObject;

import items.*;

public class ItemImporter {
	public static Class determineClass(JSONObject item) {
		if (item.has("takeable")) { 
			return items.GenericTakeableItem.class;
		}
		return items.GenericItem.class;
	}
	
	public static GenericItem createItem(JSONObject item) {
		Class itemClass = determineClass(item);
		
		String name = item.getString("itemName");
		String description = item.getString("itemDescription");
		
		if (itemClass == items.GenericTakeableItem.class) {
			return new GenericTakeableItem(name, description);
		} else {
			return new GenericItem(name, description);
		}
	}
}

package world;

import org.json.JSONObject;

import items.*;

public class ItemImporter {
	public static Class determineClass(JSONObject item) {
		if (item.has("takeable")) { 
			return items.GenericTakeableItem.class;
		} else if (item.has("useable")) {
			JSONObject useable = item.getJSONObject("useable");
			if (fieldEqualsObject(useable, "type", "lever")) {
				if (fieldEqualsObject(useable, "onetime", true)) {
					return items.OneTimeLever.class;
				} else {
					return items.Lever.class;	
				}
			}
			return items.IUseableItem.class;
		}
		return items.GenericItem.class;
	}
	
	public static GenericItem createItem(JSONObject item) {
		Class itemClass = determineClass(item);
		
		String name = item.getString("itemName");
		String description = item.getString("itemDescription");
		
		if (itemClass == items.GenericTakeableItem.class) {
			return new GenericTakeableItem(name, description);
		} else if (itemClass == items.OneTimeLever.class){
			return new OneTimeLever(name, description);
		} else if (itemClass == items.Lever.class) {
			return new Lever(name, description);
		}
		return new GenericItem(name, description);
	}
	
	/**
	 * Returns true if the JSON object has field and that field is equal to an object
	 * Returns false if the field does not exist or if the field is not equal.
	 */
	public static boolean fieldEqualsObject(JSONObject json, String field, Object obj) {
		if (json.has(field)) {
			return json.get(field).equals(obj);
		} else {
			return false;
		}
	}
}

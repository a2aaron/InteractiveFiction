package rooms;

import java.util.EnumMap;

public abstract class Exit<K extends Enum<K>, RoomType> {
	
	EnumMap<K, GenericRoom> exits;
	
	public Exit(EnumMap<K, GenericRoom> exits) {
		this.exits = exits;
	}
	
	public Exit() {   }
	
	public EnumMap<K, GenericRoom> getExitSet() {
		return exits;
	}
	
	public boolean hasExit(GenericRoom room) {
		return exits.containsValue(room);
	}
	
	public GenericRoom getExitByName(String name) {
		for (GenericRoom room : exits.values()) {
			if (name.toLowerCase() == room.getRoomName().toLowerCase()) {
				return room;
			}
		}
		
		return null;
	}
	
	public void addExit(K e, GenericRoom room) {
		exits.put(e, room);
	}
	
	public GenericRoom get(K e) {
		return exits.get(e);
	}
}
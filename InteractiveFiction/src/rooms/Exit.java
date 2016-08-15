package rooms;

import java.util.concurrent.CopyOnWriteArraySet;

public class Exit {
	
	CopyOnWriteArraySet<GenericRoom> exits;
	
	public Exit(CopyOnWriteArraySet<GenericRoom> exits) {
		this.exits = exits;
	}
	
	public Exit() {
		this.exits = new CopyOnWriteArraySet<GenericRoom>();
	}
	
	public CopyOnWriteArraySet<GenericRoom> getExitSet() {
		return exits;
	}
	
	public boolean hasExit(GenericRoom room) {
		return exits.contains(room);
	}
	
	public GenericRoom getExitByName(String name) {
		for (GenericRoom room : exits) {
			if (name.toLowerCase() == room.getRoomName().toLowerCase()) {
				return room;
			}
		}
		
		return null;
	}
	
	public void addExit(GenericRoom room) {
		exits.add(room);
	}
	
	public static void twoWayLink(GenericRoom room1, GenericRoom room2) {
		room1.getExits().addExit(room2);
		room1.getExits().addExit(room1);
	}
	
	public static void oneWayLink(GenericRoom from, GenericRoom to) {
		from.getExits().addExit(to);
	}
}
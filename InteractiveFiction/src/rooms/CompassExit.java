package rooms;

import java.util.EnumMap;

import org.json.JSONObject;

import types.Action.MovementAdverb;

public class CompassExit {

	EnumMap<MovementAdverb, GenericRoom> exits = new EnumMap<MovementAdverb, GenericRoom>(MovementAdverb.class);
	
	public CompassExit() {   }
	
	public CompassExit(JSONObject exit) {
		for (String key : exit.keySet()) {
			// Note that this will ignore the "roomName" key even if it exists.
			if (MovementAdverb.stringToAdverb(key) != null) {
				addExit(MovementAdverb.stringToAdverb(key), exit.getString(key));
			}
		}
	}
	
	public void addExit(MovementAdverb direction, GenericRoom room) {
		exits.put(direction, room);
	}
	
	public void addExit(MovementAdverb direction, String roomName) {
		exits.put(direction, new GenericRoom(roomName, ""));
	}
	
	public GenericRoom get(MovementAdverb direction) {
		return exits.get(direction);
	}
	
	public void linkRoomTo(MovementAdverb direction, GenericRoom room) {
		addExit(direction, room);
	}
	 
	public static void twoWayLink(MovementAdverb fromDirection, CompassRoom from, CompassRoom to) {
		from.getExit().addExit(fromDirection, to);
		to.getExit().addExit(MovementAdverb.getOpposite(fromDirection), from);
	}
	
	public static void oneWayLink(MovementAdverb fromDirection, CompassRoom from, CompassRoom to) {
		from.getExit().addExit(fromDirection, to);
	}
}

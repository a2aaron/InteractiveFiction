package rooms;

public class CompassRoom {
/**
 * TODO: Make CompassRoom a subclass of a generic room class
 * CompassRoom has four exits, north, south, east, and west.
 * These exits should be other rooms. Note that CompassRooms do not
 * have to have Eucledian geometry.
 */
	String roomName;
	
	// TODO: This would be much better as an Enum.
	CompassRoom northExit;
	CompassRoom eastExit;
	CompassRoom southExit;
	CompassRoom westExit;

	public CompassRoom(String name, CompassRoom n, CompassRoom e, CompassRoom s, CompassRoom w) {
		this.roomName = name;
		this.northExit = n;
		this.eastExit = e;
		this.southExit = s;
		this.westExit = w;
	}
	
	public CompassRoom(String name) {
		this.roomName = name;
	}
	
	public void linkRoom(String direction, CompassRoom room) {
		switch(direction) {
		case "north":
			northExit = room;
		case "east":
			eastExit = room;
		case "south":
			southExit = room;
		case "west":
			westExit = room;
		}
	}
	
	public CompassRoom getExitRoom(String direction) {
		switch(direction) {
			case "north":
				return northExit;
			case "east":
				return eastExit;
			case "south":
				return southExit;
			case "west":
				return westExit;
		}
		
		// If none of the above apply
		return null;
	}
	public void setRoomName(String name){
		roomName = name;
	}
	public String getRoomName(){
		return roomName;
	}
}

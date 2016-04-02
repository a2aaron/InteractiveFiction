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
	CompassRoom northExit = null;
	CompassRoom eastExit = null;
	CompassRoom southExit = null;
	CompassRoom westExit = null;

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

	/**
	 * Link a room from this room to another room 
	 * */
	public void linkTo(String direction, CompassRoom room) {
		switch(direction.toLowerCase()) {
		case "north":
			northExit = room;
			break;
		case "east":
			eastExit = room;
			break;
		case "south":
			southExit = room;
			break;
		case "west":
			westExit = room;
			break;
		}
	}

	public void twoSidedLink(String direction, CompassRoom room) {
		linkTo(direction, room);
		room.linkTo(getReverseDirection(direction), this);
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

	//TODO: Make direction it's own data type, probably.
	public String getReverseDirection(String direction) {
		switch(direction) {
		case "north":
			return "south";
		case "east":
			return "west";
		case "south":
			return "north";
		case "west":
			return "east";
			//		case "left":
			//			return "right";
			//		case "right":
			//			return "left";
			//		case "up":
			//			return "down";
			//		case "down":
			//			return "up";
			//		case "forwards":
			//			return "backwards";
			//		case "backwards":
			//			return "forward";
		default:
			return "none";
		}
	}

}

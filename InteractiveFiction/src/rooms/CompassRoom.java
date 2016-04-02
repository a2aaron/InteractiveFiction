package rooms;

import types.Directions.CompassDirections;

public class CompassRoom{
	/**
	 * TODO: Make CompassRoom a subclass of a generic room class
	 * CompassRoom has four exits, north, south, east, and west.
	 * These exits should be other rooms. Note that CompassRooms do not
	 * have to have Eucledian geometry.
	 */
	String roomName;
	String roomDescription;
	// TODO: This would be much better as an Enum.
	CompassRoom northExit, eastExit, southExit, westExit = null;
	

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

	public CompassRoom(String name, String description) {
		this.roomName = name;
		this.roomDescription = description;
	}
	
	/**
	 * Link a room from this room to another room 
	 * */
	public void linkTo(CompassDirections direction, CompassRoom room) {
		switch(direction) {
		case North:
			northExit = room;
			break;
		case East:
			eastExit = room;
			break;
		case South:
			southExit = room;
			break;
		case West:
			westExit = room;
			break;
		}
	}

	public void twoSidedLink(CompassDirections direction, CompassRoom room) {
		linkTo(direction, room);
		room.linkTo(direction.getOpposite(direction), this);
	}

	public CompassRoom getExitRoom(CompassDirections direction) {
		switch(direction) {
		case North:
			return northExit;
		case East:
			return eastExit;
		case South:
			return southExit;
		case West:
			return westExit;
		default:
			return null;
		}
	}

	public String getRoomName(){
		return roomName;
	}

	public String getRoomDescription() {
		return roomDescription;
		
	}
	
	public void setRoomName(String name){
		roomName = name;
	}
	
	public void setRoomDescription(String description) {
		roomDescription = description;
	}
}

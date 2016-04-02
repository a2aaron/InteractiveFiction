package character;

import rooms.CompassRoom;

public class Movement {
	CompassRoom currentRoom;
	public Movement(CompassRoom room) {
		this.currentRoom = room;
	}
	/**
	 * Returns true on success and
	 * false on failure to move.
	 */
	public boolean canMove(String direction) {
		if (isMovement(direction)) {
			if (currentRoom.getExitRoom(direction) == null) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	public boolean isMovement(String direction) {
		switch(direction) {
		case "north":
			return true;
		case "east":
			return true;
		case "south":
			return true;
		case "west":
			return true;
		default:
			return false;
		}
	}
	
	public void move(String direction) {
		if (canMove(direction) == true) {
			currentRoom = currentRoom.getExitRoom(direction);
		} else {
			// Can't move, just ignore.
		}
	}
	
	public CompassRoom getCurrentRoom() {
		return currentRoom;
	}
	
	public String getCurrentRoomName() {
		return currentRoom.getRoomName();
	}
	
}

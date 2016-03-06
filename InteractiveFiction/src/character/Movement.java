package character;

import rooms.CompassRoom;

public class Movement {
	CompassRoom currentRoom;
	public Movement(CompassRoom room) {
		this.currentRoom = room;
	}
	/**
	 * Returns 0 on success and
	 * -1 on failure to move.
	 */
	public boolean canMove(String direction) {
		if (currentRoom.getExitRoom(direction) == null) {
			return false;
		} else {
			return true;
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

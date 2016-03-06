package character;

import rooms.CompassRoom;

public class Movement {
	CompassRoom currentRoom;
	public Movement(CompassRoom room) {
		this.currentRoom = room;
	}
	
	public void move(String direction) {
			currentRoom = currentRoom.getExitRoom(direction); 
	}
	
	public CompassRoom getCurrentRoom() {
		return currentRoom;
	}
	
	public String getCurrentRoomName() {
		return currentRoom.getRoomName();
	}
	
}

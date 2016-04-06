package character;

import java.util.ArrayList;

import rooms.CompassRoom;
import types.AbstractItem;
import types.Directions;
import types.Directions.CompassDirections;

public class Movement {
	Directions directionHelper = new Directions();
	CompassRoom currentRoom;
	public Movement(CompassRoom room) {
		this.currentRoom = room;
	}
	/**
	 * Returns true on success and
	 * false on failure to move.
	 */
	public boolean canMove(CompassDirections direction) {
		if (directionHelper.isCompassDirection(direction)) {
			if (currentRoom.getExitRoom(direction) == null) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public void move(CompassDirections directions) {
		if (canMove(directions) == true) {
			currentRoom = currentRoom.getExitRoom(directions);
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

	public String getCurrentRoomDescription() { 
		return currentRoom.getRoomDescription();
	}

	public Inventory getCurrentRoomInventory() {
		return currentRoom.getRoomInventory();
	}
	
	public ArrayList<AbstractItem> getCurrentRoomInventoryList() {
		return currentRoom.getRoomInventoryList();
	}
}

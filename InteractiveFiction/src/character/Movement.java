package character;


import java.util.concurrent.CopyOnWriteArrayList;

import items.AbstractItem;
import rooms.CompassRoom;
import types.Action.MovementAdverb;
import types.Directions;

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
	public boolean canMove(MovementAdverb direction) {
		if (currentRoom.getExitRoom(direction) == null) {
			return false;
		} else {
			return true;
		}
	}

	public void move(MovementAdverb directions) {
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
	
	public CopyOnWriteArrayList<AbstractItem> getCurrentRoomInventoryList() {
		return currentRoom.getRoomInventoryList();
	}
}

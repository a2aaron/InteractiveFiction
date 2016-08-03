package rooms;

import items.Door.DoorState;
import types.Directions.CompassDirections;
import items.LockedDoor;

public class LockedDoorRoom extends CompassRoom {
	LockedDoor lockedDoor;
	CompassDirections lockedDoorDirection; //Direction the locked door will attach to
	RoomInventory roomInventory = new RoomInventory();

	public LockedDoorRoom(String name, String description, LockedDoor lockedDoor, CompassDirections lockedDoorDirection) {
		super(name, description);
		this.lockedDoor = lockedDoor;
		this.lockedDoorDirection = lockedDoorDirection;
		roomInventory.addItem(lockedDoor);
	}
	
	public CompassRoom getExitRoom(CompassDirections direction) {
		if (direction == lockedDoorDirection && lockedDoor.getDoorState() == DoorState.Closed) {
			return null;
		} else {
			return super.getExitRoom(direction);
		}
	}
}

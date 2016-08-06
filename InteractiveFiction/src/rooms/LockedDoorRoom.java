package rooms;

import items.Door.DoorState;
import items.LockedDoor;
import types.Action.MovementAdverb;

public class LockedDoorRoom extends CompassRoom {
	LockedDoor lockedDoor;
	MovementAdverb lockedDoorDirection; //Direction the locked door will attach to
	RoomInventory roomInventory = new RoomInventory();

	public LockedDoorRoom(String name, String description, LockedDoor lockedDoor, MovementAdverb lockedDoorDirection) {
		super(name, description);
		this.lockedDoor = lockedDoor;
		this.lockedDoorDirection = lockedDoorDirection;
		roomInventory.addItem(lockedDoor);
	}
	
	public CompassRoom getExitRoom(MovementAdverb direction) {
		if (direction == lockedDoorDirection && lockedDoor.getDoorState() == DoorState.closed) {
			System.out.println("The door is closed! Open it!");
			return null;
		} else {
			return super.getExitRoom(direction);
		}
	}
}

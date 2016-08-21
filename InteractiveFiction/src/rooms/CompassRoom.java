package rooms;

import items.Vace;
import types.Action.MovementAdverb;

public class CompassRoom extends GenericRoom {
	/**
	 * CompassRoom has four exits, north, south, east, and west.
	 * These exits should be other rooms. Note that CompassRooms do not
	 * have to have Eucledian geometry.
	 */
	String extendedRoomDescrption;
	// TODO: Change to entrances and exits.
//	CompassRoom northExit, eastExit, southExit, westExit = null;
	CompassExit exit = new CompassExit();

	public CompassRoom() {
		super();
	}
	
	public CompassRoom(String name) {
		super(name, null);
	}

	public CompassRoom(String name, String description) {
		super(name, description);
		roomInventory = new RoomInventory();
		// [DEBUG]
		addItem(new Vace(name + " Vace", "green"));
		roomDescription = "You are in the " + name + ".\n";
		extendedRoomDescription = description;
	}
	
	public GenericRoom getExitRoom(MovementAdverb direction) {
		return exit.get(direction);
//		switch(direction) {
//		case north:
//			if (northExit == null) {
//				System.out.println("Can't move there.");
//			}
//			return northExit;
//		case east:
//			if (eastExit == null) {
//				System.out.println("Can't move there.");
//			}
//			return eastExit;
//		case south:
//			if (southExit == null) {
//				System.out.println("Can't move there.");
//			}
//			return southExit;
//		case west:
//			if (westExit == null) {
//				System.out.println("Can't move there.");
//			}
//			return westExit;
//		default:
//			return null;
//		}
	}
	
	public CompassExit getExit() {
		return exit;
	}
}

package rooms;

import java.util.concurrent.CopyOnWriteArrayList;

import items.AbstractItem;
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
	CompassRoom northExit, eastExit, southExit, westExit = null;
	

	public CompassRoom(String name, String description, CompassRoom n, CompassRoom e, CompassRoom s, CompassRoom w) {
		super(name, description);
		this.northExit = n;
		this.eastExit = e;
		this.southExit = s;
		this.westExit = w;
	}

	public CompassRoom() {
		super();
	}
	
	public CompassRoom(String name) {
		super(name, null);
	}

	public CompassRoom(String name, String description) {
		super(name, description);
		roomInventory = new RoomInventory();
		addItem(new Vace(name + " Vace", "green"));
		roomDescription = "You are in the " + name + ".\n";
		extendedRoomDescription = description;
	}
	
	/**
	 * Link a room from this room to another room 
	 * */
	public void linkRoomTo(MovementAdverb direction, CompassRoom room) {
		switch(direction) {
		case north:
			northExit = room;
			break;
		case east:
			eastExit = room;
			break;
		case south:
			southExit = room;
			break;
		case west:
			westExit = room;
			break;
		}
	}

	public void twoSidedLink(MovementAdverb direction, CompassRoom room) {
		linkRoomTo(direction, room);
		room.linkRoomTo(MovementAdverb.getOpposite(direction), this);
	}

	public CompassRoom getExitRoom(MovementAdverb direction) {
		switch(direction) {
		case north:
			if (northExit == null) {
				System.out.println("Can't move there.");
			}
			return northExit;
		case east:
			if (eastExit == null) {
				System.out.println("Can't move there.");
			}
			return eastExit;
		case south:
			if (southExit == null) {
				System.out.println("Can't move there.");
			}
			return southExit;
		case west:
			if (westExit == null) {
				System.out.println("Can't move there.");
			}
			return westExit;
		default:
			return null;
		}
	}
}

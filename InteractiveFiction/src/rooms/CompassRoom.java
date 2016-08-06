package rooms;

import java.util.concurrent.CopyOnWriteArrayList;

import items.AbstractItem;
import items.Vace;
import types.Action.MovementAdverb;
import types.Directions.CompassDirections;

public class CompassRoom extends GenericRoom {
	/**
	 * CompassRoom has four exits, north, south, east, and west.
	 * These exits should be other rooms. Note that CompassRooms do not
	 * have to have Eucledian geometry.
	 */
	String roomName;
	String roomDescription;
	String extendedRoomDescrption;
	// TODO: Change to entrances and exits.
	CompassRoom northExit, eastExit, southExit, westExit = null;
	RoomInventory roomInventory;
	

	public CompassRoom(String name, String description, CompassRoom n, CompassRoom e, CompassRoom s, CompassRoom w) {
		super(name, description);
		this.roomName = name;
		this.northExit = n;
		this.eastExit = e;
		this.southExit = s;
		this.westExit = w;
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
	public void linkRoomTo(CompassDirections direction, CompassRoom room) {
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
		linkRoomTo(direction, room);
		room.linkRoomTo(direction.getOpposite(direction), this);
	}

	public CompassRoom getExitRoom(MovementAdverb direction) {
		switch(direction) {
		case north:
			return northExit;
		case east:
			return eastExit;
		case south:
			return southExit;
		case west:
			return westExit;
		default:
			return null;
		}
	}
	
	public RoomInventory getRoomInventory() {
		return roomInventory;
	}
	
	public CopyOnWriteArrayList<AbstractItem> getRoomInventoryList() {
		return roomInventory.getInventoryList();
	}
	
	public void addItem(AbstractItem item) {
		roomInventory.addItem(item);
	}
}

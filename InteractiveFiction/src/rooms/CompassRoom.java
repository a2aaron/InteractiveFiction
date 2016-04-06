package rooms;

import types.Directions.CompassDirections;

import java.util.ArrayList;

import items.AbstractItem;
import items.Vace;

public class CompassRoom extends GenericRoom{
	/**
	 * TODO: Make CompassRoom a subclass of a generic room class
	 * CompassRoom has four exits, north, south, east, and west.
	 * These exits should be other rooms. Note that CompassRooms do not
	 * have to have Eucledian geometry.
	 */
	String roomName;
	String roomDescription;
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
		roomInventory = new RoomInventory(
				new Vace(name + " Vace", "green"));
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

	public CompassRoom getExitRoom(CompassDirections direction) {
		switch(direction) {
		case North:
			return northExit;
		case East:
			return eastExit;
		case South:
			return southExit;
		case West:
			return westExit;
		default:
			return null;
		}
	}
	
	public RoomInventory getRoomInventory() {
		return roomInventory;
	}
	
	public ArrayList<AbstractItem> getRoomInventoryList() {
		return roomInventory.getInventoryList();
	}
	
	public void addItem(AbstractItem item) {
		roomInventory.addItem(item);
	}
}

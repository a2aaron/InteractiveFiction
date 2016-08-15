package rooms;

import types.Action.MovementAdverb;

public class CompassExit extends Exit {

	//CopyOnWriteArraySet<>
	
	GenericRoom northExit;
	GenericRoom eastExit;
	GenericRoom southExit;
	GenericRoom westExit;
	
	public CompassExit() {
		super();
	}
	
	public void addExit(MovementAdverb direction, GenericRoom room) {
		switch(direction) {
		case east:
			eastExit = room;
			break;
		case north:
			northExit = room;
			break;
		case south:
			southExit = room;
			break;
		case west:
			westExit = room;
			break;
		default:
			break;
		}
	}
	
	public GenericRoom getExitRoom(MovementAdverb direction) {
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
	
	public void linkRoomTo(MovementAdverb direction, GenericRoom room) {
		addExit(direction, room);
	}
	 
	
	public static void twoWayLink(MovementAdverb fromDirection, CompassRoom from, CompassRoom to) {
		from.getExit().addExit(fromDirection, to);
		to.getExit().addExit(MovementAdverb.getOpposite(fromDirection), from);
	}
	
	public static void oneWayLink(MovementAdverb fromDirection, CompassRoom from, CompassRoom to) {
		from.getExit().addExit(fromDirection, to);
	}
}

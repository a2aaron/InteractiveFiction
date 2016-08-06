package types;

public class Directions {
	/* TODO: These are directions, however a class
	 * which handles links (direction, starting room, destination room)
	 * would be highly useful. 
	 */ 
	
	public enum CompassDirections {
		North, South, East, West;

		public CompassDirections getOpposite(CompassDirections direction) {
			switch(direction) {
			case North:
				return CompassDirections.South;
			case East:
				return CompassDirections.West;
			case South:
				return CompassDirections.North;
			case West:
				return CompassDirections.East;
			default:
				return null;
			}
		}
		
		public String toString(CompassDirections direction) {
			switch(direction) {
			case North:
				return "north";
			case East:
				return "east";
			case South:
				return "south";
			case West:
				return "west";
			default:
				return null;
			}
		}
		
	}

	public boolean isCompassDirection(String direction) {
		switch(direction) {
		case "north":
		case "south":
		case "east":
		case "west":
			return true;
		default:
			return false;
		}
	}	
	public CompassDirections StringToDirection(String direction) {
		switch(direction) {
		case "north":
			return CompassDirections.North;
		case "east":
			return CompassDirections.East;
		case "south":
			return CompassDirections.South;
		case "west":
			return CompassDirections.West;
		default:
			return null;
		}
	}
	
	public String DirectionsToString(CompassDirections direction) {
		switch(direction) {
		case North:
			return "north";
		case East:
			return "east";
		case South:
			return "south";
		case West:
			return "west";
		default:
			return null;
		}
	}
}
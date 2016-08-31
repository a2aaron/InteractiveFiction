package types;

import items.GenericItem;
import rooms.GenericRoom;


/** *
 * 
 * An action is an object which contains a verb, an enum which describe what
 * kind of action it will be (ex: checking inventory, using and object, etc)
 *
 * An action may optionally contain adverbs describing the verb in some way
 * (ex:  Move [left])
 * 
 * An action may optionally contain a direct or indirect object, which
 * means that the action is either done to the direct object, or that the action
 * is done to the object that effects the indirect object
 * 
 * (ex: Pull the [lever]. Use the [key] on the [door].)
 * 
 * The objects do not need to be items, they can be a room, the player, 
 * another person, etc.
 */
public class Action {
	public enum Verb {quit, inventory, use, take, destroy,
		useOn, examineRoom, examineObject, move;
	
		public static Verb stringToVerb(String verb) {
			switch (verb.toLowerCase()) {
			case "q":
			case "quit":
				return Verb.quit;
			case "i":
			case "inv":
			case "inventory":
				return Verb.inventory;
			case "l":
			case "look":
				return Verb.examineRoom;
			case "lookat":
			case "look at":
				return Verb.examineObject;
			case "use":
				return Verb.use;
			case "take":
				return Verb.take;
			case "break":
			case "destroy":
				return Verb.destroy;
			case "useon":
			case "use on":
				return Verb.useOn;
			case "go":
			case "move":
				return Verb.move;
			default:
				return null;
			}
		}
	};
	public enum MovementAdverb {UP, RIGHT, DOWN, LEFT;
	
		public static MovementAdverb stringToAdverb(String direction) {
			switch (direction.toLowerCase()) {
			case "n":
			case "north":
			case "up":
				return MovementAdverb.UP;
			case "s":
			case "south":
			case "down":
				return MovementAdverb.DOWN;
			case "w":
			case "west":
			case "left":
				return MovementAdverb.LEFT;
			case "e":
			case "east":
			case "right":
				return MovementAdverb.RIGHT;
			default:
				return null;
			}
		}
			
		public static MovementAdverb getOpposite(MovementAdverb direction) {
			switch (direction) {
			case UP:
				return DOWN;
			case RIGHT:
				return LEFT;
			case DOWN:
				return UP;
			case LEFT:
				return RIGHT;
			default:
				return null;
			}
		}
	};
	public Verb verb;
	public MovementAdverb direction;
	public Object directObject; // The object used in the action
								// ex: Look at the [room]
	public Object indirectObject; // The  object used by another object
							      //ex: Use the key on the [door]
	
	
	// Use [object1] on [object2]
	public Action(Verb verb, GenericItem directObject, GenericItem indirectObject) {
		this.verb = verb;
		this.directObject = directObject;
		this.indirectObject = indirectObject;
	}
	
	// Use [object]
	public Action(Verb verb, GenericItem directObject) {
		this.verb = verb;
		this.directObject = directObject;
	}
	
	// Look at [room]
	public Action(Verb verb, GenericRoom room) {
		this.verb = verb;
		this.directObject = room;
	}
	
	// Move in [direction]
	public Action(Verb verb, MovementAdverb direction) {
		this.verb = verb;
		this.direction = direction;
	}
	
	public Action(Verb verb) {
		this.verb = verb;
	}
	
	public Verb getVerb() {
		return verb;
	}
	
	public MovementAdverb getDirection() {
		return direction;
	}
	
	public Object getDirectObject() {
		return directObject;
	}
	
	public Object getIndirectObject() {
		return indirectObject;
	}
}
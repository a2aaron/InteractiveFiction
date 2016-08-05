package types;

import items.AbstractItem;
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
		useOn, examineRoom, examineObject, move};
	public enum MovementAdverb {north, east, south, west};
	public Verb verb;
	public MovementAdverb direction;
	public Object directObject; // The object used in the action
								// ex: Look at the [room]
	public Object indirectObject; // The  object used by another object
							      //ex: Use the key on the [door]
	
	
	// Use [object1] on [object2]
	public Action(Verb verb, AbstractItem directObject, AbstractItem indirectObject) {
		this.verb = verb;
		this.directObject = directObject;
		this.indirectObject = indirectObject;
	}
	
	// Use [object]
	public Action(Verb verb, AbstractItem directObject) {
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
/*
 * set up game world
 * 
 * game interaction loop
 * 
 * 1. get input
 * 2. parse input, return an action
 *  a. an action consists of two things
 *     - the actor (usually the player)
 *     - a verb (use, take, examine)
 *     - an object with the verb (the direct object)
 *     - optional: a second object which has an action done to it (the indirect object)
 *         - this takes the form of "use X on Y" or "do X to the Y using Z"
 * 3. do the action
 * 	  - you need to know the state of the actor (inventory, current hp, etc)
 *    - the state of the direct object (locked, unlocked, what items are in the room)
 *    - the state of any indirect objects
 * 
 *- game state is
 *- player inv
 *- current room state
 *	- inv
 *	- state of objects
 * 
 *
 * 
 * 
 */
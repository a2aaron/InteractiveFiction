package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import character.Movement;
import rooms.CompassExit;
import rooms.CompassRoom;
import types.Action.MovementAdverb;


public class MovementTest {
	
	@Test
	public void movementTest() {
		// Initialize rooms.
		CompassRoom westRoom = new CompassRoom("This room has a west exit");
		CompassRoom eastRoom = new CompassRoom("This room has a east exit");
		// Link rooms.
		CompassExit.oneWayLink(MovementAdverb.LEFT, westRoom, eastRoom);
		CompassExit.oneWayLink(MovementAdverb.RIGHT, eastRoom, westRoom);
		// Initialize player in west exit room.
		Movement player = new Movement(westRoom);
		// Should be able to move west.
		assertEquals(westRoom, player.getCurrentRoom());
		assertEquals(true, player.canMove(MovementAdverb.LEFT));
		assertEquals(false, player.canMove(MovementAdverb.RIGHT));
		// Move west into eastRoom.
		player.move(MovementAdverb.LEFT);
		// Should be able to move east.
		assertEquals(eastRoom, player.getCurrentRoom());
		assertSame(false, player.canMove(MovementAdverb.LEFT));
		assertSame(true, player.canMove(MovementAdverb.RIGHT));
	}
}

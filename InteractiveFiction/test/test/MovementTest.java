package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import character.Movement;
import rooms.CompassRoom;

public class MovementTest {
	
	@Test
	public void movementTest() {
		// Initialize rooms.
		CompassRoom westRoom = new CompassRoom("This room has a west exit");
		CompassRoom eastRoom = new CompassRoom("This room has a east exit");
		// Link rooms.
		westRoom.linkTo("west", eastRoom);
		eastRoom.linkTo("east", westRoom);
		// Initialize player in west exit room.
		Movement player = new Movement(westRoom);
		// Should be able to move west.
		assertEquals(westRoom, player.getCurrentRoom());
		assertEquals(true, player.canMove("west"));
		assertEquals(false, player.canMove("east"));
		// Move west into eastRoom.
		player.move("west");
		// Should be able to move east.
		assertEquals(eastRoom, player.getCurrentRoom());
		assertSame(false, player.canMove("west"));
		assertSame(true, player.canMove("east"));
	}
}

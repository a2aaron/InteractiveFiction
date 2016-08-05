package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import character.Movement;
import rooms.CompassRoom;
import types.Action.MovementAdverb;
import types.Directions.CompassDirections;


public class MovementTest {
	
	@Test
	public void movementTest() {
		// Initialize rooms.
		CompassRoom westRoom = new CompassRoom("This room has a west exit");
		CompassRoom eastRoom = new CompassRoom("This room has a east exit");
		// Link rooms.
		westRoom.linkRoomTo(CompassDirections.West, eastRoom);
		eastRoom.linkRoomTo(CompassDirections.East, westRoom);
		// Initialize player in west exit room.
		Movement player = new Movement(westRoom);
		// Should be able to move west.
		assertEquals(westRoom, player.getCurrentRoom());
		assertEquals(true, player.canMove(MovementAdverb.west));
		assertEquals(false, player.canMove(MovementAdverb.east));
		// Move west into eastRoom.
		player.move(MovementAdverb.west);
		// Should be able to move east.
		assertEquals(eastRoom, player.getCurrentRoom());
		assertSame(false, player.canMove(MovementAdverb.west));
		assertSame(true, player.canMove(MovementAdverb.east));
	}
}

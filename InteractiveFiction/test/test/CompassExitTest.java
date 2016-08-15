package test;

import static org.junit.Assert.*;

import org.junit.Test;

import rooms.CompassExit;
import rooms.CompassRoom;
import types.Action.MovementAdverb;

public class CompassExitTest {
	@Test
	public void simpleExitTest(){
		// Initialize rooms.
		CompassRoom westRoom = new CompassRoom("This room has a west exit");
		CompassRoom eastRoom = new CompassRoom("This room has a east exit");
		// Link rooms.
		CompassExit.oneWayLink(MovementAdverb.west, westRoom, eastRoom);
		CompassExit.oneWayLink(MovementAdverb.east, eastRoom, westRoom);
		// Test if the right exits exist
		assertEquals(eastRoom, westRoom.getExitRoom(MovementAdverb.west));
		assertEquals(westRoom, eastRoom.getExitRoom(MovementAdverb.east));
		
		// Check that the rooms are linked correctly (most common mistake is to
		// get the linking backwards
		assertNull(westRoom.getExitRoom(MovementAdverb.east));
		assertNull(eastRoom.getExitRoom(MovementAdverb.west));
	}
}

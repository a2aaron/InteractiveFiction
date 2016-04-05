package test;

import static org.junit.Assert.*;
import org.junit.Test;

import rooms.CompassRoom;
import types.Directions.CompassDirections;

public class RoomLinkerTest {
	
	
	@Test
	public void oneWayLinkTest() {
		CompassRoom exitRoom = new CompassRoom("exitRoom");
		CompassRoom noExit = new CompassRoom("noExit");
		exitRoom.linkRoomTo(CompassDirections.North, noExit);
		assertEquals("exitRoom should link to noExit.",
				noExit, exitRoom.getExitRoom(CompassDirections.North));
		assertEquals("noExit should not link to exit room",
				null, noExit.getExitRoom(CompassDirections.South));
	}
	
	@Test
	public void simpleLinkTest() {
		CompassRoom northRoom = new CompassRoom("Has south exit.");
		assertEquals("northRoom is unlinked and should not have any exit.",
				null, northRoom.getExitRoom(CompassDirections.South));
		
		CompassRoom southRoom = new CompassRoom("Has north exit.");
		assertEquals("southRoom is unlinked and should not have any exit.",
				null, southRoom.getExitRoom(CompassDirections.North));
		
		northRoom.linkRoomTo(CompassDirections.South, southRoom);
		assertEquals("northRoom should link to southRoom.",
				southRoom, northRoom.getExitRoom(CompassDirections.South));
		
		assertEquals("southRoom is still unlinked and should not have any exit.",
				null, southRoom.getExitRoom(CompassDirections.North));

		southRoom.linkRoomTo(CompassDirections.North, northRoom);
		assertEquals("southRoom should link to southRoom.",
				northRoom, southRoom.getExitRoom(CompassDirections.North));		
		
	}
	@Test
	public void harderLinkTest() {
		// Layout should be
		// [west room] <-> [east room]
		CompassRoom eastRoom = new CompassRoom("eastRoom");
		CompassRoom westRoom = new CompassRoom("westRoom");
		// Link rooms.
		westRoom.linkRoomTo(CompassDirections.East, eastRoom); // [west] -> [east]
		eastRoom.linkRoomTo(CompassDirections.West, westRoom); // [west] <- [east]
		
		assertEquals("westRoom should link east to eastRoom.",
				eastRoom, westRoom.getExitRoom(CompassDirections.East));
		assertEquals("eastRoom should link west to westRoom.",
				westRoom, eastRoom.getExitRoom(CompassDirections.West));
		assertEquals("westRoom should not have a west exit.",
				null, westRoom.getExitRoom(CompassDirections.West));
		assertEquals("eastRoom should not have an east exit.",
				null, eastRoom.getExitRoom(CompassDirections.East));
	}
	
	@Test
	public void twoWayLinkTest() {
		CompassRoom northRoom = new CompassRoom("Has south exit.");
		CompassRoom southRoom = new CompassRoom("Has north exit.");
		northRoom.twoSidedLink(CompassDirections.South, southRoom);

		assertEquals("northRoom should link to southRoom.",
				southRoom, northRoom.getExitRoom(CompassDirections.South));
		assertEquals("southRoom should link to southRoom.",
				northRoom, southRoom.getExitRoom(CompassDirections.North));		
	}
}

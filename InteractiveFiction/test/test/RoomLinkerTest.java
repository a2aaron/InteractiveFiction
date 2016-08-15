package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import rooms.CompassExit;
import rooms.CompassRoom;
import types.Action.MovementAdverb;

public class RoomLinkerTest {
	
	
	@Test
	public void oneWayLinkTest() {
		CompassRoom exitRoom = new CompassRoom("exitRoom");
		CompassRoom noExit = new CompassRoom("noExit");
		exitRoom.getExit().linkRoomTo(MovementAdverb.north, noExit);
		assertEquals("exitRoom should link to noExit.",
				noExit, exitRoom.getExitRoom(MovementAdverb.north));
		assertEquals("noExit should not link to exit room",
				null, noExit.getExitRoom(MovementAdverb.south));
	}
	
	@Test
	public void simpleLinkTest() {
		CompassRoom northRoom = new CompassRoom("Has south exit.");
		assertEquals("northRoom is unlinked and should not have any exit.",
				null, northRoom.getExitRoom(MovementAdverb.south));
		
		CompassRoom southRoom = new CompassRoom("Has north exit.");
		assertEquals("southRoom is unlinked and should not have any exit.",
				null, southRoom.getExitRoom(MovementAdverb.north));
		
		northRoom.getExit().linkRoomTo(MovementAdverb.south, southRoom);
		assertEquals("northRoom should link to southRoom.",
				southRoom, northRoom.getExitRoom(MovementAdverb.south));
		
		assertEquals("southRoom is still unlinked and should not have any exit.",
				null, southRoom.getExitRoom(MovementAdverb.north));

		southRoom.getExit().linkRoomTo(MovementAdverb.north, northRoom);
		assertEquals("southRoom should link to southRoom.",
				northRoom, southRoom.getExitRoom(MovementAdverb.north));		
		
	}
	@Test
	public void harderLinkTest() {
		// Layout should be
		// [west room] <-> [east room]
		CompassRoom eastRoom = new CompassRoom("eastRoom");
		CompassRoom westRoom = new CompassRoom("westRoom");
		// Link rooms.
		westRoom.getExit().linkRoomTo(MovementAdverb.east, eastRoom); // [west] -> [east]
		eastRoom.getExit().linkRoomTo(MovementAdverb.west, westRoom); // [west] <- [east]
		
		assertEquals("westRoom should link east to eastRoom.",
				eastRoom, westRoom.getExitRoom(MovementAdverb.east));
		assertEquals("eastRoom should link west to westRoom.",
				westRoom, eastRoom.getExitRoom(MovementAdverb.west));
		assertEquals("westRoom should not have a west exit.",
				null, westRoom.getExitRoom(MovementAdverb.west));
		assertEquals("eastRoom should not have an east exit.",
				null, eastRoom.getExitRoom(MovementAdverb.east));
	}
	
	@Test
	public void twoWayLinkTest() {
		CompassRoom northRoom = new CompassRoom("Has south exit.");
		CompassRoom southRoom = new CompassRoom("Has north exit.");
		CompassExit.twoWayLink(MovementAdverb.south, northRoom, southRoom);

		assertEquals("northRoom should link to southRoom.",
				southRoom, northRoom.getExitRoom(MovementAdverb.south));
		assertEquals("southRoom should link to southRoom.",
				northRoom, southRoom.getExitRoom(MovementAdverb.north));		
	}
}

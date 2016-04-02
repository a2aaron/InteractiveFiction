package test;

import static org.junit.Assert.*;
import org.junit.Test;

import rooms.CompassRoom;


public class RoomLinkerTest {
	
	
	@Test
	public void oneWayLinkTest() {
		CompassRoom exitRoom = new CompassRoom("exitRoom");
		CompassRoom noExit = new CompassRoom("noExit");
		exitRoom.linkTo("north", noExit);
		assertEquals("exitRoom should link to noExit.",
				noExit, exitRoom.getExitRoom("north"));
		assertEquals("noExit should not link to exit room",
				null, noExit.getExitRoom("south"));
	}
	
	@Test
	public void simpleLinkTest() {
		CompassRoom northRoom = new CompassRoom("Has south exit.");
		assertEquals("northRoom is unlinked and should not have any exit.",
				null, northRoom.getExitRoom("south"));
		
		CompassRoom southRoom = new CompassRoom("Has north exit.");
		assertEquals("southRoom is unlinked and should not have any exit.",
				null, southRoom.getExitRoom("north"));
		
		northRoom.linkTo("south", southRoom);
		assertEquals("northRoom should link to southRoom.",
				southRoom, northRoom.getExitRoom("south"));
		
		assertEquals("southRoom is still unlinked and should not have any exit.",
				null, southRoom.getExitRoom("north"));

		southRoom.linkTo("north", northRoom);
		assertEquals("southRoom should link to southRoom.",
				northRoom, southRoom.getExitRoom("north"));		
		
	}
	@Test
	public void harderLinkTest() {
		// Layout should be
		// [west room] <-> [east room]
		CompassRoom eastRoom = new CompassRoom("eastRoom");
		CompassRoom westRoom = new CompassRoom("westRoom");
		// Link rooms.
		westRoom.linkTo("east", eastRoom); // [west] -> [east]
		eastRoom.linkTo("west", westRoom); // [west] <- [east]
		
		assertEquals("westRoom should link east to eastRoom.",
				eastRoom, westRoom.getExitRoom("east"));
		assertEquals("eastRoom should link west to westRoom.",
				westRoom, eastRoom.getExitRoom("west"));
		assertEquals("westRoom should not have a west exit.",
				null, westRoom.getExitRoom("west"));
		assertEquals("eastRoom should not have an east exit.",
				null, eastRoom.getExitRoom("east"));
	}
	
	@Test
	public void twoWayLinkTest() {
		CompassRoom northRoom = new CompassRoom("Has south exit.");
		CompassRoom southRoom = new CompassRoom("Has north exit.");
		northRoom.twoSidedLink("south", southRoom);

		assertEquals("northRoom should link to southRoom.",
				southRoom, northRoom.getExitRoom("south"));
		assertEquals("southRoom should link to southRoom.",
				northRoom, southRoom.getExitRoom("north"));		
	}
}

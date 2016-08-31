package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import org.json.JSONObject;
import org.json.JSONTokener;
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
		CompassExit.oneWayLink(MovementAdverb.LEFT, westRoom, eastRoom);
		CompassExit.oneWayLink(MovementAdverb.RIGHT, eastRoom, westRoom);
		// Test if the right exits exist
		assertEquals(eastRoom, westRoom.getExitRoom(MovementAdverb.LEFT));
		assertEquals(westRoom, eastRoom.getExitRoom(MovementAdverb.RIGHT));
		
		// Check that the rooms are linked correctly (most common mistake is to
		// get the linking backwards
		assertNull(westRoom.getExitRoom(MovementAdverb.RIGHT));
		assertNull(eastRoom.getExitRoom(MovementAdverb.LEFT));
	}
	
	@Test
	public void exitFromJSONTest() throws Exception {
		File file = new File("test/test/testExit.json");
		JSONTokener tokener = new JSONTokener(new FileInputStream(file));
		JSONObject reader = new JSONObject(tokener);
		CompassExit exit = new CompassExit(reader);
		
		assertTrue(exit.get(MovementAdverb.DOWN).getRoomName().equals("lighthouse"));
		assertNull(exit.get(MovementAdverb.UP));
	}
}

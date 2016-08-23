package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.*;
import org.junit.Test;

import items.AbstractItem;
import items.Vace;
import rooms.CompassRoom;
import rooms.Exit;
import rooms.GenericRoom;
import rooms.RoomInventory;

public class WorldImporterTest {	
	File roomFile = new File("test/test/testRoom.json");
	File itemFile = new File("test/test/testItem.json");
	
	@Test
	public void createItemFromJSONTest() throws Exception {
		JSONTokener tokener = new JSONTokener(new FileInputStream(itemFile));
		JSONObject reader = new JSONObject(tokener);
		Vace item = new Vace(reader);
		assertTrue(item.getName() != null);
		assertTrue(item.getDescription() != null);
	}
		
	@Test
	public void createRoomFromJSONTest() throws Exception {
		JSONTokener tokener = new JSONTokener(new FileInputStream(roomFile));
		JSONObject reader = new JSONObject(tokener);
		GenericRoom room = new GenericRoom(reader);
		assertTrue(room.getRoomName() != null);
		assertTrue(room.getRoomDescription() != null);
		assertTrue(room.getExits() != null);
		assertTrue(room.getExits().getExitSet() != null);
		assertTrue(room.getExits().getExitSet().size() != 0);
//		assertTrue(room.getRoomInventory().getItemByName("Test Vace") != null);
	}
}

package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import org.json.*;
import org.junit.Test;

import items.Vace;
import rooms.GenericRoom;
import world.ItemImporter;
import world.WorldImporter;

public class WorldImporterTest {	
	File roomFile = new File("test/test/testRoom.json");
	File itemFile = new File("test/test/testItem.json");
	
	@Test
	public void createItemFromJSONTest() throws Exception {
		JSONTokener tokener = new JSONTokener(new FileInputStream(itemFile));
		JSONObject reader = new JSONObject(tokener);
		Vace item = (Vace) ItemImporter.createItem(reader);
		assertTrue(item.getName() != null);
		assertTrue(item.getDescription() != null);
	}
		
	@Test
	public void createRoomFromJSONTest() throws Exception {
		JSONTokener tokener = new JSONTokener(new FileInputStream(roomFile));
		JSONObject reader = new JSONObject(tokener);
		assertTrue(reader != null); //Sanity check
		GenericRoom room = WorldImporter.genericRoomFromJSON(reader);
		assertTrue(room.getRoomName() != null);
		assertTrue(room.getRoomDescription() != null);
		assertTrue(room.getExits() != null);
		assertTrue(room.getExits().getExitSet() != null);
		assertTrue(room.getExits().getExitSet().size() != 0);
//		assertTrue(room.getRoomInventory().getItemByName("Test Vace") != null);
	}
}

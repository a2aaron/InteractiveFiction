package test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import character.Inventory;
import character.PlayerState;
import items.Vace;
import rooms.GenericRoom;
import types.Action;
import types.Action.MovementAdverb;
import types.Action.Verb;
import world.Parser;

public class ParserTest {
	@Test
	public void testMovementAction() {
		Parser parser = new Parser();
		Action north = parser.parseInput("move north");
		
		assertSame(north.getVerb(), Verb.move);
//		System.out.println(north.getDirection().toString());
		System.out.println("Nothing");
		assertSame(north.getDirection(), MovementAdverb.north);
		assertSame(north.getDirectObject(), null);
		assertSame(north.getIndirectObject(), null);
	}
	
	@Test
	public void testNoDirectObject() {
		Parser parser = new Parser();
		Action quit = parser.parseInput("quit");
		Action inventory = parser.parseInput("i");
		
		assertSame(quit.getVerb(), Verb.quit);
		assertSame(inventory.getVerb(), Verb.inventory);
	}
	
	@Test
	public void testItemActionRoomAndPlayer() {
		Inventory playerInventory = new Inventory();
		GenericRoom GenericRoom = new GenericRoom();
		
		Vace vacePlayer = new Vace("vacePlayer", "vacePlayerdescription");
		Vace vaceRoom = new Vace("vaceRoom", "vaceRoomdescription");
		
		playerInventory.addItem(vacePlayer);
		GenericRoom.addItem(vaceRoom);
		
		PlayerState playerState = new PlayerState(playerInventory, GenericRoom);
		Parser parser = new Parser(playerState);
		
		Action itemAction1 = parser.parseInput("use vacePlayer");		
		Action itemAction2 = parser.parseInput("break vaceRoom");

		assertSame(itemAction1.getVerb(), Verb.use);
		assertSame(itemAction1.getDirectObject(), vacePlayer);
		assertSame(itemAction1.getIndirectObject(), null);
		
		assertSame(itemAction2.getVerb(), Verb.destroy);
		assertSame(itemAction2.getDirectObject(), vaceRoom);
		assertSame(itemAction2.getIndirectObject(), null);
	}
	
	@Test
	public void testItemActionRoomOnly() {
		Inventory playerInventory = new Inventory();
		GenericRoom GenericRoom = new GenericRoom();
		
		Vace vacePlayer = new Vace("vacePlayer", "vacePlayerdescription");
		Vace vaceRoom = new Vace("vaceRoom", "vaceRoomdescription");
		
		playerInventory.addItem(vacePlayer);
		GenericRoom.addItem(vaceRoom);
		
		PlayerState playerState = new PlayerState(playerInventory, GenericRoom);
		Parser parser = new Parser(playerState);
		
		// Should not be possible because this is in the player inventory
		Action takeNull = parser.parseInput("take vacePlayer");
		// Should be possible because it is in the room
		Action takeRoom = parser.parseInput("take vaceRoom");
		
		assertSame(takeNull.getVerb(), Verb.take);
		assertSame(takeNull.getDirectObject(), null);
		assertSame(takeNull.getIndirectObject(), null);
		
		assertSame(takeRoom.getVerb(), Verb.take);
		assertSame(takeRoom.getDirectObject(), vaceRoom);
		assertSame(takeRoom.getIndirectObject(), null);
	}
	
	@Test
	public void testItemActionIndirectObject() {
		Inventory playerInventory = new Inventory();
		GenericRoom GenericRoom = new GenericRoom();
		
		Vace vacePlayer = new Vace("vace player", "vacePlayerdescription");
		Vace vaceRoom = new Vace("vace room", "vaceRoomdescription");
		
		playerInventory.addItem(vacePlayer);
		GenericRoom.addItem(vaceRoom);
		
		PlayerState playerState = new PlayerState(playerInventory, GenericRoom);
		Parser parser = new Parser(playerState);
		
		Action useOn = parser.parseInput("useon vace player vace room");
		
		assertSame(useOn.getVerb(), Verb.useOn);
		assertSame(useOn.getDirectObject(), vacePlayer);
		assertSame(useOn.getIndirectObject(), vaceRoom);
	}
	
	@Test
	public void testExamineRoom() {
		GenericRoom room = new GenericRoom();
		Parser parser = new Parser(new PlayerState(room));
		
		Action examineRoom = parser.parseInput("look");
		
		assertSame(examineRoom.getVerb(), Verb.examineRoom);
		assertSame(examineRoom.getDirectObject(), room);
	}
	
	@Test
	public void testInvalidCommands() {
		Parser parser = new Parser();
		// Invalid commands should return null (Can't make an action out of it)
		String[] invalid = {"quir", "null", "", "", "\n", "NaN", "undefined"};
		for (String input : invalid) {
			assertNull(parser.parseInput(input));
		}
	}
	
	@Test
	public void testCantFindItem() {
		Inventory playerInventory = new Inventory();
		GenericRoom GenericRoom = new GenericRoom();
		
		Vace vacePlayer = new Vace("vace player", "vacePlayerdescription");
		Vace vaceRoom = new Vace("vaceRoom", "vaceRoomdescription");
		
		playerInventory.addItem(vacePlayer);
		GenericRoom.addItem(vaceRoom);
		
		PlayerState playerState = new PlayerState(playerInventory, GenericRoom);
		Parser parser = new Parser(playerState);
		
		String[] nullDirectObject = {"use", "break ", "take nothing", "useOn nothing nothing", "useOn"};
		String[] nullIndirectObject = {"useOn vace player", "useOn", "useOn vace player nothing"};
		for (String input : nullDirectObject) {
			assertNull(parser.parseInput(input).getDirectObject());
		}
		
		for (String input : nullIndirectObject) {
			assertNull(parser.parseInput(input).getIndirectObject());
		}
	}
}

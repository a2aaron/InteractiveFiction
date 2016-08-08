package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import character.Interact;
import character.PlayerState;
import items.Vace;
import types.Action;
import types.Action.Verb;
import types.ItemAction;

public class InteractTest {
	final ByteArrayOutputStream printedStatements = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(printedStatements));
		printedStatements.reset();
	}
	
	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}
 	
	@Test
	public void testNullPrints() {
		Interact interact = new Interact(new PlayerState());

		// Should output "invalid command"
		// Note that I don't actually care about the underlying message, 
		// only that it does output something
		interact.doAction(null);
		assertNotNull(printedStatements);
		printedStatements.reset();
		
		// Should output "can't find that item"
		interact.doAction(new ItemAction(Verb.use, null));
		assertNotNull(printedStatements);
		printedStatements.reset();

		// Should output "can't find your second item"
		interact.doAction(new ItemAction(Verb.useOn, new Vace("", ""), null));
		assertNotNull(printedStatements);
		printedStatements.reset();
	}
	
	@Test(expected=RuntimeException.class) 
	public void testNullVerbThrowsException() {
		Interact interact = new Interact(new PlayerState());
		interact.doAction(new Action(null));
	}
}

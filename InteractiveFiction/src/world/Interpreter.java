package world;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import character.Interact;
import character.Inventory;
import character.PlayerState;
import rooms.GenericRoom;
import types.Action;
import types.Action.MovementAdverb;

public class Interpreter {
	static PlayerState playerState;
	static Scanner playerInput;
	static Interact playerInteractor;

	public Interpreter() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		File file = new File("sub1/exits.json");
		WorldImporter world = new WorldImporter();
		try {
			world.createRoomSet(new File("sub1/rooms"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		GenericRoom startingRoom = world.getStartingRoom();
		
		playerInput = new Scanner(System.in);
		playerState = new PlayerState(new Inventory(), startingRoom);
		playerInteractor = new Interact(playerState);
		Parser playerParser = new Parser(playerState);
		System.out.println("[GAME START]");
		while(playerInteractor.isPlaying()) {
			System.out.print("[" + playerState.getCurrentRoom().getRoomName() + "] > ");
			String input = playerInput.nextLine();
			Action action = playerParser.parseInput(input);
			playerInteractor.doAction(action);
		}
		
		System.out.println("Goodbye");
		playerInput.close();
	}
}

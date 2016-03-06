package world;

import java.util.Scanner;

import character.Movement;
import rooms.CompassRoom;

public class InterpreterTest {
	public InterpreterTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Scanner playerInput = new Scanner(System.in);
		
		CompassRoom startingRoom = new CompassRoom("Starting Room.");
		CompassRoom northRoom = new CompassRoom("North Exit Room");
		CompassRoom southRoom = new CompassRoom("South Exit Room");
		startingRoom.linkRoom("north", northRoom);
		startingRoom.linkRoom("south", southRoom);
		northRoom.linkRoom("south", startingRoom);
		southRoom.linkRoom("north", startingRoom);
		
		Movement player = new Movement(startingRoom);
		
		while(true) {
			System.out.println("You are in room: " + player.getCurrentRoomName());
			System.out.println("Enter a movement command.");
			String command = playerInput.nextLine();
			if (player.canMove(command)) {
				player.move(command);	
			} else {
				System.out.println("Can't move there.");
			}
		}
	}
}

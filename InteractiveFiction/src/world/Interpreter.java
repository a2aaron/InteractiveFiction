package world;

import java.util.Scanner;

import character.Movement;
import rooms.CompassRoom;

public class Interpreter {
	public Interpreter() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Scanner playerInput = new Scanner(System.in);
		
		CompassRoom startingRoom = new CompassRoom("Starting Room.");
		CompassRoom northRoom = new CompassRoom("North Exit Room");
		CompassRoom southRoom = new CompassRoom("South Exit Room");
		startingRoom.twoSidedLink("north", northRoom);
		startingRoom.twoSidedLink("south", southRoom);
		
		Movement player = new Movement(startingRoom);
		boolean playing = true;
		while(playing) {
			System.out.println("You are in room: " + player.getCurrentRoomName());
			System.out.println("Enter a movement command.");
			String command = playerInput.nextLine().toLowerCase();
			
			if(player.isMovement(command)) {
				if (player.canMove(command)) {
					player.move(command);	
				} else {
					System.out.println("Can't move there.");
				}
			} else {
				if (command.equals("quit")) {
					playing = false;
					playerInput.close();
					System.out.println("Quit. Goodbye");
				} else if (command.equals("help")) {
					System.out.println("Help? What help?");
				} else {
					System.out.println("Invalid Command: [" + command +"]");
				}
			} 
		}		
	}
}

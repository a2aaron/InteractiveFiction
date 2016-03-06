package world;

import character.Movement;
import rooms.CompassRoom;

public class InterpreterTest {

	public InterpreterTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		CompassRoom northRoom = new CompassRoom("North Exit Room");
		CompassRoom southRoom = new CompassRoom("South Exit Room");
		northRoom.linkRoom("north", southRoom);
		northRoom.linkRoom("south", northRoom);
		
		Movement player = new Movement(northRoom);
		player.move("north");
		System.out.println(player.getCurrentRoomName());
	}
}

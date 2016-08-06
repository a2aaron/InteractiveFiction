package character;

import items.AbstractItem;
import rooms.CompassRoom;
import rooms.GenericRoom;
import rooms.RoomInventory;

public class PlayerState {
	int playerEnergy = 100;
	Inventory playerInventory;
	GenericRoom currentRoom;
	
	public PlayerState(Inventory playerInventory, CompassRoom currentRoom) {
		this.playerInventory = playerInventory;
		this.currentRoom = currentRoom;
	}
	
	public int getPlayerEnergy() {
		return playerEnergy;
	}
	
	public void setPlayerEnergy(int energy) {
		playerEnergy = energy;
	}
	
	public Inventory getPlayerInventory() {
		return playerInventory;
	}
	
	public GenericRoom getCurrentRoom() {
		return currentRoom;
	}
	
	public RoomInventory getCurrentRoomInventory() {
		return currentRoom.getRoomInventory();
	}
	
	public void setCurrentRoom(GenericRoom currentRoom) {
		this.currentRoom  = currentRoom;
	}
	
	public AbstractItem searchPlayerAndRoomInventory(String itemName) {
		if (playerInventory.getItemByName(itemName) != null) {
			return playerInventory.getItemByName(itemName);
		} else if (currentRoom.getRoomInventory().getItemByName(itemName) != null) {
			return currentRoom.getRoomInventory().getItemByName(itemName);
		} else {
			return null;
		}
	}
}




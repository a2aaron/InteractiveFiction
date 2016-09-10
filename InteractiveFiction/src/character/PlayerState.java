package character;

import inventory.Inventory;
import inventory.RoomInventory;
import items.utility.GenericItem;
import rooms.GenericRoom;

public class PlayerState {
	int playerEnergy = 100;
	Inventory playerInventory;
	GenericRoom currentRoom;
	
	public PlayerState() {
		this.playerInventory = new Inventory();
		this.currentRoom = new GenericRoom();
	}
	
	public PlayerState(GenericRoom currentRoom) {
		this.playerInventory = new Inventory();
		this.currentRoom = currentRoom;
	}
	
	public PlayerState(Inventory playerInventory, GenericRoom genericRoom) {
		this.playerInventory = playerInventory;
		this.currentRoom = genericRoom;
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
	
	public GenericItem searchPlayerAndRoomInventory(String itemName) {
		if (playerInventory.getItemByName(itemName) != null) {
			return playerInventory.getItemByName(itemName);
		} else if (currentRoom.getRoomInventory().getItemByName(itemName) != null) {
			return currentRoom.getRoomInventory().getItemByName(itemName);
		} else {
			return null;
		}
	}
	
	public Inventory getPlayerAndRoomInventory() {
		return new Inventory(playerInventory, currentRoom.getRoomInventory());
	}
}




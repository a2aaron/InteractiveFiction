package character;

import java.util.concurrent.CopyOnWriteArrayList;

import items.AbstractItem;
import items.IBreakableItem;
import items.ITakeableItem;
import rooms.GenericRoom;
import rooms.RoomInventory;
import world.Parser;

public class Interact {
	public enum Action {quit, inventory, use, take, destroy,
						useOn, examineRoom, examineObject};
	
	Inventory playerInventory;
	GenericRoom currentRoom;
	Parser playerParser;
	
	public Interact(Inventory playerInventory) {
		this.playerInventory = playerInventory;
	}
	
	public void doAction(Action action) {
		switch(action) {
		case take:
			takeItem(playerParser.askForItem(currentRoom.getRoomInventory()), currentRoom);
		case use:
			
		}
	}
	
	public AbstractItem findItemInInventories(AbstractItem item, Inventory...inventories) {
		for (Inventory inventory : inventories) {
			if(inventory.hasItem(item)) {
				return item;
			}
		}
		
		return null;
	}
	
	public String examine(AbstractItem item) {
		return item.getDescription(); 
	}
	
	public void useItem(AbstractItem item) {
		item.useItem();
	}
	
	public void takeItem(AbstractItem item, RoomInventory inventory) {
		playerInventory.takeItem(item, inventory);
	}
	
	public void takeItem(AbstractItem item, GenericRoom room) {
		playerInventory.takeItem(item, room.getRoomInventory());
	}

	public boolean canTakeItem(AbstractItem item) {
		if (item instanceof ITakeableItem) {
			if (((ITakeableItem) item).isTakeable()) {
				return true;
			}
		}
		return false;
	}
	
	public void breakItem(IBreakableItem item) {
		item.breakItem();
	}
	
	/**
	 * 
	 * @param item
	 * @return true if it succeeds, false if it does not
	 */
	public boolean tryBreakItem(AbstractItem item) {
		if (item instanceof IBreakableItem) {
			if (!((IBreakableItem) item).isBroken()) {
				((IBreakableItem) item).breakItem();
				return true;
			}
		}
		return false;
	}
	
	public static void printItems(CopyOnWriteArrayList<AbstractItem> inventoryList) {
	    System.out.println("---[ITEMS]---");
		if (inventoryList.size() != 0) {
			for (AbstractItem item : inventoryList) {
				System.out.println("[" + item.getName() + "]");
				System.out.println(item.getDescription());
			} 
		} else {
			System.out.println("No items.");
		}
		System.out.println("-------------");		
	}
}

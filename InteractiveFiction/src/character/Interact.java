package character;

import java.util.concurrent.CopyOnWriteArrayList;

import items.AbstractItem;
import items.IBreakableItem;
import items.IItemUseableOn;
import items.ITakeableItem;
import rooms.CompassRoom;
import rooms.GenericRoom;
import rooms.RoomInventory;
import types.Action;
import world.Parser;

public class Interact {
	//public enum Action {quit, inventory, use, take, destroy,
	//					useOn, examineRoom, examineObject, move};
	
	PlayerState playerState;
	Parser playerParser;
	boolean playing = true;
	
	public Interact(PlayerState playerState) {
		this.playerState = playerState;
	}
	
	public void doAction(Action action) {
		Inventory playerInventory = playerState.getPlayerInventory();
		Inventory currentRoomInventory = playerState.getCurrentRoomInventory();

		if (action != null) {	
			switch(action.getVerb()) {
			case quit:
				playing = false;
				break;
			case examineRoom:
			{
				String description = playerState.getCurrentRoom().getExtendedRoomDescription();
				System.out.println("---[DESCRIPTION]---");
				System.out.println(description);
				System.out.println("-------------\n");
				playerState.getCurrentRoomInventory().printItems();
				break;
			}
			case inventory:
				playerState.getPlayerInventory().printItems();
				break;
			case move:
			{
				CompassRoom currentRoom = ((CompassRoom) playerState.getCurrentRoom());
				CompassRoom newRoom = currentRoom.getExitRoom(action.getDirection());
				if (newRoom != null) {
					playerState.setCurrentRoom(newRoom);
				}
				System.out.println(playerState.getCurrentRoom().getRoomDescription());
				break;
			}
			case destroy:
				if (action.getDirectObject() instanceof IBreakableItem) {
					((IBreakableItem) action.getDirectObject()).breakItem();
				}
				break;
			case examineObject:
			{
				if (action.getDirectObject() == null) {
					System.out.println("Can't find that item");
				} else if (action.getDirectObject() instanceof AbstractItem) {
					String description = ((AbstractItem) action.getDirectObject()).getDescription();
					System.out.println(description);
				}
				break;
			}
			case take:
			{
				Object directObject = action.getDirectObject();
				if (directObject == null) {
					System.out.println("Can't find that item");
				} else if (directObject instanceof ITakeableItem) {
					playerInventory.takeItem((AbstractItem) directObject, currentRoomInventory);
				} else {
					System.out.println("Can't take that item");
				}
				break;
			}
			case use:
			{
				if (action.getDirectObject() == null) {
					System.out.println("Can't find that item");
				} else if (action.getDirectObject() instanceof AbstractItem) {
					((AbstractItem) action.getDirectObject()).useItem();
				}
				break;
			}
			case useOn:
			{
				if (action.getDirectObject() == null) {
					System.out.println("Can't find that item");
				} else if (action.getDirectObject() instanceof IItemUseableOn) {
					IItemUseableOn directObject = (IItemUseableOn) action.getDirectObject();
					AbstractItem indirectObject = (AbstractItem) action.getIndirectObject();
					directObject.useItemOn(indirectObject);
				}
				break;
			}
			default:
				break;
			}
		} else {
			System.out.println("Invalid Command");
		}
	}

	public boolean isPlaying() {
		return playing;
	}
}

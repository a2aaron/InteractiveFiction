package character;

import items.AbstractItem;
import items.IBreakableItem;
import items.IItemUseableOn;
import items.ITakeableItem;
import rooms.CompassRoom;
import types.Action;
import types.ItemAction;
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

		if (action == null) {
			System.out.println("Invalid Command");
			return;
		}
		
		if (action instanceof ItemAction) {
			if (action.getDirectObject() == null) {
				System.out.println("Can't find that item");
			} else {
				AbstractItem directObject = (AbstractItem) action.getDirectObject();
				switch(action.getVerb()) {
				case examineObject:
					System.out.println(directObject.getDescription());
					break;
				case use:
					directObject.useItem();
					break;
				case take:
					if (directObject instanceof ITakeableItem) {
						playerInventory.takeItem(directObject, currentRoomInventory);
						System.out.println("Took the " + directObject.getName());
					} else {
						System.out.println("Can't take the " + directObject.getName());
					}
					break;
				case destroy:
					if (directObject instanceof IBreakableItem) {
						((IBreakableItem) action.getDirectObject()).breakItem();
						System.out.println("Broke the " + directObject.getName());
					} else {
						System.out.println("Can't break the " + directObject.getName());
					}
					break;
				case useOn:
					if (directObject instanceof IItemUseableOn) {
						AbstractItem indirectObject = (AbstractItem) action.getIndirectObject();
						((IItemUseableOn) directObject).useItemOn(indirectObject);
					}
					break;
				default:
					break;
				}
			}
		} else {
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
			default:
				break;
			}
		}
	}

	public boolean isPlaying() {
		return playing;
	}
}

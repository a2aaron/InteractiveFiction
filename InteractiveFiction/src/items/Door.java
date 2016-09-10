package items;

import items.utility.GenericItem;
import items.utility.IUseableItem;

public class Door extends GenericItem implements IUseableItem {
	public enum DoorState {open, closed};
	public DoorState doorState;
	
    public Door(String name, String description, DoorState initialState) {
        super(name, description);
        doorState = initialState;
    }

	public void useItem() {
        if (doorState == DoorState.closed) {
        	doorState = DoorState.open;
            System.out.println("You open the door.");
        } else {
        	doorState = DoorState.closed;
            System.out.println("You close the door.");
        }
	}
	
	public DoorState getDoorState() {
		return doorState;
	}

	@Override
	public boolean isUseable() {
		return true;
	}
}
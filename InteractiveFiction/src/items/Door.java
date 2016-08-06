package items;

public class Door extends AbstractItem {
	public enum DoorState {open, closed};
	public DoorState doorState;
	
    public Door(String name, String description, DoorState initialState) {
        super(name, description);
        doorState = initialState;
    }

	@Override
	public void useItem() {
        if (doorState == DoorState.closed) {
        	doorState = DoorState.open;
            System.out.println("You open the door.");
        } else {
        	doorState = DoorState.closed;
            System.out.println("You close the door.");
        }
	}
	
	public void openDoor() {
		useItem();
	}
	
	public DoorState getDoorState() {
		return doorState;
	}
}
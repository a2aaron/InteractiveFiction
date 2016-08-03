package items;

public class LockedDoor extends Door {
	public enum LockState {Locked, Unlocked};
	
	public DoorState doorState;
	public LockState lockState = LockState.Locked;
	public Key key; // Key which unlocks the door.
	public String name;
	public String description;
	
	public LockedDoor(String name, String description, DoorState initialState, Key key) {
		super(name, description, initialState);
		this.name = name;
		this.description = description;
		this.key = key;
		doorState = initialState;
		setDescription("This door is " + lockState.toString() + " and is " + doorState.toString() + ".");
	}
	
	public void unlockDoor(AbstractItem item) {
		if (item instanceof Key) {
			if (item == key) {
				lockState = LockState.Unlocked;
				System.out.println("You unlock the door");
				setDescription("This door is unlocked. It is currently " + doorState.toString() + ".");
			} else {
			System.out.println("The key does not fit.");
			}
		} else {
			System.out.println("This is not a key!");
		}
	}
	
	public void useItem() {
		if (lockState == LockState.Locked) {
			System.out.println("This door is locked. Find the key");
		} else {
			super.useItem(); // Normal door opening logic
		}
		
		setDescription("This door is " + lockState.toString() + " and is " + doorState.toString() + ".");
	}
	
	public LockState getLockState() {
		return lockState;
	}
}

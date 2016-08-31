package items;

public class LockedDoor extends Door {
	public enum LockState {locked, unlocked};
	
	public LockState lockState = LockState.locked;
	public Key key; // Key which unlocks the door.
	public String name;

	
	public LockedDoor(String name, Key key) {
		super(name, "This door is locked and is closed.", DoorState.closed);
		this.name = name;
		this.key = key;
	}
	
	public void unlockDoor(GenericItem item) {
		if (item instanceof Key) {
			if (item == key) {
				lockState = LockState.unlocked;
				System.out.println("You unlock the door");
				updateDescription();
			} else {
			System.out.println("The key does not fit.");
			}
		} else {
			System.out.println("This is not a key!");
		}
	}
	
	public void useItem() {
		if (lockState == LockState.locked) {
			System.out.println("This door is locked. Find the key");
		} else {
			super.useItem(); // Normal door opening logic
		}
		updateDescription();
	}
	
	private void updateDescription() {
		setDescription("This door is " + lockState.toString() + 
				" . It is currently " + doorState.toString() + ".");
	}
	
	public LockState getLockState() {
		return lockState;
	}
}

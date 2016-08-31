package items;

public class Key extends GenericTakeableItem implements IItemUseableOn {

	public Key(String name, String description) {
		super(name, description);
	}

	@Override
	public void useItemOn(GenericItem item) {
		if (item instanceof LockedDoor) {
			((LockedDoor) item).unlockDoor(this);
		} else {
			System.out.println("Cannot use the key on " + item.getName() + ".");
		}
	}

}





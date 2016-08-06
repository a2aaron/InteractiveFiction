package items;

import character.Inventory;

public class Key extends AbstractItem implements ITakeableItem, IItemUseableOn {

	public Key(String name, String description) {
		super(name, description);
	}

	@Override
	public void takeItem(Inventory destination) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isTakeable() {
		return true;
	}

	@Override
	public void useItem() {
		System.out.println("You can't just use the key, you have to use it on something!");
	}

	@Override
	public void useItemOn(AbstractItem item) {
		if (item instanceof LockedDoor) {
			((LockedDoor) item).unlockDoor(this);
		} else {
			System.out.println("Cannot use the key on " + item.getName() + ".");
		}
	}

}





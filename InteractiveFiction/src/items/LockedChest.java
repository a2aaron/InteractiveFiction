package items;

import java.util.function.Function;

import inventory.Inventory;
import items.utility.GenericItem;
import items.utility.ILockedItem;

public class LockedChest extends Chest implements ILockedItem {

	boolean locked = true;
	Function<GenericItem, Boolean> unlockCondition;

	public LockedChest() {};
	
	public LockedChest(GenericItem key) {
		unlockCondition = (GenericItem testKey) -> {
			return testKey == key;
		};
	};
	
	public LockedChest(Function<GenericItem, Boolean> unlock) {
		unlockCondition = unlock;
	}
	
	@Override
	public Inventory getInventory() {
		if (locked) {
			return null;
		} else {
			return inventory;
		}
	}
	
	public void unlock(GenericItem key) {
		if (unlockCondition.apply(key)) {
			locked = false;
		}
	}
	
	public boolean isLocked() {
		return locked;
	}
}

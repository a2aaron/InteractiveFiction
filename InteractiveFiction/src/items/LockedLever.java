package items;

import java.util.concurrent.Callable;
import java.util.function.Function;

public class LockedLever extends Lever implements ILockedItem {

	boolean locked = true;
	Function<GenericItem, Boolean> unlockCondition;
	
	public LockedLever(String name, String description, GenericItem key) {
		super(name, description);
		leverPosition = LeverPosition.up; // Always default up
		unlockCondition = (GenericItem testKey) -> {
			return testKey == key;
		};
	}
	
	public LockedLever(String name, String description, Function<GenericItem, Boolean> unlock) {
		super(name, description);
		unlockCondition = unlock;
	}
	
	@Override
	public void useItem() {
		if (isLocked()) {
			System.out.println("You pull the lever, but it moves back up... Perhaps it needs something");
		} else {
			super.useItem();
		}
	}
	
	public boolean isLocked() {
		return locked;
	}

	public void unlock(GenericItem key) {
		if (unlockCondition.apply(key)) {
			locked = false;
		}
	}
}

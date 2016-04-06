package items;

public abstract class BreakableItem extends AbstractItem {
	boolean isBroken = false;
	
	public BreakableItem(String name, String description) {
		super(name, description);
	}
	
	public void breakItem() {
		isBroken = true;
	}
	
	public boolean isBroken() {
		return isBroken;
	}
}

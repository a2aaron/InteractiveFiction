package items.utility;

import org.json.JSONObject;

import inventory.Inventory;

public class GenericTakeableItem extends GenericItem implements ITakeableItem {

	public GenericTakeableItem(String name, String description) {
		super(name, description);
	}

	public GenericTakeableItem() {
		super();
	}

	@Override
	public void takeItem(Inventory destination) {
		if (isTakeable()) {
			destination.addItem(this);
		}
	}

	@Override
	public boolean isTakeable() {
		return true;
	}
}

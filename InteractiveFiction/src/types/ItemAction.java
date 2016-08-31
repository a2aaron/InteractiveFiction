package types;

import items.GenericItem;

public class ItemAction extends Action { 
	public GenericItem directObject; // The object used in the action
	// ex: Look at the [room]
	public GenericItem indirectObject; // The  object used by another object
	//ex: Use the key on the [door]

	// Use [object1] on [object2]
	public ItemAction(Verb verb, GenericItem directObject, GenericItem indirectObject) {
		super(verb); // This lets us only use our fields and not the superclass fields
		this.directObject = directObject;
		this.indirectObject = indirectObject;
	}

	// Use [object]
	public ItemAction(Verb verb, GenericItem directObject) {
		this(verb, directObject, null);
	}

	public ItemAction(Verb verb) {
		this(verb, null);
	}
	
	@Override
	public GenericItem getDirectObject() {
		return directObject;
	}

	@Override
	public GenericItem getIndirectObject() {
		return indirectObject;
	}
}

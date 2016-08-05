package types;

import items.AbstractItem;

public class ItemAction extends Action { 
	public AbstractItem directObject; // The object used in the action
	// ex: Look at the [room]
	public AbstractItem indirectObject; // The  object used by another object
	//ex: Use the key on the [door]

	// Use [object1] on [object2]
	public ItemAction(Verb verb, AbstractItem directObject, AbstractItem indirectObject) {
		super(verb); // This lets us only use our fields and not the superclass fields
		this.directObject = directObject;
		this.indirectObject = indirectObject;
	}

	// Use [object]
	public ItemAction(Verb verb, AbstractItem directObject) {
		this(verb, directObject, null);
	}

	public ItemAction(Verb verb) {
		this(verb, null);
	}
	
	@Override
	public AbstractItem getDirectObject() {
		return directObject;
	}

	@Override
	public AbstractItem getIndirectObject() {
		return indirectObject;
	}
}

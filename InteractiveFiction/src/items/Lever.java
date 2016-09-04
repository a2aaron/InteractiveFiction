package items;

public class Lever extends GenericItem implements IUseableItem {
    public enum LeverPosition {up, down};
    LeverPosition leverPosition = LeverPosition.up;
    /*
    String upFlipDescription = "A lever fliped up.";
    String downFlipDescription = "A lever fliped down.";
    String upFlipMessage = "You move the lever up.";
    String downFlipMessage = "You move the lever down.";
    */
    public Lever(String name, String description, LeverPosition initialPosition) {
        super(name, description);
        leverPosition = initialPosition;
    }
    
    public Lever(String name, LeverPosition initialPosition) {
    	super(name, "A lever in the " + initialPosition.toString() + " position.");
    	leverPosition = initialPosition;
    }

    public Lever(String name, String description) {
		this(name, description, LeverPosition.up);
	}

	public void useItem() {
        if (leverPosition == LeverPosition.up) {
            leverPosition = LeverPosition.down;
        } else {
            leverPosition = LeverPosition.up;     
        }
        System.out.println("You move the lever to the " + leverPosition.toString() + " position.");
        setDescription("A lever in the " + leverPosition.toString() + " position.");
    }
    
    public LeverPosition getLeverPosition() {
        return leverPosition;
    }

	@Override
	public boolean isUseable() {
		return true;
	}

}

package items;

public class Lever extends GenericItem {
    public enum LeverPosition {up, down};
    LeverPosition leverPosition;

    public Lever(String name, String description, LeverPosition initialPosition) {
        super(name, description);
        leverPosition = initialPosition;
    }
    
    public Lever(String name, LeverPosition initialPosition) {
    	super(name, "A lever in the " + initialPosition.toString() + " position.");
    	leverPosition = initialPosition;
    }

    @Override
    public void useItem() {
        if (leverPosition == LeverPosition.up) {
            leverPosition = LeverPosition.down;
        } else {
            leverPosition = LeverPosition.up;     
        }
        System.out.println("You move the lever to the " + leverPosition.toString() + " position.");
        super.setDescription("A lever in the " + leverPosition.toString() + " position.");
    }
    
    public LeverPosition getLeverPosition() {
        return leverPosition;
    }

}

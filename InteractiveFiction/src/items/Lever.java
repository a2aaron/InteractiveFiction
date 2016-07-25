package items;

public class Lever extends AbstractItem {
    public enum LeverPosition {Up, Down};
    LeverPosition leverPosition;

    public Lever(String name, String description, LeverPosition initialPosition) {
        super(name, description);
        leverPosition = initialPosition;
    }

    @Override
    public void useItem() {
        if (leverPosition == LeverPosition.Up) {
            leverPosition = LeverPosition.Down;
            System.out.println("You move the lever to the down position.");
        } else {
            leverPosition = LeverPosition.Up;
            System.out.println("You move the lever to the up position.");
        }
    }
    
    public LeverPosition getLeverPosition() {
        return leverPosition;
    }

}

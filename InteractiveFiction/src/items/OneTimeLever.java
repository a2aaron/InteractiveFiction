package items;

public class OneTimeLever extends Lever {
	String postFlipDescription = "";
	String onFlipMessage = "";
	
	public OneTimeLever(String name, String description) {
		super(name, description, LeverPosition.up);
	}

	
	@Override
	public void useItem() {
		if (leverPosition == LeverPosition.up) {
			leverPosition = LeverPosition.down;
			System.out.println("You pull down the lever.");
		} else {
			System.out.println("No reason to flip it. Just leave it be");
		}
	}

}

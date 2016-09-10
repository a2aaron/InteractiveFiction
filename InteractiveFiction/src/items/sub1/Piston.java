package items.sub1;

import items.Lever;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Piston extends Lever {
    public enum PistonState {open, closed};
    PistonState pistonState = PistonState.open;
    
	public Piston(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	
	public Piston(PistonState pistonState) {
		this.pistonState = pistonState;
	}
	
	@Override
	public void useItem() {
        if (pistonState == PistonState.open) {
        	pistonState = PistonState.closed;
        	System.out.println("You close the piston");
        	setDescription("A closed piston");
        } else {
        	pistonState = PistonState.open;
        	System.out.println("You open the piston");
        	setDescription("An open piston");
        }
    }
	
	public PistonState getPistonState() {
		return pistonState;
	}
	
	@Override
	public LeverPosition getLeverPosition() {
		throw new NotImplementedException();
	};

}

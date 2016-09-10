package items.sub1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import items.sub1.Piston.PistonState;

public class PistonSolution {
	List<Piston> pistons;
	public PistonSolution() {
		for (int i = 0; i < 3; i++) {
			if (new Random().nextBoolean()) {
				pistons.add(new Piston(PistonState.open));
			} else {
				pistons.add(new Piston(PistonState.closed));
			}
		}
	}
	
	public List<Piston> getPistons() {
		return pistons;
	}
}

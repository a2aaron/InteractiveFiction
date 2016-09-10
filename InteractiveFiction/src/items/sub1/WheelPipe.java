package items.sub1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import items.Lever;
import items.sub1.Piston.PistonState;
import items.utility.GenericItem;

public class WheelPipe extends GenericItem {
	boolean solved = false;
	PistonSolution solution;
	List<Piston> pistons;
	
	
	public WheelPipe(List<Piston> pistons, PistonSolution solution) {
		this.pistons = pistons;
		this.solution = solution;
	}
	
	public void useItem() {
		if (getPistonState(pistons) == getPistonState(solution.getPistons())) {
			solved = true;
		} else {
			System.out.println("Nothing happens... Something must be wrong with the pipes");
		}
	}
	
	private List<PistonState> getPistonState(List<Piston> pistionList) {
		List<PistonState> states = new ArrayList<PistonState>();
		pistionList.forEach((piston) -> states.add(piston.getPistonState()));
		return states;
	}
}

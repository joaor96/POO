package path_simulation;
import pec.Event;
/**
 * A type of event that prints on the terminal an observation of the Simulation
 * @author Joao,Sara
 */
class Observation extends Event{
	PathSimulation simulation;
	static int number=0;
	 /**
	  * Creates an Observation
	  * @param _time time of the Observation
	  * @param _simulation Simulation is given to check the parameters
	  */
	Observation(double _time, PathSimulation _simulation){
		super(_time);
		simulation = _simulation;
	}
	
	/**
	 * Method to print updated data about the simulation from time to time
	 */
	public void simulateEvent(){
		System.out.println("Observation " + (number++) + ":");
		System.out.println("		Present instant:		" + simulation.currTime);
		System.out.println("		Number of realized events:	" + simulation.nbEvents);
		System.out.println("		Population size:		" + simulation.pop.size);
		
		String answer = "no";
		if(simulation.bestPath != null && simulation.bestPath.path.getLast()==simulation.finalPoint) {
			answer = "yes";
		}
		System.out.println("		Final point has been hit:	" + answer);
		
		System.out.println("		Path of the best fit individual:" + simulation.bestPath);
		if(simulation.bestPath != null)
			System.out.println("		Cost/Comfort:		" + simulation.bestPath.cost + "/" + simulation.bestComfort);
		else
			System.out.println("		No path");
		
	}
	
	@Override
	public String toString() {
		return "Event: Observation at t = " + time;
	}
}
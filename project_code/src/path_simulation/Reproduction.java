package path_simulation;
import pec.Event;

/**
 * A type of Event that makes an Individual reproduce
 * @author Joao, Sara
 *
 */
class Reproduction extends Event{
	Individual id;
	PathSimulation sim;
	
	/**
	 * Creates an Event of type Reproduction
	 * @param _time Time of the Event
	 * @param _id Individual on which the Event will be applied
	 * @param _simulation The Simulation is passed to access methods and parameters
	 */
	Reproduction(double _time, Individual _id, PathSimulation _simulation){
		super(_time);
		id=_id;
		sim=_simulation;
	}
	
	/**
	 * Method simulating the reproduction of an Individual
	 */
	public void simulateEvent(){
		if (sim.pop.indList.contains(id)) {
			// create path to be inherited by child
			int min_len = (int)(0.9*id.path.getLength());
			int len = min_len + (int)(id.comf*(id.path.getLength()-min_len));		
			Path childPath = new Path(sim.initPoint);
			
			for(int i=1; i<len; i++)
				childPath.updatePath(id.path.path.get(i));
			
			// create new Individual and set its parameters to the inherited ones (path and current position)
			Individual child = new Individual(childPath.path.getLast(),childPath);
			sim.initInd(child);
			
			//Add next Reproduction
			double time = sim.currTime + sim.setTime(id, sim.reprP);
			//System.out.println("New event at time = " + time);
			if(time< id.timeDeath)
				sim.pec.addEvPEC(new Reproduction(time, id, sim));
		}
	}
	
	@Override
	public String toString() {
		return "Event: Reproduction , id= " + id + " at t = " + time;
	}
}

package simulation;

class Reproduction extends Event{
	Reproduction(int _time, int _prob){
		super(_time,_prob);
	}
	
	void simulateEvent(){
		
	}
	
	@Override
	public String toString() {
		return "Event: Reproduction ; Time: " + time;
	}
}
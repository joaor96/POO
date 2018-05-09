package path_simulation;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
/*
 * A Population has a the current size, the max size and a method that applies an Epidemics to the current Population
 */
class Population {
	int size;
	int maxSize;
	LinkedList<Individual> indList;
	static int i=0;
	
	Population(int _maxSize){
		maxSize=_maxSize;
		if(maxSize>=0)
			indList = new LinkedList<Individual>();
		else {
			System.out.println("Max size of population must be greater than 0");
		}
	}
	
	/*
	 * Method that adds an Individual to the Population
	 */
	void addIndividual(Individual ind) {
		indList.add(ind);
		size++;
		
		if(size>maxSize)
			epidemics();
	}
	
	/*
	 * Method that removes an individual from a Population
	 */
	void remIndividual(Individual ind) {
		try {
			indList.remove(ind);
		}catch(Exception e) {
			System.out.println("Impossible to remove the Individual");
			return;
		}
		size--;
	}
	
	/*
	 * Method that applies an epidemic occurrence in the Population: needs Individual list
	 */
	void epidemics() {	
		Collections.sort(indList, new Comparator<Individual>() {
			@Override
			public int compare(Individual i1, Individual i2) {
				int ret=0;
				double dif = i1.getComf() - i2.getComf();
				if(dif > 0)
					ret=-1;
				else if(dif < 0)
					ret=1;
				
				return ret;
			}
		});
		/*
		if(i==0)
			System.out.println(indList);
		i=1;;
		*/
		
		//System.out.println("\n" + indList.size() + " ff " + size + " " + maxSize + "\n");
		int min = indList.size()-1;
		while (min != 4) {
		    if(!killProb(indList.get(min).getComf())) {
		    	remIndividual(indList.get(min));
		    	
		    }
		    min--;
		}
	}
	
	// Method that determines if Individual survives epidemic
	boolean killProb(double comf){
		comf *= 100;
		
		Random rand = new Random();
		int prob = rand.nextInt();
		prob %= 100;
		
		if (comf <= prob) return true;
		else return false;
	}
	
	@Override
	public String toString() {
		String ret="";
		ret = ret + "Population: size= " + size + "\n";
		Iterator<Individual> it= this.indList.iterator();
		while(it.hasNext()) {
			ret= ret + it.next().toString() + "\n";
		}
		return ret;
		
	}
}

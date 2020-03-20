package nl.plaatsoft.knightsquest.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * The Class ScoreDAO
 * 
 * @author wplaat
 */
public class ScoreDAO {

	/** The local. */
	private ArrayList<Score> local = new ArrayList<>();
	
	/** The global. */
	private ArrayList<Score> global = new ArrayList<>();
		
	/**
	 * Adds the local.
	 *
	 * @param score the score
	 * @return the int
	 */
	public int addLocal(Score score) {
		local.add(score);
		
		sort(local);
		
		int count = 0;
		Iterator<Score> iter = local.iterator();    	
		while (iter.hasNext()) {
			count++;
			if (score == (Score) iter.next()) {
				break;
			}
		}
		
		// Return highscore place
		return count;		
	}
	
	/**
	 * Sort.
	 *
	 * @param list the list
	 */
	private void sort(ArrayList <Score> list) {
        ScoreSort comparator = new ScoreSort();
        Collections.sort(list, comparator);
	}
	
	/**
	 * Adds the global.
	 *
	 * @param score the score
	 */
	public void addGlobal(Score score) {
		global.add(score);
	}
		
	/**
	 * Gets the local.
	 *
	 * @return the local
	 */
	public ArrayList<Score> getLocal() {
		
		return local;
	}
	
	/**
	 * Gets the global.
	 *
	 * @return the global
	 */
	public ArrayList<Score> getGlobal() {
		
		return global;
	}
	
	/**
	 * Clear global.
	 */
	public void clearGlobal() {
		
		Iterator<Score> iter = global.iterator();    	
		while (iter.hasNext()) {
			@SuppressWarnings("unused")
			Score score = (Score) iter.next();
			iter.remove();
		}
	}
}

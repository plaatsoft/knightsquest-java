/**
 *  @file
 *  @brief 
 *  @author wplaat
 *
 *  Copyright (C) 2008-2016 PlaatSoft
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, version 3.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package nl.plaatsoft.knightsquest.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * The Class ScoreDAO.
 */
public class ScoreDAO {

	/** The local. */
	private ArrayList<Score> local = new ArrayList<Score>();
	
	/** The global. */
	private ArrayList<Score> global = new ArrayList<Score>();
		
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

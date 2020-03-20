package nl.plaatsoft.knightsquest.tools;

import java.util.List;
import java.util.Random;

import nl.plaatsoft.knightsquest.model.Land;

/**
 * The Class MyRandom.
 * 
 * @author wplaat
 */
public class MyRandom {
	
	/** The rnd. */
	private static Random rnd;
	
	/**
	 * Clear.
	 */
	public static void clear() {
		rnd=null;
	}
	
	/**
	 * Next int.
	 *
	 * @param value the value
	 * @return the int
	 */
	public static int nextInt(int value) {
		
		if (rnd==null) {
			rnd = new Random(MyData.getSeed());
		}
		return rnd.nextInt(value);
	}
	
	/**
	 * Next land.
	 *
	 * @param list the list
	 * @return the land
	 */
	public static Land nextLand(List<Land> list) { 
		
		Land land = null;
		
		if (list.size()>0) {
			land = list.get(rnd.nextInt(list.size()));
		}
		return land;
	}
}

package nl.plaatsoft.knightsquest.tools;

import java.util.List;
import java.util.Random;

import nl.plaatsoft.knightsquest.model.Land;

public class MyRandom {
	
	private static Random rnd;
	
	public static void clear() {
		rnd=null;
	}
	
	public static int nextInt(int value) {
		if (rnd==null) {
			rnd = new Random(MyData.getSeed());
		}
		return rnd.nextInt(value);
	}
	
	public static Land nextLand(List<Land> list) {
		Land land = null;
		if (!list.isEmpty()) {
			land = list.get(rnd.nextInt(list.size()));
		}
		return land;
	}
}

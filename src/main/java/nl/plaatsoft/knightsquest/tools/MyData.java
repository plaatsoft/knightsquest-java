package nl.plaatsoft.knightsquest.tools;

import nl.plaatsoft.knightsquest.ui.Constants;

/**
 * The Class MyData.
 * 
 * @author wplaat
 */
public class MyData {
	
	/** The Constant MODE_1P. */
	public static final int MODE_1P = 1;
	
	/** The Constant MODE_2P. */
	public static final int MODE_2P = 2;
		
	/** The level. */
	private static int level = 0;
	
	/** The map. */
	private static int map = 1;
	
	/** The mode. */
	private static int mode = 1;
		
	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public static int getPlayers() {
		
		int amount=getBots() + 1;
			
		if (mode==MODE_2P) {
			
			amount = 2;
		}	
		return amount; 
	}
	
	/**
	 * Gets the seed.
	 *
	 * @return the seed
	 */
	public static int getSeed() {
		
		switch (map) {
		
			case 1: return 1234;
			case 2: return 64;
			case 3: return 4341;
			case 4: return 66;
			case 5: return 70;
			case 6: return 72;
			
			default: return map;
		}
	}
	
	
	/**
	 * Gets the bots.
	 *
	 * @return the bots
	 */
	public static int getBots() {
		
		int value = level+1;
		if (value>5) {
			value = 5;
		}
		
		if (mode==MODE_2P) {
			value=0;
		}
		
		return value;
	}
	
	/**
	 * Gets the lands.
	 *
	 * @return the lands
	 */
	public static int getLands() {		
		int value = 3;		
		return value;
	}
	
	/**
	 * Gets the towers.
	 *
	 * @return the towers
	 */
	public static int getTowers() {
		
		int value = Math.round(level/3)+1;
		if (value>3) {
			value = 3;
		}
		
		if (mode==MODE_2P) {
			value=1;
		}
		
		return value;
	}

	/**
	 * Gets the chance new soldier.
	 *
	 * @return the chance new soldier
	 */
	public static int getChanceNewSoldier() {
		
		int value=1;
		
		if (level==0) {
			// 25%			
			value = 4;
		}
		
		if (level==1) {
			// 33%		
			value = 3;
		}
		
		if (level==2) {
			// 50%
			value = 2;
		}
		
		if (level>2) {
			// 100%
			value = 1;
		}		
		return value;
	}
	
	/**
	 * Gets the harbors.
	 *
	 * @return the harbors
	 */
	public static int getHarbors() {
		
		int value = 0;
		
		if (level>0) {
			value = 4;
		}
		
		if (level>1) {
			value = 6;
		}
		
		if (level>2) {
			value = 8;
		}
		
		if (level>3) {
			value = 10;
		}		
		return value;
	}
	
	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public static int getLevel() {
		return level;
	}
	
	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public static void setLevel(int level) {
		MyData.level = level;
	}

	/**
	 * Sets the mode.
	 *
	 * @param mode the new mode
	 */
	public static void setMode(int mode) {
		MyData.mode = mode;
	}
	
	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	public static int getMode() {
		return mode;
	}
	
	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public static int getMap() {
		return map;
	}

	/**
	 * Sets the map.
	 *
	 * @param map the new map
	 */
	public static void setMap(int map) {
		
		MyRandom.clear();
		MyData.map = map;
	}
	
	/**
	 * Gets the next map.
	 *
	 * @param map the map
	 * @return the next map
	 */
	public static int getNextMap(int map) {
		
		int value = 0;
		
		int tmp1 = (map/10) * 10;
		int tmp2 = (map%10);
		tmp2++;
		if (tmp2>6) {
			tmp2=1;
			tmp1+=10;			
		}
		value = tmp1+tmp2;
				
		/* Maximum map reached */
		if (tmp1>Constants.MAX_LEVELS) {
			value = 0;
		}
		
		return value;
	}
}

package nl.plaatsoft.knightsquest.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Class Player
 * 
 * @author wplaat
 */
public class Player {

	/** The id. */
	// Player Generic 
	private int id;		
	
	/** The type. */
	private PlayerEnum type;
	
	/** The region. */
	private List <Region> region;

	/** The conquer. */
	// Player Statistics
	private int conquer;
	
	/** The upgrades. */
	private int upgrades;
	
	/** The moves. */
	private int moves;
	
	/** The creates. */
	private int creates;
		
	/**
	 * Instantiates a new player.
	 *
	 * @param id the id
	 * @param type the type
	 */
	public Player(int id, PlayerEnum type) {
		this.type = type;
		this.id = id;
		
		conquer=0;
		upgrades=0;
		moves=0;
		creates=0;
		
		region = new ArrayList<Region>();
	}
	
	/**
	 * Draw.
	 */
	public void draw() {		
		Iterator<Region> iter = region.iterator();  
		while (iter.hasNext()) {
			Region region = (Region) iter.next();
			region.draw();
		}
	}
		
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "playerId="+id;
	}
	
	/**
	 * Gets the land size.
	 *
	 * @return the land size
	 */
	public int getLandSize() {
		
		int amount = 0;
		Iterator<Region> iter = getRegion().iterator();
		while (iter.hasNext()) {
			Region region = (Region) iter.next();				
			amount += region.getLands().size();  
		}
		return amount;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the region.
	 *
	 * @return the region
	 */
	public List<Region> getRegion() {
		return region;
	}
	
	/**
	 * Sets the region.
	 *
	 * @param region the new region
	 */
	public void setRegion(List<Region> region) {
		this.region = region;
	}

	/**
	 * Gets the upgrades.
	 *
	 * @return the upgrades
	 */
	public int getUpgrades() {
		return upgrades;
	}

	/**
	 * Sets the upgrades.
	 *
	 * @param upgrades the new upgrades
	 */
	public void setUpgrades(int upgrades) {
		this.upgrades = upgrades;
	}

	/**
	 * Gets the moves.
	 *
	 * @return the moves
	 */
	public int getMoves() {
		return moves;
	}

	/**
	 * Sets the moves.
	 *
	 * @param moves the new moves
	 */
	public void setMoves(int moves) {
		this.moves = moves;
	}

	/**
	 * Gets the creates.
	 *
	 * @return the creates
	 */
	public int getCreates() {
		return creates;
	}

	/**
	 * Sets the creates.
	 *
	 * @param creates the new creates
	 */
	public void setCreates(int creates) {
		this.creates = creates;
	}

	/**
	 * Gets the conquer.
	 *
	 * @return the conquer
	 */
	public int getConquer() {
		return conquer;
	}

	/**
	 * Sets the conquer.
	 *
	 * @param conquer the new conquer
	 */
	public void setConquer(int conquer) {
		this.conquer = conquer;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public PlayerEnum getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(PlayerEnum type) {
		this.type = type;
	}	
}

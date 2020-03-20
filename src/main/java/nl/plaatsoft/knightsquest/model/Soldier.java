package nl.plaatsoft.knightsquest.model;

import nl.plaatsoft.knightsquest.tools.MyFactory;

/**
 * The Class Soldier
 * 
 * @author wplaat
 */
public class Soldier {
	
	/** The type. */
	private SoldierEnum type;
	
	/** The enabled. */
	private boolean enabled;
	
	/** The player. */
	private Player player;
	
	/** The land. */
	private Land land;	

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return ""+type;
	}

	/**
	 * Instantiates a new soldier.
	 *
	 * @param type the type
	 * @param player the player
	 * @param land the land
	 */
	public Soldier(SoldierEnum type, Player player, Land land) {

		enabled = false;
		
		this.type = type;
		this.setLand(land);
		this.player = player;
	}
	
	/**
	 * Draw.
	 */
	public void draw() {
		
		//log.info("draw "+type+" playerId="+player.getId()+" [x="+land.getX()+"|y="+land.getY()+"]");
				
		int offset = 0;
		if ((land.getY() % 2)==1) {
			offset = land.getSize()*2;
		} 
	             	
		double posX = land.getSize()+(land.getX()*(land.getSize()*4))+offset-2;
		double posY = (land.getY()*land.getSize())+(land.getSize()/2)-2;
		
		land.getGc().setGlobalAlpha(1.0);			
		
		boolean red = false;
		if (player.getType()==PlayerEnum.HUMAN_LOCAL) {
			red = enabled;
		}
		land.getGc().drawImage(MyFactory.getSoldierDAO().get(type, red), posX, posY);
	}
		
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public SoldierEnum getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(SoldierEnum type) {
		this.type = type;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Gets the land.
	 *
	 * @return the land
	 */
	public Land getLand() {
		return land;
	}

	/**
	 * Sets the land.
	 *
	 * @param land the new land
	 */
	public void setLand(Land land) {
		this.land = land;
	}

	/**
	 * Checks if is enabled.
	 *
	 * @return true, if is enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}

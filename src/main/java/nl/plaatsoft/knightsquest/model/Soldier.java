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

import nl.plaatsoft.knightsquest.tools.MyFactory;

/**
 * The Class Soldier.
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

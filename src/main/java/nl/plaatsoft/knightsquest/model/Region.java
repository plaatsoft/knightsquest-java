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
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nl.plaatsoft.knightsquest.tools.MyFactory;

/**
 * The Class Region.
 */
public class Region {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger( Region.class);
	
	/** The id. */
	private int id;	
	
	/** The player. */
	private Player player;
	
	/** The lands. */
	private List <Land> lands = new ArrayList<Land>();

	/**
	 * Instantiates a new region.
	 *
	 * @param id the id
	 * @param player the player
	 */
	public Region(int id, Player player) {
		this.id = id;
		this.player = player;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "regionId="+id;
	}
	
	/**
	 * Draw.
	 */
	public void draw() {
			
		//log.info("draw region [id="+id+"|landSize="+lands.size()+"]");
		
		Iterator<Land> iter1 = lands.iterator();  
		while (iter1.hasNext()) {
			Land land = (Land) iter1.next();
			land.draw();
		}
	}
	
	/**
	 * Food available.
	 *
	 * @return the int
	 */
	public int foodAvailable() {
		
		int foodDemand = 0;
		
		Iterator<Land> iter = lands.iterator();						
		while (iter.hasNext()) {				
			Land land = (Land) iter.next();
			if (land.getSoldier()!=null) {					
				foodDemand += MyFactory.getSoldierDAO().food(land.getSoldier().getType());
			}		
		}	
				
		int foodAvailable = lands.size()- foodDemand;	
		
		//log.info("regionId="+id+" foodDemand="+foodDemand+" foodProduction="+lands.size()+" foodAvailable="+foodAvailable);
		
		return foodAvailable;
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
	 * Sets the lands.
	 *
	 * @param lands the new lands
	 */
	public void setLands(List<Land> lands) {
		this.lands = lands;
	}
	
	/**
	 * Gets the lands.
	 *
	 * @return the lands
	 */
	public List<Land> getLands() {
		return lands;
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
}

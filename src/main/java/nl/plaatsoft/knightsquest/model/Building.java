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
 * The Class Building.
 */
public class Building {

	/** The land. */
	private Land land;	
	
	/** The type. */
	private BuildingEnum type;

	/**
	 * Instantiates a new building.
	 *
	 * @param type the type
	 * @param land the land
	 */
	public Building(BuildingEnum type, Land land) {
		
		this.type = type;
		this.setLand(land);
	}
	
	/**
	 * Draw.
	 */
	void draw() {
		
		int offset = 0;
		if ((land.getY() % 2)==1) {
			offset = land.getSize()*2;
		} 
	             	
		double posX = land.getSize()+(land.getX()*(land.getSize()*4))+offset-2;
		double posY = (land.getY()*land.getSize())+(land.getSize()/2)-2;
		
		land.getGc().setGlobalAlpha(1.0);			
		
		land.getGc().drawImage(MyFactory.getBuildingDAO().get(type), posX, posY);
		
		//log.info("draw ["+land.getX()+","+land.getY()+"]");
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public BuildingEnum getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(BuildingEnum type) {
		this.type = type;
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
}

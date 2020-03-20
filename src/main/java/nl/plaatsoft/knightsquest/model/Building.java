package nl.plaatsoft.knightsquest.model;

import nl.plaatsoft.knightsquest.tools.MyFactory;

/**
 * The Class Building
 * 
 * @author wplaat
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

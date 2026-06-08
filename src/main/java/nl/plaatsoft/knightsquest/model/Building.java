package nl.plaatsoft.knightsquest.model;

import nl.plaatsoft.knightsquest.tools.MyFactory;

public class Building {

	private Land land;
	private BuildingEnum type;

	public Building(BuildingEnum type, Land land) {
		
		this.type = type;
		this.setLand(land);
	}
	
	void draw() {
		
		int offset = 0;
		if ((land.getY() % 2)==1) {
			offset = land.getSize()*2;
		} 
	             	
		double posX = land.getSize()+(land.getX()*(land.getSize()*4))+offset-2;
		double posY = (land.getY()*land.getSize())+(land.getSize()/2)-2;
		
		land.getGc().setGlobalAlpha(1.0);
		land.getGc().drawImage(MyFactory.getBuildingDAO().get(type), posX, posY);
	}

	public BuildingEnum getType() {
		return type;
	}
	public void setType(BuildingEnum type) {
		this.type = type;
	}
	public Land getLand() {
		return land;
	}
	public void setLand(Land land) {
		this.land = land;
	}
}

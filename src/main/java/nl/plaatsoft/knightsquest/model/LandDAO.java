package nl.plaatsoft.knightsquest.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import nl.plaatsoft.knightsquest.tools.MyData;
import nl.plaatsoft.knightsquest.tools.MyFactory;
import nl.plaatsoft.knightsquest.tools.MyRandom;
import nl.plaatsoft.knightsquest.tools.MySound;
import nl.plaatsoft.knightsquest.ui.Constants;

/**
 * The Class LandDAO.
 * 
 * @author wplaat
 */
public class LandDAO {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger( LandDAO.class);
	
	/** The lands. */
	private Land[][] lands = new Land[Constants.SEGMENT_X][Constants.SEGMENT_Y];
	
	/** The water. */
	private Image water;
	
	/** The forest. */
	//private Image ocean;
	private Image forest;
	
	/** The coast. */
	private Image coast;
	
	/** The rock. */
	private Image rock;
	
	/** The grass. */
	private Image grass;
			
	/**
	 * Inits the.
	 */
	private void init() {
		water = new Image("images/water.png");
		//ocean = new Image("images/ocean.png");
		forest = new Image("images/forest.png");
		coast = new Image("images/coast.png");
		rock = new Image("images/rock.png");
		grass = new Image("images/grass.png");
	}
	
	/**
	 * Gets the texture.
	 *
	 * @param gc the gc
	 * @param type the type
	 * @return the texture
	 */
	public void getTexture(GraphicsContext gc, LandEnum type) {
		
		if (water==null) {
			init();
		}
		
		switch(type) {
		
			case FOREST:
					gc.setFill(new ImagePattern(forest, 0, 0, 1, 1, true));
					break;	
					
			case GRASS:
					//gc.setFill(Color.DARKGREEN);
					gc.setFill(new ImagePattern(grass, 0, 0, 1, 1, true));
					break;	
					
			case COAST: 
					//gc.setFill(Color.BURLYWOOD);
					gc.setFill(new ImagePattern(coast, 0, 0, 1, 1, true));
					break;	
				
			case MOUNTAIN:
					gc.setFill(new ImagePattern(rock, 0, 0, 1, 1, true));
					break;
				
			case WATER:
					//gc.setFill(Color.BLUE);
					gc.setFill(new ImagePattern(water, 0, 0, 1, 1, true));
					break;
					
			case OCEAN:
					gc.setFill(Color.DARKBLUE);
					//gc.setFill(new ImagePattern(ocean, 0, 0, 1, 1, true));
					break;
					
			default:
					gc.setFill(Color.BLACK);
					break;
		}
	}
	
	/**
	 * Gets the bot enemy land with soldier.
	 *
	 * @param land the land
	 * @return the bot enemy land with soldier
	 */
	public List <Land> getBotEnemyLandWithSoldier(Land land) {
		
		log.debug("enter");
		
		List <Land> list2 = new ArrayList<Land>();
		
		/* If source land is harbor get all free ports */
		if ((land.getBuilding()!=null) && (land.getBuilding().getType()==BuildingEnum.HARBOR)) {			 
			Iterator<Land> iter3 = MyFactory.getBuildingDAO().getFreeHarbor(land).iterator();
			while (iter3.hasNext()) {				
				 Land land3 = (Land) iter3.next();
				 if ((land3.getPlayer()!=null) && !land3.getPlayer().equals(land.getPlayer())) {
				 	if (land3.getSoldier()!=null) {
				 		if ((land3.getSoldier().getType()!=SoldierEnum.TOWER) && (land3.getSoldier().getType()!=SoldierEnum.CROSS)) {
				 			
							int attackStrength = land.getSoldier().getType().getValue();
							int defendStrength = land3.getSoldier().getType().getValue();
							
							if (attackStrength>defendStrength) {
							
								list2.add(land3);
							}
				 		}
				 	}
				 }				
			 }						
		}
		 		 
		 /* Get all enemy lands */
		List <Land> list1 = getNeigbors(land);
		Iterator<Land> iter1 = list1.iterator();
						
		while (iter1.hasNext()) {				
			Land land1 = (Land) iter1.next();
			if ((land1.getType()!=LandEnum.WATER) && (land1.getType()!=LandEnum.OCEAN)) {
				
			 	 if ((land1.getPlayer()!=null) && !land1.getPlayer().equals(land.getPlayer())) {
			 		if (land1.getSoldier()!=null) {			 			
			 			if ((land1.getSoldier().getType()!=SoldierEnum.TOWER) && (land1.getSoldier().getType()!=SoldierEnum.TOWER)) {
			 				
			 				int attackStrength = land.getSoldier().getType().getValue();
							int defendStrength = land1.getSoldier().getType().getValue();
							
							if (attackStrength>defendStrength) {
							
								list2.add(land1);
							}
			 			}
			 		}
				}
			}
		}	
		
		log.debug("leave");
		return list2;
	}
	
	/**
	 * Gets the bot enemy land without soldier.
	 *
	 * @param land the land
	 * @return the bot enemy land without soldier
	 */
	public List <Land> getBotEnemyLandWithoutSoldier(Land land) {
		
		log.debug("enter");
		
		List <Land> list2 = new ArrayList<Land>();
		
		/* If source land is harbor get all free ports */
		if ((land.getBuilding()!=null) && (land.getBuilding().getType()==BuildingEnum.HARBOR)) {			 
			Iterator<Land> iter3 = MyFactory.getBuildingDAO().getFreeHarbor(land).iterator();
			while (iter3.hasNext()) {				
				 Land land3 = (Land) iter3.next();
				 if ((land3.getPlayer()!=null) && !land3.getPlayer().equals(land.getPlayer())) {
					if ((land3.getSoldier()==null) || ((land3.getSoldier()!=null) && (land3.getSoldier().getType()==SoldierEnum.CROSS))) {				
						list2.add(land3);
				 	}
				 }				
			 }						
		}
		 		 
		 /* Get all enemy lands without soldiers */
		List <Land> list1 = getNeigbors(land);
		Iterator<Land> iter1 = list1.iterator();
						
		while (iter1.hasNext()) {				
			Land land1 = (Land) iter1.next();
			if ((land1.getType()!=LandEnum.WATER) && (land1.getType()!=LandEnum.OCEAN)) {
				
			 	 if ((land1.getPlayer()!=null) && !land1.getPlayer().equals(land.getPlayer())) {
			 		if ((land1.getSoldier()==null) || ((land1.getSoldier()!=null) && (land1.getSoldier().getType()==SoldierEnum.CROSS))) {					
						
			 			list2.add(land1);
			 		}
				}
			}
		}	
		
		log.debug("leave");
		return list2;
	}
		
	/**
	 * Gets the bot upgrade soldier.
	 *
	 * @param land the land
	 * @param region the region
	 * @return the bot upgrade soldier
	 */
	public List <Land> getBotUpgradeSoldier(Land land, Region region) {
				
		List <Land> list2 = new ArrayList<Land>();
		
		List <Land> list1 = getNeigbors(land);
		
		Iterator<Land> iter1 = list1.iterator();						
		while (iter1.hasNext()) {				
			Land land1 = (Land) iter1.next();
			if ((land1.getPlayer()!=null) && land1.getPlayer().equals(land.getPlayer()) &&
				(land1.getSoldier()!=null) && (land1.getSoldier().getType()==SoldierEnum.PAWN)) 
			{				
				// Only upgrade if there is enough food
				SoldierEnum nextType = MyFactory.getSoldierDAO().upgrade(land.getSoldier().getType());					
				if ((nextType!=null) && (region.foodAvailable()>MyFactory.getSoldierDAO().food(nextType))) {
					list2.add(land1);	
				}				
			}
		}			
		return list2;
	}
		
	/**
	 * Gets the bot own land.
	 *
	 * @param land the land
	 * @return the bot own land
	 */
	public List <Land> getBotOwnLand(Land land) {
		
		log.debug("enter");
		
		List <Land> list2 = new ArrayList<Land>();
		
		 /* If source land is harbor get all free ports */
		 if ((land.getBuilding()!=null) && (land.getBuilding().getType()==BuildingEnum.HARBOR)) {
			 
			 Iterator<Land> iter3 = MyFactory.getBuildingDAO().getFreeHarbor(land).iterator();
			 while (iter3.hasNext()) {				
				 Land land3 = (Land) iter3.next();
				 if ((land3.getType()!=LandEnum.WATER) && (land3.getType()!=LandEnum.OCEAN)) {				
					if ((land3.getPlayer()!=null) && land3.getPlayer().equals(land.getPlayer())) {
						if ((land3.getSoldier()!=null) && (land3.getSoldier().getType()!=SoldierEnum.TOWER)) {
						} else {
							list2.add(land3);
						}
					}
				 }
			 }						
		}
		 		 
		List <Land> list1 = getNeigbors(land);
		Iterator<Land> iter1 = list1.iterator();
						
		while (iter1.hasNext()) {				
			Land land1 = (Land) iter1.next();
			if ((land1.getType()!=LandEnum.WATER) && (land1.getType()!=LandEnum.OCEAN)) {				
				if ((land1.getPlayer()!=null) && land1.getPlayer().equals(land.getPlayer())) {
					if ((land1.getSoldier()!=null) && (land1.getSoldier().getType()!=SoldierEnum.TOWER)) {
						// Soldier on own land, skip it.
					} else {
						// log.info("land [x="+land.getX()+"|y="+land.getY()+"|player="+land.getPlayer()+"|type="+land.getType()+"|soldier="+land.getSoldier()+"]");
						list2.add(land1);
					}
				}
			}
		}			
		
		log.debug("leave");
		return list2;
	}
	
	/**
	 * Gets the bot new land.
	 *
	 * @param land the land
	 * @return the bot new land
	 */
	public List <Land> getBotNewLand(Land land) {
		
		log.debug("enter");

		List <Land> list2 = new ArrayList<Land>();
		
		 /* If source land is harbor get all free ports */
		 if ((land.getBuilding()!=null) && (land.getBuilding().getType()==BuildingEnum.HARBOR)) {
			 
			 Iterator<Land> iter3 = MyFactory.getBuildingDAO().getFreeHarbor(land).iterator();
			 while (iter3.hasNext()) {				
				 Land land3 = (Land) iter3.next();
				 if ((land3.getType()!=LandEnum.WATER) && (land3.getType()!=LandEnum.OCEAN) && (land3.getPlayer()==null)) {
					 list2.add(land3);
				 }
			 }						
		}
		 		 
		List <Land> list1 = getNeigbors(land);
		Iterator<Land> iter1 = list1.iterator();						
		while (iter1.hasNext()) {			
			Land land1 = (Land) iter1.next();			
			if ((land1.getType()!=LandEnum.WATER) && (land1.getType()!=LandEnum.OCEAN) && (land1.getPlayer()==null)) {
				//log.info("land [x="+land.getX()+"|y="+land.getY()+"|player="+land.getPlayer()+"|type="+land.getType()+"|soldier="+land.getSoldier()+"]");
				list2.add(land1);				
			}
		}		
		
		log.debug("leave");
		return list2;
	}

	/**
	 * Gets the bot region land.
	 *
	 * @param land the land
	 * @return the bot region land
	 */
	public List <Land> getBotRegionLand(Land land) {
		
		log.debug("enter");
		
		List <Land> list2 = new ArrayList<Land>();
		
		List <Land> list1 = getNeigbors(land);
		Iterator<Land> iter1 = list1.iterator();						
		while (iter1.hasNext()) {			
			Land land1 = (Land) iter1.next();			
			if ((land1.getType()!=LandEnum.WATER) && (land1.getType()!=LandEnum.OCEAN) && 
				(land1.getPlayer()!=null) && land1.getPlayer().equals(land.getPlayer()) && land1.getRegion()==0) {				
					list2.add(land1);				
			}
		}
		
		log.debug("leave");
		return list2;
	}
	
	/**
	 * Gets the player selected land.
	 *
	 * @param mouseX the mouse X
	 * @param mouseY the mouse Y
	 * @return the player selected land
	 */
	public Land getPlayerSelectedLand(double mouseX, double mouseY) {
		
		for (int x=0; x<Constants.SEGMENT_X; x++) {			
			for (int y=0; y<Constants.SEGMENT_Y; y++) {
			    if (lands[x][y].getPolygon().contains(mouseX, mouseY)) {
			       return lands[x][y];
			    }		
			}
		}	
		return null;
	}
	
	/**
	 * Move soldier.
	 *
	 * @param source the source
	 * @param destination the destination
	 */
	public void moveSoldier(Land source, Land destination) {
		
		//log.info("Move soldier ["+source.getX()+","+source.getY()+"]->["+destination.getX()+","+destination.getY()+"]");
		
		Region srcRegion = MyFactory.getRegionDAO().getRegion(source);
		Region dstRegion = MyFactory.getRegionDAO().getRegion(destination);
				
		if ((destination.getSoldier()!=null) && (destination.getPlayer()!=null) && destination.getPlayer().equals(source.getPlayer()) && (destination.getSoldier().getType()!=SoldierEnum.CROSS)) {
			
			// Soldier upgrade			
			if (destination.getSoldier().getType()!=SoldierEnum.PAWN) {
				// upgrade only allowed with Pawn, so skip upgrade
				return;
			}
			
			SoldierEnum nextType = MyFactory.getSoldierDAO().upgrade(source.getSoldier().getType());				
			if ((nextType!=null) && srcRegion.foodAvailable()<=MyFactory.getSoldierDAO().food(nextType)) {
				//log.info("Not food enough, upgrade skipped");
				// Not enough food, so skip upgrade.
				return;
			}
			
			source.getPlayer().setUpgrades(source.getPlayer().getUpgrades()+1);
			source.getSoldier().setType(nextType);
			
			MySound.play(source.getPlayer(), MySound.CLIP_UPGRADE);
		}
				
		if ((destination.getSoldier()!=null) && (destination.getPlayer()!=null) && !destination.getPlayer().equals(source.getPlayer())) { 
		
			if (destination.getSoldier().getType()==SoldierEnum.CROSS) {
				// Soldier funeral
				MySound.play(source.getPlayer(), MySound.CLIP_FUNERAL);	
			} else {
				// Soldier Fight				
				MySound.play(source.getPlayer(), MySound.CLIP_FIGHT);	
			}
		}
	 	   
		source.getPlayer().setMoves(source.getPlayer().getMoves()+1);
		
		destination.setSoldier(source.getSoldier());
		destination.setPlayer(source.getPlayer());		
		destination.getSoldier().setLand(destination);		
		destination.getSoldier().setEnabled(false);
			
		source.setSoldier(null);
				
		// Remove land from old region		
		if (dstRegion!=null) {
			source.getPlayer().setConquer(source.getPlayer().getConquer()+1);
			dstRegion.getLands().remove(destination);
		}
		
		// Add land to new region
		srcRegion.getLands().add(destination);
		
		if ((source.getPlayer().getType()!=PlayerEnum.BOT) && (MyData.getMode()==MyData.MODE_2P)) {
			MyFactory.getUDPServer().move(source.getX(),source.getY(),destination.getX(),destination.getY());
		}
		
		MySound.play(source.getPlayer(), MySound.CLIP_STEP);	
	}
	
	/**
	 * Creates the soldier.
	 *
	 * @param source the source
	 * @param destination the destination
	 */
	private void createSoldier(Land source, Land destination) {
		
		Region srcRegion = MyFactory.getRegionDAO().getRegion(source);
		Region dstRegion = MyFactory.getRegionDAO().getRegion(destination);
		
		Soldier soldier = new Soldier(SoldierEnum.PAWN, source.getPlayer(), destination);
		source.getPlayer().setCreates(source.getPlayer().getCreates()+1);
		source.getSoldier().setEnabled(false);
		
		destination.setSoldier(soldier);
		destination.setPlayer(source.getPlayer());		
		destination.getSoldier().setLand(destination);		
		destination.getSoldier().setEnabled(false);
		
		// Remove land from old region		
		if (dstRegion!=null) {
			dstRegion.getLands().remove(destination);			
		}
		
		// Add land to new region
		srcRegion.getLands().add(destination);
		
		MySound.play(source.getPlayer(), MySound.CLIP_CREATE);
		if ((source.getPlayer().getType()!=PlayerEnum.BOT) && (MyData.getMode()==MyData.MODE_2P)) {
			MyFactory.getUDPServer().create(destination.getX(),destination.getY());
		}
	}
		
	/**
	 * Reset selected.
	 */
	public void resetSelected() {

		for (int x=0; x<Constants.SEGMENT_X; x++) {			
			for (int y=0; y<Constants.SEGMENT_Y; y++) {
				lands[x][y].setDestination(false);
				lands[x][y].setSource(false);
			}		
		}
	}
		
	/**
	 * Gets the player land has friendly neigbor.
	 *
	 * @param land the land
	 * @param player the player
	 * @return the player land has friendly neigbor
	 */
	public boolean getPlayerLandHasFriendlyNeigbor(Land land, Player player) {
			
		List <Land> list1 = getNeigbors(land);
		Iterator<Land> iter1 = list1.iterator();						
		while (iter1.hasNext()) {			
			Land land1 = (Land) iter1.next();			
			if ((land1.getPlayer()!=null) && land1.getPlayer().equals(player)) {				
			   return true;		
			}
		}			
		return false;
	}

	/**
	 * Sets the player soldier move destinations.
	 *
	 * @param land the new player soldier move destinations
	 */
	public void setPlayerSoldierMoveDestinations(Land land) {
				
		List <Land> list1;
				
		 /* If source land is harbor get all free ports */
		 if ((land.getBuilding()!=null) && (land.getBuilding().getType()==BuildingEnum.HARBOR)) {
			 
			 Iterator<Land> iter2 = MyFactory.getBuildingDAO().getFreeHarbor(land).iterator();
			 while (iter2.hasNext()) {				
				 Land land2 = (Land) iter2.next();
				 land2.setDestination(true);						
			 }						
		 }
				
		if (land.getSoldier().getType()==SoldierEnum.PAWN) {
			// Pawn can move to two land tills per turn
			list1 = getNeigbors2(land);
		} else {
			// All other soldier types can only move one land till per turn
			list1 = getNeigbors(land);
		}
		Iterator<Land> iter1 = list1.iterator();
						
		while (iter1.hasNext()) {				
			Land land1 = (Land) iter1.next();
			if ( (land1.getType()!=LandEnum.WATER) && 
				 (land1.getType()!=LandEnum.OCEAN)) {
			
				// Pawn can not move two tills if no friendly land is connected
				if (land.getSoldier().getType()==SoldierEnum.PAWN) {
					if (!getPlayerLandHasFriendlyNeigbor(land1, land.getPlayer())) {
						continue;
					}
				}
							 
				// Target land is free
				if (land1.getPlayer()==null) {
					 land1.setDestination(true);
					 continue;
				 }
				 
				 // Target land has no soldier
				 if (land1.getSoldier()==null) {
					 land1.setDestination(true);
					 continue;
				 }
				
				 // Target land has cross
				 if ((land1.getSoldier()!=null) && (land1.getSoldier().getType()==SoldierEnum.CROSS)) { 
					 land1.setDestination(true);
					 continue;
				 }
				 
				 // Target land is own by weaker enemy
				 if ((land1.getSoldier()!=null) && (land.getSoldier().getType().getValue()>land1.getSoldier().getType().getValue())) { 
					 land1.setDestination(true);
					 continue;
				 }
				 
				 				 				 				 
				 // Target land is owned, Pawn soldier available and there is food available for upgrade. 
				 Region region = MyFactory.getRegionDAO().getRegion(land);
				 SoldierEnum nextType = MyFactory.getSoldierDAO().upgrade(land.getSoldier().getType());	
				 
				 if ((nextType!=null) && (land1.getPlayer()!=null) && land1.getPlayer().equals(land.getPlayer()) && 
					 (land1.getSoldier()!=null) && (land1.getSoldier().getType()==SoldierEnum.PAWN) &&
					 (region.foodAvailable()>MyFactory.getSoldierDAO().food(nextType))) {
					 land1.setDestination(true);
					 continue;				 
				 }
			}
		}	
	}
	
	/**
	 * Sets the player new soldier destinations.
	 *
	 * @param land the new player new soldier destinations
	 */
	public void setPlayerNewSoldierDestinations(Land land) {
		
		List <Land> list1 = getNeigbors(land);
		Iterator<Land> iter1 = list1.iterator();
						
		while (iter1.hasNext()) {				
			Land land1 = (Land) iter1.next();
			if ( (land1.getType()!=LandEnum.WATER) && 
				 (land1.getType()!=LandEnum.OCEAN)) {
			
				 // Target land is free
				 if (land1.getPlayer()==null) {
					 land1.setDestination(true);
					 continue;
				 }
				
				// Target land without soldier
				 if (land1.getSoldier()==null) {
					 land1.setDestination(true);
					 continue;
				 }
				 
				// Target land has cross
				 if ((land1.getSoldier()!=null) && (land1.getSoldier().getType()==SoldierEnum.CROSS)) { 
					 land1.setDestination(true);
					 continue;
				 }
			}
		}	
	}
	
	/**
	 * Gets the source land.
	 *
	 * @return the source land
	 */
	private Land getSourceLand() {
		
		/* Return selected source land. Total map scan needed else harbors will not work */
		for (int x=0; x<Constants.SEGMENT_X; x++) {
			for (int y=0; y<Constants.SEGMENT_Y; y++) {			
				if (lands[x][y].isSource()){
					return lands[x][y];
				}
			}
		}
		return null;			
	}
				
	/**
	 * Do player actions.
	 *
	 * @param land the land
	 * @param player the player
	 */
	public void doPlayerActions(Land land, Player player) {
	
		if (land.isDestination()) {
						
			// Search source land
			Land source = getSourceLand();
			if (source!=null) {				
				if (source.getSoldier().getType()==SoldierEnum.TOWER) { 
													
					createSoldier(source, land);
						
				} else {
										
					moveSoldier(source, land);
				}
					
				/* Rebuild all regions */
				int regions = MyFactory.getRegionDAO().detectedRegions();		
				MyFactory.getRegionDAO().rebuildRegions(regions);	
			}	
			
			resetSelected();
			
		} else {
						
			if ( (land.getPlayer()!=null) && 
			     (land.getPlayer().equals(player)) && 
				 (land.getSoldier()!=null) && 
				  land.getSoldier().isEnabled() &&			     
				 (land.getSoldier().getType()!=SoldierEnum.CROSS))
			{		
				
				if (land.isSource()) {
				
					// Deselect soldier
					resetSelected();
					
				} else {
					
					// Select soldier
					resetSelected();
					
					/* Select source */
					land.setSource(true);
					
					/* Select destination */				
					if (land.getSoldier().getType()==SoldierEnum.TOWER) {
						setPlayerNewSoldierDestinations(land);
					} else {
						setPlayerSoldierMoveDestinations(land);
					}
				}
			}
		}	
	}
		
	/**
	 * Get Neighers of select x,y coordinate.
	 *
	 * @param land the land
	 * @return the neigbors
	 */
	public List <Land> getNeigbors(Land land) {
				
		int x = land.getX();
		int y = land.getY();
		
		List <Land> list = new ArrayList<Land>();
		
		if (y+1<Constants.SEGMENT_Y) {
			list.add(lands[x][y+1]);
		}
		
		if (y-1>=0) {
			list.add(lands[x][y-1]);
		}
		
		if (y+2<Constants.SEGMENT_Y) {
			list.add(lands[x][y+2]);
		}
		
		if (y-2>=0) {
			list.add(lands[x][y-2]);
		}
		
		if (y%2==1) {	
			if ((x+1<Constants.SEGMENT_X) && (y+1<Constants.SEGMENT_Y)) {
				list.add(lands[x+1][y+1]);
			}
			
			if ((x+1<Constants.SEGMENT_X) && (y-1>=0)) {
				list.add(lands[x+1][y-1]);
			}						
		} else {
			
			if ((x-1>=0) && (y+1<Constants.SEGMENT_Y)) {
				list.add(lands[x-1][y+1]);
			}
			
			if ((x-1>=0) && (y-1>=0)) {
				list.add(lands[x-1][y-1]);
			}			
		}
		
		return list;
	}
		
	/**
	 * Gets the neigbors 2.
	 *
	 * @param land the land
	 * @return the neigbors 2
	 */
	public List <Land> getNeigbors2(Land land) {
		
		int x = land.getX();
		int y = land.getY();
		
		List <Land> list = new ArrayList<Land>();
		
		if (y+1<Constants.SEGMENT_Y) {
			list.add(lands[x][y+1]);
		}
		
		if (y-1>=0) {
			list.add(lands[x][y-1]);
		}
		
		if (y+2<Constants.SEGMENT_Y) {
			list.add(lands[x][y+2]);
		}
		
		if (y-2>=0) {
			list.add(lands[x][y-2]);
		}
		
		if (y-4>=0) {
			list.add(lands[x][y-4]);
		}
		
		if (y-3>=0) {
			list.add(lands[x][y-3]);
		}
		
		if ((x+1<Constants.SEGMENT_X) && (y-2>=0)) {
			list.add(lands[x+1][y-2]);
		}
				
		if (x+1<Constants.SEGMENT_X) {
			list.add(lands[x+1][y]);
		}
		
		if ((x+1<Constants.SEGMENT_X) && (y+2<Constants.SEGMENT_Y)) {
			list.add(lands[x+1][y+2]);
		}
		
		if (y+3<Constants.SEGMENT_Y) {
			list.add(lands[x][y+3]);
		}
		
		if (y+4<Constants.SEGMENT_Y) {
			list.add(lands[x][y+4]);
		}
			
		if ((x-1>=0) && (y+2<Constants.SEGMENT_Y)) {
			list.add(lands[x-1][y+2]);
		}
				
		if (x-1>=0) {
			list.add(lands[x-1][y]);
		}
		
		if ((x-1>=0) && (y-2>=0)) {
			list.add(lands[x-1][y-2]);
		}
				
		if (y%2==1) {	
			
			if ((x+1<Constants.SEGMENT_X) && (y-3>=0)) {
				list.add(lands[x+1][y-3]);
			}
			
			if ((x+1<Constants.SEGMENT_X) && (y+3<Constants.SEGMENT_Y)) {
				list.add(lands[x+1][y+3]);
			}
			
			if ((x+1<Constants.SEGMENT_X) && (y+1<Constants.SEGMENT_Y)) {
				list.add(lands[x+1][y+1]);
			}
			
			if ((x+1<Constants.SEGMENT_X) && (y-1>=0)) {
				list.add(lands[x+1][y-1]);
			}			
			
		} else {
			if ((x-1>=0) && (y+3<Constants.SEGMENT_Y)) {
				list.add(lands[x-1][y+3]);
			}
						
			if ((x-1>=0) && (y-3>=0)) {
				list.add(lands[x-1][y-3]);
			}
			
			if ((x-1>=0) && (y+1<Constants.SEGMENT_Y)) {
				list.add(lands[x-1][y+1]);
			}
			
			if ((x-1>=0) && (y-1>=0)) {
				list.add(lands[x-1][y-1]);
			}		
		}
				
		return list;
	}
	
	/**
	 * Scale map.
	 *
	 * @param scale the scale
	 */
	public void scaleMap(double scale) {
		
		//log.info("scale="+scale);
		for (int x=0; x<Constants.SEGMENT_X; x++) {			
			for (int y=0; y<Constants.SEGMENT_Y; y++) {			
				lands[x][y].setScale(scale);	
			}
		}		
	}

	/**
	 * Optimize map.
	 */
	private void optimizeMap() {
		
		for (int x=0; x<Constants.SEGMENT_X; x++) {
			
			for (int y=0; y<Constants.SEGMENT_Y; y++) {
			
				if (lands[x][y].getType()==LandEnum.COAST) {
					
					int found=0;
					List <Land> list = getNeigbors(lands[x][y]);
					Iterator<Land> iter = list.iterator();    	
					while (iter.hasNext()) {
						Land land = (Land) iter.next();
						if (land.getType()==LandEnum.WATER) {
							found=1;
						}
					}				
					if (found==0) {
					    lands[x][y].setType(LandEnum.GRASS);
					}
				};				
			}
		}		
	}
		
	/**
	 * Creates the forest mountain.
	 */
	private void createForestMountain() {
				
		for (int x=0; x<Constants.SEGMENT_X; x++) {
			
			for (int y=0; y<Constants.SEGMENT_Y; y++) {
			
				if ((lands[x][y].getType()==LandEnum.GRASS) && (MyRandom.nextInt(2)==1)) {
					
					if (MyRandom.nextInt(2)==1) {
						lands[x][y].setType(LandEnum.FOREST);
					} else {
						lands[x][y].setType(LandEnum.MOUNTAIN);
					}
				};				
			}
		}		
	}

	/**
	 * Creates the water.
	 */
	private  void createWater() {
		
		for (int x=0; x<Constants.SEGMENT_X; x++) {
			
			for (int y=0; y<Constants.SEGMENT_Y; y++) {
			
				if (lands[x][y].getType()==LandEnum.COAST) {					
					List <Land> list = getNeigbors(lands[x][y]);
					Iterator<Land> iter = list.iterator();    	
					while (iter.hasNext()) {
						Land segment = (Land) iter.next();
						if (segment.getType()==LandEnum.OCEAN) {
							segment.setType(LandEnum.WATER);
						}
					}					
				};				
			}
		}		
	}

	/**
	 * Creates the coast.
	 */
	private void createCoast() {
		
		for (int x=0; x<Constants.SEGMENT_X; x++) {
			
			for (int y=0; y<Constants.SEGMENT_Y; y++) {
			
				if (lands[x][y].getType()==LandEnum.GRASS) {					
					List <Land> list = getNeigbors(lands[x][y]);
					Iterator<Land> iter = list.iterator();    	
					while (iter.hasNext()) {
						Land segment = (Land) iter.next();
						if (segment.getType()==LandEnum.OCEAN) {
							segment.setType(LandEnum.COAST);
						}
					}
					
				};				
			}
		}		
	}
	
	
	/**
	 * Creates the grass.
	 *
	 * @param level the level
	 */
	private void createGrass(int level) {
						
		for (int i=0; i<level; i++) {
			
			int x = MyRandom.nextInt(Constants.SEGMENT_X);
			int y = MyRandom.nextInt(Constants.SEGMENT_Y);

			for (int j=0; j<Constants.SEGMENT_Y; j++) {
				
				lands[x][y].setType(LandEnum.GRASS);
		 
				List <Land> list = getNeigbors(lands[x][y]);
				Iterator<Land> iter = list.iterator();
									
				int next = MyRandom.nextInt(list.size()); 
				int count=0;
				
				while (iter.hasNext()) {				
					Land land = (Land) iter.next();
					land.setType(LandEnum.GRASS);
					if (count++==next) {
						
						x=land.getX();
						y=land.getY(); 

						break;
					}					
				}
			}
		}
	}
	
	/**
	 * Creates the map.
	 *
	 * @param gc the gc
	 * @param size the size
	 * @param level the level
	 */
	public void createMap(GraphicsContext gc, int size, int level) {
					
		for (int x=0; x<Constants.SEGMENT_X; x++) {	
			for (int y=0; y<Constants.SEGMENT_Y; y++) {
				lands[x][y] = new Land(gc, x, y, size, LandEnum.OCEAN);				
			}
		}
		
		createGrass(level);
		createCoast();	
		createWater();
		createForestMountain();
		optimizeMap();			
	}		
		
	/**
	 * Draw.
	 */
	public void draw() {
		for (int x=0; x<Constants.SEGMENT_X; x++) {					
			for (int y=0; y<Constants.SEGMENT_Y; y++) {				
				lands[x][y].draw();				
			}
		}		
	}
	
	/**
	 * Gets the lands.
	 *
	 * @return the lands
	 */
	public Land[][] getLands() {
		return lands;
	}	
}

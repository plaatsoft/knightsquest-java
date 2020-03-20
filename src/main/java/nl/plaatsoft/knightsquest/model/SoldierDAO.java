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

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.image.Image;

import nl.plaatsoft.knightsquest.tools.MyData;
import nl.plaatsoft.knightsquest.tools.MyFactory;
import nl.plaatsoft.knightsquest.tools.MyRandom;

/**
 * The Class SoldierDAO.
 */
public class  SoldierDAO {
	
	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(SoldierDAO.class);
		
	/** The tower. */
	private Image tower;
	
	/** The tower 2. */
	private Image tower2;
	
	/** The pawn. */
	private Image pawn;
	
	/** The pawn 2. */
	private Image pawn2;
	
	/** The horse. */
	private Image horse;
	
	/** The horse 2. */
	private Image horse2;
	
	/** The bishop. */
	private Image bishop;
	
	/** The bishop 2. */
	private Image bishop2;
	
	/** The queen. */
	private Image queen;
	
	/** The queen 2. */
	private Image queen2;
	
	/** The king. */
	private Image king;
	
	/** The king 2. */
	private Image king2;
	
	/** The cross. */
	private Image cross;
			
	/**
	 * Inits the.
	 *
	 * @param size the size
	 */
	public void init(int size) {
		tower = new Image("images/tower.png", size+4, size+4, false, false);
		tower2 = new Image("images/tower2.png", size+4, size+4, false, false);
		pawn = new Image("images/pawn.png", size+4, size+4, false, false);
		pawn2 = new Image("images/pawn2.png", size+4, size+4, false, false);
		horse = new Image("images/horse.png", size+4, size+4, false, false);
		horse2 = new Image("images/horse2.png", size+4, size+4, false, false);
		bishop = new Image("images/bishop.png", size+4, size+4, false, false);
		bishop2 = new Image("images/bishop2.png", size+4, size+4, false, false);
		queen = new Image("images/queen.png", size+4, size+4, false, false);
		queen2 = new Image("images/queen2.png", size+4, size+4, false, false);
		king = new Image("images/king.png", size+4, size+4, false, false);
		king2 = new Image("images/king2.png", size+4, size+4, false, false);
		cross = new Image("images/cross.png", size+4, size+4, false, false);
	}

	/**
	 * Creates the bot soldier.
	 *
	 * @param region the region
	 */
	public void createBotSoldier(Region region) {
						
		/* Create new Soldier if there is enough food */  
		if (region.foodAvailable()>=food(SoldierEnum.PAWN)) {
						
			if (MyRandom.nextInt(MyData.getChanceNewSoldier())==0) {
				Land land1 = MyFactory.getRegionDAO().getTowerPosition(region);
				if (land1!=null) {
					
					/* Create new Soldier if there is room around the castle */  
					List <Land> list2 = MyFactory.getLandDAO().getBotOwnLand(land1);			
					Iterator<Land> iter2 = list2.iterator();  						
					if (iter2.hasNext()) {				
						Land land2 = (Land) iter2.next();
						
						Soldier soldier = new Soldier(SoldierEnum.PAWN, region.getPlayer(), land2);
						land2.setSoldier(soldier);
					}
				}
			}							
		}
	}
		
	/**
	 * New soldier arrive.
	 *
	 * @param region the region
	 */
	public void newSoldierArrive(Region region) {
		
		/* Inform player that new soldier has arrived if there is enough food */  
		if (region.foodAvailable()>=food(SoldierEnum.PAWN)) {
						
			Land land = MyFactory.getRegionDAO().getTowerPosition(region);
			if (land!=null) {
				
				/* Enable tower, so player know that new soldier is available */  
				land.getSoldier().setEnabled(true);
			}							
		}
	}
		
	/**
	 * Enable soldier.
	 *
	 * @param player the player
	 * @return the int
	 */
	public int enableSoldier(Player player) {
		
		int count = 0;
	
		Iterator<Region> iter2 = player.getRegion().iterator();  
		while (iter2.hasNext()) {
			Region region = (Region) iter2.next();
			
			//log.info(player+" "+region+" size="+region.getLands().size());
			
			Iterator<Land> iter3 = region.getLands().iterator();  
			while (iter3.hasNext()) {
				Land land = (Land) iter3.next();
				
				if ((land.getSoldier()!=null) && (land.getSoldier().getType()!=SoldierEnum.TOWER) && (land.getSoldier().getType()!=SoldierEnum.CROSS)) {
					land.getSoldier().setEnabled(true);
					count++;
				}
			}
		}
		return count;
	}
		
	/**
	 * Move bot soldier.
	 *
	 * @param region the region
	 */
	/* Move bots smart */
	public void moveBotSoldier(Region region) {
		
		log.debug("enter");
		
		Iterator<Land> iter1 = region.getLands().iterator();  
		while (iter1.hasNext()) {
			Land land1 = (Land) iter1.next();
			
			if ((land1.getSoldier()!=null) && 
				land1.getSoldier().isEnabled() && 
				(land1.getSoldier().getType()!=SoldierEnum.CROSS) && 
				(land1.getSoldier().getType()!=SoldierEnum.TOWER)) {
					
				//log.info(land1.getSoldier().getType()+" found [x="+land1.getX()+"|y="+land1.getY()+"]");	
				
				/* -------------------------------------- */
				/* Conquer enemy land with weaker soldier */
				/* -------------------------------------- */
				
				List <Land> list2 = MyFactory.getLandDAO().getBotEnemyLandWithSoldier(land1);	
				Land land2 = MyRandom.nextLand(list2);
				if (land2!=null) {
					//log.info(land1.getPlayer()+" kill enemy land with soldier");				
					MyFactory.getLandDAO().moveSoldier(land1, land2);
					return; 
				}
				
				/* -------------------------------------------- */
				/* Conquer enemy land without soldier */
				/* -------------------------------------------- */
				
				List <Land> list7 = MyFactory.getLandDAO().getBotEnemyLandWithoutSoldier(land1);	
				Land land7 = MyRandom.nextLand(list7);
				if (land7!=null) {
					//log.info(land1.getPlayer()+" Capture enemy land");				
					MyFactory.getLandDAO().moveSoldier(land1, land7);
					return; 
				}
								
				/* ------------------- */
				/* Upgrade bot soldier */
				/* ------------------- */
				
				if (land1.getSoldier().getType()!=SoldierEnum.KING) {
					List <Land> list5 = MyFactory.getLandDAO().getBotUpgradeSoldier(land1, region);
					Land land5 = MyRandom.nextLand(list5);
					if (land5!=null) {		
						//log.info(land1.getPlayer()+" Upgrade soldier");		
						MyFactory.getLandDAO().moveSoldier(land1, land5);			
						return;
					}
				}
			
				/* ------------------------ */
				/* Move soldier to new land */		
				/* ------------------------ */
				
				List <Land> list4 = MyFactory.getLandDAO().getBotNewLand(land1);					
				Land land4 = MyRandom.nextLand(list4);
				if (land4!=null) {			
					//log.info(land1.getPlayer()+" Capture new land");	
					MyFactory.getLandDAO().moveSoldier(land1, land4);
					return;
				}
				
				/* ------------------------ */
				// Move soldier on own land
				/* ------------------------ */
				
				List <Land> list6 = MyFactory.getLandDAO().getBotOwnLand(land1);	
				Land land6 = MyRandom.nextLand(list6);
				if (land6!=null) {		
					//log.info(land1.getPlayer()+" Move on own land");	
					MyFactory.getLandDAO().moveSoldier(land1, land6);
					return;
				}		
			}
		}	
		log.debug("leave");
	}

	/**
	 * Gets the.
	 *
	 * @param soldier the soldier
	 * @param enabled the enabled
	 * @return the image
	 */
	public Image get(SoldierEnum soldier, boolean enabled) {
	
		switch(soldier) {
	
			case TOWER: 
				if (enabled) {
					return tower2;
				} else {
					return tower;
				}
		
			case PAWN:
				if (enabled) {
					return pawn2;
				} else {
					return pawn;
				}
								
			case BISHOP:
				if (enabled) {
					return bishop2;
				} else {
					return bishop;
				}
				
			case HORSE:
				if (enabled) {
					return horse2;
				} else {
					return horse;
				}
				
			case QUEEN:
				if (enabled) {
					return queen2;
				} else {
					return queen;
				}
				
			case KING:
				if (enabled) {
					return king2;
				} else {
					return king;
				}
				
			case CROSS:
				return cross;

			default:
				break;
		}
		return null;
	}		
	
	/**
	 * Upgrade.
	 *
	 * @param type the type
	 * @return the soldier enum
	 */
	public SoldierEnum upgrade(SoldierEnum type) {
		
		SoldierEnum value;
		
		switch(type) {
		
			case PAWN:
				value = SoldierEnum.BISHOP;
				break;
			
			case BISHOP:
				value = SoldierEnum.HORSE;
				break;
				
			case HORSE:
				value = SoldierEnum.QUEEN;
				break;
				
			case QUEEN:
				value = SoldierEnum.KING;
				break;
				
			default:
				value=null;
				break;
		}
			
		return value;
	}
	
	/**
	 * Food.
	 *
	 * @param type the type
	 * @return the int
	 */
	public int food(SoldierEnum type) {
			
		int value=0;
				
		switch(type) {
	
			case PAWN:
				value = 2;
				break;
				
			case BISHOP:
				value = 4;
				break;
				
			case HORSE:
				value = 8;
				break;
				
			case QUEEN:
				value = 10;
				break;
				
			case KING:
				value = 12;
				break;
				
			default:
				value = 0;					
				break;
						
		}		
		return value;
	}
}




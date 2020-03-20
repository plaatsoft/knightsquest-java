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

package nl.plaatsoft.knightsquest.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nl.plaatsoft.knightsquest.model.BuildingDAO;
import nl.plaatsoft.knightsquest.model.LandDAO;
import nl.plaatsoft.knightsquest.model.SettingDAO;
import nl.plaatsoft.knightsquest.model.PlayerDAO;
import nl.plaatsoft.knightsquest.model.RegionDAO;
import nl.plaatsoft.knightsquest.model.ScoreDAO;
import nl.plaatsoft.knightsquest.model.SoldierDAO;
import nl.plaatsoft.knightsquest.network.UDPServer;

/**
 * A factory for creating My objects.
 */
public class MyFactory {
    
	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(MyFactory.class);
	
    /** The score DAO. */
    private static ScoreDAO scoreDAO; 	
    
    /** The player DAO. */
    private static PlayerDAO playerDAO;
    
    /** The land DAO. */
    private static LandDAO landDAO;	
    
    /** The region DAO. */
    private static RegionDAO regionDAO;
    
    /** The soldier DAO. */
    private static SoldierDAO soldierDAO;
    
    /** The building DAO. */
    private static BuildingDAO buildingDAO;
    
    /** The setting DAO. */
    private static SettingDAO settingDAO;
    
    /** The u DP server. */
    private static UDPServer uDPServer;
					
	/**
	 * Gets the setting DAO.
	 *
	 * @return the setting DAO
	 */
	public static SettingDAO getSettingDAO() {
		
		if (settingDAO==null) {
			settingDAO = new SettingDAO();
		}
		return settingDAO;
	}
	
	/**
	 * Gets the player DAO.
	 *
	 * @return the player DAO
	 */
	public static PlayerDAO getPlayerDAO() {
		
		if (playerDAO==null) {
			playerDAO = new PlayerDAO();
		}
		return playerDAO;
	}
	
	/**
	 * Gets the score DAO.
	 *
	 * @return the score DAO
	 */
	public static ScoreDAO getScoreDAO() {
		
		if (scoreDAO==null) {
			scoreDAO = new ScoreDAO();
		}
		return scoreDAO;
	}
	
	/**
	 * Gets the region DAO.
	 *
	 * @return the region DAO
	 */
	public static RegionDAO getRegionDAO() {
		
		if (regionDAO==null) {
			regionDAO = new RegionDAO();
		}
		return regionDAO;
	}

	/**
	 * Gets the land DAO.
	 *
	 * @return the land DAO
	 */
	public static LandDAO getLandDAO() {
		
		if (landDAO==null) {
			landDAO = new LandDAO();
		}
		return landDAO;
	}

	/**
	 * Gets the soldier DAO.
	 *
	 * @return the soldier DAO
	 */
	public static SoldierDAO getSoldierDAO() {
		
		if (soldierDAO==null) {
			soldierDAO = new SoldierDAO();
		}
		return soldierDAO;
	}
	
	/**
	 * Gets the building DAO.
	 *
	 * @return the building DAO
	 */
	public static BuildingDAO getBuildingDAO() {
		
		if (buildingDAO==null) {
			buildingDAO = new BuildingDAO();
		}
		return buildingDAO;
	}
		
	/**
	 * Clear factory.
	 */
	public static void clearFactory() {
		
		playerDAO = null;
		landDAO = null;		
		regionDAO = null;
		soldierDAO = null;
		buildingDAO = null;
	}

	/**
	 * Gets the UDP server.
	 *
	 * @return the UDP server
	 */
	public static UDPServer getUDPServer() {
		if (uDPServer==null) {			
			try {
				uDPServer = new UDPServer();
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return uDPServer;
	}


}

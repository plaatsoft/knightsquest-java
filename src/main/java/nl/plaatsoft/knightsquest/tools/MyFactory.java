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

public class MyFactory {

  private static final Logger log = LogManager.getLogger(MyFactory.class);
  private static ScoreDAO scoreDAO;
  private static PlayerDAO playerDAO;
  private static LandDAO landDAO;
  private static RegionDAO regionDAO;
  private static SoldierDAO soldierDAO;
  private static BuildingDAO buildingDAO;
  private static SettingDAO settingDAO;
  private static UDPServer uDPServer;

  public static SettingDAO getSettingDAO() {

    if (settingDAO == null) {
      settingDAO = new SettingDAO();
    }
    return settingDAO;
  }

  public static PlayerDAO getPlayerDAO() {

    if (playerDAO == null) {
      playerDAO = new PlayerDAO();
    }
    return playerDAO;
  }

  public static ScoreDAO getScoreDAO() {
    if (scoreDAO == null) {
      scoreDAO = new ScoreDAO();
    }
    return scoreDAO;
  }

  public static RegionDAO getRegionDAO() {
    if (regionDAO == null) {
      regionDAO = new RegionDAO();
    }
    return regionDAO;
  }

  public static LandDAO getLandDAO() {
    if (landDAO == null) {
      landDAO = new LandDAO();
    }
    return landDAO;
  }

  public static SoldierDAO getSoldierDAO() {
    if (soldierDAO == null) {
      soldierDAO = new SoldierDAO();
    }
    return soldierDAO;
  }

  public static BuildingDAO getBuildingDAO() {
    if (buildingDAO == null) {
      buildingDAO = new BuildingDAO();
    }
    return buildingDAO;
  }

  public static void clearFactory() {
    playerDAO = null;
    landDAO = null;
    regionDAO = null;
    soldierDAO = null;
    buildingDAO = null;
  }

  public static UDPServer getUDPServer() {
    if (uDPServer == null) {
      try {
        uDPServer = new UDPServer();
      } catch (Exception e) {
        log.error(e.getMessage());
      }
    }
    return uDPServer;
  }


}

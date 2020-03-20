package nl.plaatsoft.knightsquest.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class SettingDAO.
 * 
 * @author wplaat
 */
public class SettingDAO {

	/** The log. */
	private static final Logger log = LogManager.getLogger(SettingDAO.class.getName());

	/** The settings. */
	private static Setting settings = new Setting();
	
	/** The filename. */
	private static String filename = System.getProperty("user.home")+"/KnightsQuest.dat";

	/**
	 * Save.
	 */
	public void save() {

		log.info("save settings {}", filename);

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(settings);
			oos.close();

		} catch (Exception e) {
			log.error(e.getMessage());
		} 	
	}

	/**
	 * Load.
	 */
	public void load() {

		log.info("load settings {}", filename);

		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			settings = (Setting) ois.readObject();

			ois.close();
		} catch (Exception e) {
			log.error(e.getMessage());			
		}
	}

	/**
	 * Gets the settings.
	 *
	 * @return the settings
	 */
	public Setting getSettings() {
		return settings;
	}

	/**
	 * Sets the settings.
	 *
	 * @param settings the new settings
	 */
	public void setSettings(Setting settings) {
		SettingDAO.settings = settings;
	}
}

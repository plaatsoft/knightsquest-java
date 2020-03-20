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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class SettingDAO.
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

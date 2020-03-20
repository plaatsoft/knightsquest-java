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

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The Class MyMusic.
 */
public class MyMusic {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(MyMusic.class);
	
	/** The mp. */
	private static MediaPlayer mp;
	
	/**
	 * Inits the.
	 */
	public static void init() {
		log.info("init");
		String path = MyMusic.class.getResource("/sounds/intro.mp3").toExternalForm();
        Media media = new Media(path);
        mp = new MediaPlayer(media);
        mp.setCycleCount(MediaPlayer.INDEFINITE);
	}
	
	/**
	 * Play.
	 */
	public static void play() {  
		if (mp==null) {
			init();
		}
        if (MyFactory.getSettingDAO().getSettings().isMusicOn()) {
        	mp.play();
        }
	}
		
	/**
	 * Stop.
	 */
	public static void stop() {
		mp.stop();
	}
}

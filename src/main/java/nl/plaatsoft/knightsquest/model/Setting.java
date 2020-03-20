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

import java.io.Serializable;

import nl.plaatsoft.knightsquest.ui.Constants;

/**
 * The Class Setting.
 */
public class Setting implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The x. */
	private double x;
	
	/** The y. */
	private double y;
	
	/** The music on. */
	private boolean musicOn;
	
	/** The Sound effects on. */
	private boolean SoundEffectsOn;
	
	/** The resolution. */
	private String resolution;
	
	/** The map unlocked. */
	private boolean[] mapUnlocked = new boolean[Constants.MAX_LEVELS*10];
	
	/** The score. */
	private int[] score = new int[Constants.MAX_LEVELS*10];
		
	/**
	 * Instantiates a new setting.
	 */
	public Setting() {		
		x = 0;
		y = 0;		
		musicOn = true;		
		resolution = "640x480";
					
		for(int i=0; i<(Constants.MAX_LEVELS*10); i++) {
			mapUnlocked[i]=new Boolean(false);
		}
		mapUnlocked[1]= new Boolean(true);		
		
		for(int i=0; i<(Constants.MAX_LEVELS*10); i++) {
			score[i]=new Integer(0);
		}
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
				
		int value;
		
		if (resolution.equals("1024x768")) {
			value = 1024;
		} else if (resolution.equals("800x600")) {
			value = 800;
		} else {
			value = 640;
		}
		
		return value;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
				
		int value;
		
		if (resolution.equals("1024x768")) {
			value = 768;
		} else if (resolution.equals("800x600")) {
			value = 600;
		} else {
			value = 480;
		}

		return value;		
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Gets the resolution.
	 *
	 * @return the resolution
	 */
	public String getResolution() {
		return resolution;
	}

	/**
	 * Sets the resolution.
	 *
	 * @param resolution the new resolution
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	/**
	 * Checks if is music on.
	 *
	 * @return true, if is music on
	 */
	public boolean isMusicOn() {
		return musicOn;
	}

	/**
	 * Sets the music on.
	 *
	 * @param musicOn the new music on
	 */
	public void setMusicOn(boolean musicOn) {
		this.musicOn = musicOn;
	}

	/**
	 * Gets the map unlocked.
	 *
	 * @param map the map
	 * @return the map unlocked
	 */
	public boolean getMapUnlocked(int map) {
		return mapUnlocked[map];
	}

	/**
	 * Sets the map unlocked.
	 *
	 * @param map the map
	 * @param value the value
	 */
	public void setMapUnlocked(int map, boolean value) {
		mapUnlocked[map] = value;
	}
	
	/**
	 * Gets the score.
	 *
	 * @param map the map
	 * @return the score
	 */
	public Integer getScore(int map) {
		return score[map];
	}
	
	/**
	 * Sets the score.
	 *
	 * @param map the map
	 * @param value the value
	 */
	public void setScore(int map, int value) {
		score[map] = value;
	}
	
	/**
	 * Gets the highest map.
	 *
	 * @return the highest map
	 */
	public int getHighestMap() {
		
		for (int i=(Constants.MAX_LEVELS*10)-1; i>0; i--) {
			if (mapUnlocked[i]==true) {
				return i;
			}
		}
		return 1;
	}

	/**
	 * Checks if is sound effects on.
	 *
	 * @return true, if is sound effects on
	 */
	public boolean isSoundEffectsOn() {
		return SoundEffectsOn;
	}

	/**
	 * Sets the sound effects on.
	 *
	 * @param soundEffectsOn the new sound effects on
	 */
	public void setSoundEffectsOn(boolean soundEffectsOn) {
		SoundEffectsOn = soundEffectsOn;
	}
		
}

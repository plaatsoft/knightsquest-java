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

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

/**
 * The Class MyLabel.
 */
public class MyLabel extends Label {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(MyLabel.class);
	
	/**
	 * Instantiates a new my label.
	 *
	 * @param x the x
	 * @param y the y
	 * @param value the value
	 * @param fontSize the font size
	 * @param color the color
	 * @param options the options
	 */
	public MyLabel(double x, double y, String value, int fontSize, String color, String options) {
        
		setText(value);		
		setWrapText(true);
		setStyle("-fx-font-size:"+fontSize+"px; -fx-text-fill:"+color+"; "+options);
		
		if (x==0) {
			setMinWidth(MyFactory.getSettingDAO().getSettings().getWidth());
			setAlignment(Pos.CENTER);
			setTextAlignment(TextAlignment.CENTER);
			
		} else {
			
			setLayoutX(x);
		}
		setLayoutY(y);
	}	
	
	/**
	 * Instantiates a new my label.
	 *
	 * @param x the x
	 * @param y the y
	 * @param value the value
	 * @param fontSize the font size
	 * @param color the color
	 */
	public MyLabel(double x, double y, String value, int fontSize, String color) {
		        
		setText(value);		
		setWrapText(true);
		setStyle("-fx-font-size:"+fontSize+"px; -fx-text-fill:"+color+"; ");
		
		if (x==0) {
			setMinWidth(MyFactory.getSettingDAO().getSettings().getWidth());
			setAlignment(Pos.CENTER);
			setTextAlignment(TextAlignment.CENTER);
			
		} else {
			
			setLayoutX(x);
		}
		setLayoutY(y);
	}	
	
	/**
	 * Instantiates a new my label.
	 *
	 * @param x the x
	 * @param y the y
	 * @param value the value
	 * @param fontSize the font size
	 */
	public MyLabel(double x, double y, String value, int fontSize) {
        
		setText(value);		
		setWrapText(true);
		setStyle("-fx-font-size:"+fontSize+"px; -fx-text-fill:white; ");
		
		if (x==0) {
			setMinWidth(MyFactory.getSettingDAO().getSettings().getWidth());
			setAlignment(Pos.CENTER);
			setTextAlignment(TextAlignment.CENTER);
			
		} else {
			
			setLayoutX(x);
		}
		setLayoutY(y);
	}	
}

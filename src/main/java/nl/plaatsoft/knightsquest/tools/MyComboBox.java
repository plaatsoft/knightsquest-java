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

import javafx.scene.control.ComboBox;

/**
 * The Class MyComboBox.
 */
public class MyComboBox extends ComboBox<Object> {

	/**
	 * Instantiates a new my combo box.
	 *
	 * @param x the x
	 * @param y the y
	 * @param selected the selected
	 * @param options the options
	 * @param fontSize the font size
	 */
	public MyComboBox(int x, int y, String selected, String[] options, int fontSize ) {
	
		setLayoutX(x);
		setLayoutY(y);
		
		setValue(selected);
		
		setStyle("-fx-font-size:"+fontSize+"px;");	  
		
		for (int i=0; i<options.length; i++) {
			getItems().add(options[i]);
		}
	};       
}

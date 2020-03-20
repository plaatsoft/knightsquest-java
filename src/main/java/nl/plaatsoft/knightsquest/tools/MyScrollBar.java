package nl.plaatsoft.knightsquest.tools;

import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;

/**
 * The Class MyScrollBar.
 * 
 * @author wplaat
 */
public class MyScrollBar extends ScrollBar {

	/**
	 * Instantiates a new my scroll bar.
	 *
	 * @param max the max
	 */
	public MyScrollBar(int max) {
		setMin(0);
		setMax(max - 1);
		setValue(0);
		setUnitIncrement(1);
		setBlockIncrement(1);
		setLayoutX(MyFactory.getSettingDAO().getSettings().getWidth()-50);
		setLayoutY(125);
		setMinWidth(25);
		setMinHeight(MyFactory.getSettingDAO().getSettings().getHeight()-205);
		setOrientation(Orientation.VERTICAL);
	}
	
}

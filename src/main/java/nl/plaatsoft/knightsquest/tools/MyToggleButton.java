package nl.plaatsoft.knightsquest.tools;

import javafx.scene.control.ToggleButton;

/**
 * The Class MyToggleButton.
 */
public class MyToggleButton extends ToggleButton {

	/**
	 * Instantiates a new my toggle button.
	 *
	 * @param x the x
	 * @param y the y
	 * @param value the value
	 * @param fontSize the font size
	 */
	public  MyToggleButton(double x, double y, boolean value, int fontSize) {
		
		int width = 80;
		if (x==0) {
			x= (MyFactory.getSettingDAO().getSettings().getWidth()/2)-(width/2);
		}				
		
		setSelected(value);
		
		if (isSelected()) { 
			setText("On" );
		} else {
			setText("Off");
		}
				
	    setPrefWidth(width);
	    setLayoutX(x);
		setLayoutY(y);		
		
	    setStyle("-fx-font-size:"+fontSize+"px;");	     		
	}     
}

package nl.plaatsoft.knightsquest.tools;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

/**
 * The Class MyLabel.
 * 
 * @author wplaat
 */
public class MyLabel extends Label {
	
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

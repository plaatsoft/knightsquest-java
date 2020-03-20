package nl.plaatsoft.knightsquest.tools;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import nl.plaatsoft.knightsquest.ui.Navigator;

/**
 * The Class MyButton
 * 
 * @author wplaat
 */
public class MyButton extends Button {

	/**
	 * Instantiates a new my button.
	 *
	 * @param x the x
	 * @param y the y
	 * @param value the value
	 * @param fontSize the font size
	 * @param page the page
	 */
	public MyButton(double x, double y, String value, int fontSize, final int page) {
				
		int width = 180;
		if (x==0) {
			x= (MyFactory.getSettingDAO().getSettings().getWidth()/2)-(width/2);
		}				
		setText(value);
	    setPrefWidth(width);
	    setStyle("-fx-font-size:"+fontSize+"px;");	     
	    	  
		setLayoutX(x);
		setLayoutY(y);
		
	    setOnAction(new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent event) {
            	Navigator.go(page);
            }
        });
	}     
}

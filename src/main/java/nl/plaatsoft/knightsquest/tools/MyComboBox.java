package nl.plaatsoft.knightsquest.tools;

import javafx.scene.control.ComboBox;

/**
 * The Class MyComboBox.
 * 
 * @author wplaat
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

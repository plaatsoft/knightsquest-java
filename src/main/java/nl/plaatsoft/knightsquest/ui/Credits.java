package nl.plaatsoft.knightsquest.ui;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import nl.plaatsoft.knightsquest.tools.MyButton;
import nl.plaatsoft.knightsquest.tools.MyFactory;
import nl.plaatsoft.knightsquest.tools.MyLabel;
import nl.plaatsoft.knightsquest.tools.MyPanel;

/**
 * The Class Credits.
 * 
 * @author wplaat
 */
public class Credits extends MyPanel {

	/**
	 * Instantiates a new credits.
	 */
	public Credits() {
		
		Image image1 = new Image("images/background4.jpg");
	    BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
	    BackgroundImage backgroundImage = new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
	    Background background = new Background(backgroundImage);
	    	
	    setBackground(background);
	    
	    int y=20;
	    getChildren().add( new MyLabel(0, y, "Credits", 50, "white", "-fx-font-weight: bold;"));
	    y+=80;
	    getChildren().add( new MyLabel(0, y, "GAME LOGIC", 24, "white"));
	    y+=35;
	    getChildren().add( new MyLabel(0, y, "wplaat", 18, "white"));
	    y+=35;
	    getChildren().add( new MyLabel(0, y, "GAME GRAPHICS", 24, "white"));
	    y+=35;    	 
	    getChildren().add( new MyLabel(0, y, "wplaat", 18, "white"));
	    y+=35;
	    getChildren().add( new MyLabel(0, y, "MUSIC & SOUND EFFECTS", 24, "white"));
	    y+=35;
	    getChildren().add( new MyLabel(0, y, "wplaat", 18, "white"));
	    y+=35;
	    getChildren().add( new MyLabel(0, y, "TESTERS", 24, "white"));
	    y+=35;
	    getChildren().add( new MyLabel(0, y, "wplaat, lplaat, splaat, bplaat", 18, "white"));
	    	     
	    getChildren().add( new MyButton(0, MyFactory.getSettingDAO().getSettings().getHeight()-60, "Close", 18, Navigator.HOME));		
	}

	/**
	 * Draw.
	 */
	@Override
	public void draw() {
		
	}
}

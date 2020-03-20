package nl.plaatsoft.knightsquest.ui;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import nl.plaatsoft.knightsquest.tools.MyButton;
import nl.plaatsoft.knightsquest.tools.MyFactory;
import nl.plaatsoft.knightsquest.tools.MyHyperlink;
import nl.plaatsoft.knightsquest.tools.MyLabel;
import nl.plaatsoft.knightsquest.tools.MyPanel;

/**
 * The Class Donate.
 * 
 * @author wplaat
 */
public class Donate extends MyPanel {

	/**
	 * Instantiates a new donate.
	 */
	public Donate() {
		
		Image image1 = new Image("images/background4.jpg");
    	BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
    	BackgroundImage backgroundImage = new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    	Background background = new Background(backgroundImage);
    	
    	setBackground(background);
    	
    	int y=20;
    	getChildren().add(new MyLabel(0, y, "Donate", 50, "white", "-fx-font-weight: bold;"));
    	y+=80;
    	getChildren().add(new MyLabel(0, y, "If you enjoy this game, please sent me a small donation.", 20, "white"));
    	y+=25;
    	getChildren().add(new MyLabel(0, y, "You can make a donation online with your", 20, "white"));
    	y+=25;
    	getChildren().add(new MyLabel(0, y, "credit card, or PayPal account. Your credit card will", 20, "white"));
    	y+=25;    	 
    	getChildren().add(new MyLabel(0, y, "be processed by PayPal, a trusted name in secure ", 20, "white"));
    	y+=25;
    	getChildren().add(new MyLabel(0, y, "online transactions.", 20, "white"));
    	y+=60;  	
     	
    	// Force focus on button
    	getChildren().add(new MyButton(0,MyFactory.getSettingDAO().getSettings().getHeight()-60, "Close", 18, Navigator.HOME));		
    	
    	getChildren().add(new MyLabel(0, y, "Click on below link and follow the instructions", 20, "white"));    	
    	y+=25;
    	getChildren().add(new MyHyperlink(0, y, Constants.APP_DONATE_URL, 20));
    	y+=60;
    	getChildren().add(new MyLabel(0, y, "Many thanks for your support!", 20, "white"));    	
	}

	/**
	 * Draw.
	 */
	@Override
	public void draw() {

		
	}
}

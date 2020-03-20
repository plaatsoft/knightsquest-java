package nl.plaatsoft.knightsquest.ui;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import nl.plaatsoft.knightsquest.model.Score;
import nl.plaatsoft.knightsquest.tools.MyButton;
import nl.plaatsoft.knightsquest.tools.MyFactory;
import nl.plaatsoft.knightsquest.tools.MyImageView;
import nl.plaatsoft.knightsquest.tools.MyLabel;
import nl.plaatsoft.knightsquest.tools.MyPanel;

import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 * The Class HighScore1.
 * 
 * @author wplaat
 */
public class HighScore1 extends MyPanel {
		   
	/** The Constant formatter. */
	private final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
	
	/** The y. */
	private int y;
	
	/** The lines. */
	private int lines; 
		
	/**
	 * Draw.
	 */
	public void draw() {	
		
		Image image1 = new Image("images/background4.jpg");
    	BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
    	BackgroundImage backgroundImage = new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    	Background background = new Background(backgroundImage);
		setBackground(background);
		
		int offset = ((MyFactory.getSettingDAO().getSettings().getWidth()-640)/2);
		int x1 = 30 + offset;
		int x2 = 80 + offset;
		int x3 = 300 + offset;
		int x4 = 400 + offset;
		int x5 = 470 + offset;
		
		y=20;
		getChildren().add (new MyLabel(0, y, "Personal High Score", 50, "white", "-fx-font-weight: bold;"));		
		                
    	y+=80;    	
    	getChildren().add(new MyLabel(x1, y, "Nr", 25));
    	getChildren().add(new MyLabel(x2, y, "Date", 25));
		getChildren().add(new MyLabel(x3, y, "Score", 25));	
		getChildren().add(new MyLabel(x4, y, "Map", 25));
		getChildren().add(new MyLabel(x5, y, "Awards", 25));	
		y+=20;
				
		lines=1;
    	Iterator<Score> iter = MyFactory.getScoreDAO().getLocal().iterator();    	
		while (iter.hasNext()) {
			y+=18;
			Score score = (Score) iter.next();	
			getChildren().add(new MyLabel(x1, y, ""+lines, 18));					
			getChildren().add(new MyLabel(x2, y, formatter.format(score.getTimestamp()), 18));
			getChildren().add(new MyLabel(x3, y, ""+score.getScore(), 18));	
			getChildren().add(new MyLabel(x4, y, ""+score.getLevel(), 18));	
			
			if (lines<6) {
				for (int x=0; x<(6-lines); x++) {
					getChildren().add( new MyImageView(x5+(x*25)-20,y-20, "images/star.png", 0.3));
				}
			}
			
			if (++lines>15) {
				break;
			}
		}
		
		getChildren().add( new MyButton(0, MyFactory.getSettingDAO().getSettings().getHeight()-60, "Next", 18, Navigator.GLOBAL_HIGHSCORE));				
	}
}

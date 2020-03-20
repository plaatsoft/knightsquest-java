package nl.plaatsoft.knightsquest.tools;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.TextAlignment;

/**
 * The Class MyHyperlink
 * 
 * @author wplaat
 */
public class MyHyperlink extends Hyperlink {

	/**
	 * Instantiates a new my hyperlink.
	 *
	 * @param x the x
	 * @param y the y
	 * @param url the url
	 * @param fontSize the font size
	 */
	public MyHyperlink(int x, int y, String url, int fontSize) {
		
		setText(url);
		setLayoutY(y);
			
		if (x==0) {
			setMinWidth(MyFactory.getSettingDAO().getSettings().getWidth());
			setAlignment(Pos.CENTER);
			setTextAlignment(TextAlignment.CENTER);
			
		} else {
			
			setLayoutX(x);
		}
		
		setStyle("-fx-font-size:"+fontSize+"px; ");
		
    	setOnAction(new EventHandler<ActionEvent>() {
    	    public void handle(ActionEvent e) {
    	    	try {
					Desktop.getDesktop().browse(new URL(url).toURI());
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
    	    }
    	});
	}

}

package nl.plaatsoft.knightsquest.tools;

import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nl.plaatsoft.knightsquest.ui.Navigator;

public class MyImageView extends ImageView {

	public MyImageView(double x, double y, Image image, double scale) {

		setImage(image);
		setLayoutX(x);
		setLayoutY(y);
		setScaleX(scale);
		setScaleY(scale);
	}
		
	public MyImageView(double x, double y, Image image, double scale, boolean invert) {

		ColorAdjust blackout = new ColorAdjust();
		blackout.setBrightness(1.0);

		setImage(image);
		setLayoutX(x);
		setLayoutY(y);		
		setScaleX(scale);
		setScaleY(scale);

		setEffect(blackout);
		setCache(true);
		setCacheHint(CacheHint.SPEED);
	}

	public MyImageView(double x, double y, String resource, double scale) {

		Image image = new Image(resource);
		setImage(image);
		
		setLayoutX(x);
		setLayoutY(y);
		setScaleX(scale);
		setScaleY(scale);
	}
	
	public MyImageView(double x, double y, double width, double heigth, String resource) {

		Image image = new Image(resource);	
		setImage(image);		
		setLayoutX(x);
		setLayoutY(y);
		setFitWidth(width);
		setFitHeight(heigth);
		setPreserveRatio(true);
	}
	
	public MyImageView(double x, double y, String resource, double scale, int page) {

		Image image = new Image(resource);	
		setImage(image);		
		setLayoutX(x);
		setLayoutY(y);
		setScaleX(scale);
		setScaleY(scale);
		
		setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
	         	Navigator.go(page);
	      }
	   });
	}
}

package nl.plaatsoft.knightsquest;

import javax.swing.JOptionPane;

import nl.plaatsoft.knightsquest.common.AppConstants;
import nl.plaatsoft.knightsquest.ui.Navigator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import nl.plaatsoft.knightsquest.tools.MyFactory;
import nl.plaatsoft.knightsquest.tools.MyMusic;

public class AppMain extends Application {
	private static final Logger log = LogManager.getLogger( AppMain.class);
		
	@Override
	public void start(Stage primaryStage) {

		MyFactory.getSettingDAO().load();  
		
		setUserAgentStylesheet(STYLESHEET_MODENA);

		Navigator.setStage(primaryStage);
		Navigator.go(Navigator.INTRO1);

		primaryStage.setTitle(AppConstants.APP_NAME + " v" + AppConstants.APP_VERSION);
		primaryStage.setScene(Navigator.getScene());
		primaryStage.setWidth(MyFactory.getSettingDAO().getSettings().getWidth());
		primaryStage.setHeight(MyFactory.getSettingDAO().getSettings().getHeight()+20);       	
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("images/logo3.png"));
						
        if (MyFactory.getSettingDAO().getSettings().getX()!=0) {
               primaryStage.setX(MyFactory.getSettingDAO().getSettings().getX());
               primaryStage.setY(MyFactory.getSettingDAO().getSettings().getY());
        }
        
        primaryStage.setOnCloseRequest(we -> {
           MyFactory.getSettingDAO().getSettings().setX(primaryStage.getX());
           MyFactory.getSettingDAO().getSettings().setY(primaryStage.getY());
           MyFactory.getSettingDAO().save();
           Platform.exit();
           System.exit(0);
        });
        
        primaryStage.show();
        
		MyMusic.play();        
	}

	public static void main(String[] args) {
		
		log.info(AppConstants.APP_NAME + " v" + AppConstants.APP_VERSION+" start");
		
		String version = System.getProperty("java.version");
		String[] parts = version.split("_");
		if (((parts[0].equals("1.7.0") && Integer.parseInt(parts[1]) < 70))
				|| ((parts[0].equals("1.8.0") && Integer.parseInt(parts[1]) < 100))) {
			JOptionPane.showMessageDialog(null, "Java v" + version + " is to old. Please upgrade!");
			System.exit(1);
		}
		launch(args);
		
		log.info(AppConstants.APP_NAME + " v" + AppConstants.APP_VERSION+" end");
	}
}

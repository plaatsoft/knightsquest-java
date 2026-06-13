package nl.plaatsoft.knightsquest.ui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.plaatsoft.knightsquest.tools.MyFactory;

public class Navigator {

  private static Scene scene;
  private static Stage stage;

  public static final int NONE = 0;
  public static final int INTRO1 = 1;
  public static final int INTRO2 = 2;
  public static final int HOME = 3;
  public static final int GAME = 4;
  public static final int MODE_SELECTOR = 5;
  public static final int MAP_SELECTOR = 6;
  public static final int DONATE = 7;
  public static final int LOCAL_HIGH_SCORE = 8;
  public static final int GLOBAL_HIGH_SCORE = 9;
  public static final int CREDITS = 10;
  public static final int RELEASE_NOTES = 11;
  public static final int HELP = 12;
  public static final int SETTINGS = 13;
  public static final int COMMUNICATION = 14;
  public static final int EXIT = 15;

  public static void go(int page) {

    switch (page) {

      case INTRO1:
        Intro1 intro1 = new Intro1();
        intro1.draw();
        scene = new Scene(intro1, MyFactory.getSettingDAO().getSettings().getWidth(), MyFactory.getSettingDAO().getSettings().getHeight());
        break;

      case INTRO2:
        Intro2 intro2 = new Intro2();
        intro2.draw();
        scene.setRoot(intro2);
        break;

      case HOME:
        Home home = new Home();
        home.draw();
        scene.setRoot(home);
        break;

      case MODE_SELECTOR:
        ModeSelector modeSelector = new ModeSelector();
        modeSelector.draw();
        scene.setRoot(modeSelector);
        break;

      case MAP_SELECTOR:
        MapSelector mapSelector = new MapSelector();
        mapSelector.init();
        scene.setRoot(mapSelector);
        break;

      case GAME:
        Game game = new Game();
        game.init();
        game.start();
        scene.setRoot(game);
        //setSceneEvents(scene, game);
        break;

      case LOCAL_HIGH_SCORE:
        HighScore1 highScore1 = new HighScore1();
        highScore1.draw();
        scene.setRoot(highScore1);
        break;

      case GLOBAL_HIGH_SCORE:
        HighScore2 highScore2 = new HighScore2();
        highScore2.draw();
        scene.setRoot(highScore2);
        break;

      case DONATE:
        Donate donate = new Donate();
        donate.draw();
        scene.setRoot(donate);
        break;

      case CREDITS:
        Credits credits = new Credits();
        credits.draw();
        scene.setRoot(credits);
        break;

      case RELEASE_NOTES:
        ReleaseNotes releaseNotes = new ReleaseNotes();
        releaseNotes.draw();
        scene.setRoot(releaseNotes);
        break;

      case SETTINGS:
        Settings settings = new Settings();
        settings.draw();
        scene.setRoot(settings);
        break;

      case HELP:
        Help help = new Help();
        help.draw();
        scene.setRoot(help);
        break;

      case COMMUNICATION:
        Communication communication = new Communication();
        communication.draw();
        scene.setRoot(communication);
        break;

      case EXIT:
        Platform.exit();
        System.exit(0);
        break;
    }
  }

  //handles mouse scrolling
	/*private static void setSceneEvents(final Scene scene, final Pane page) {	    
	    scene.setOnScroll(
	            new EventHandler<ScrollEvent>() {
	              public void handle(ScrollEvent event) {
	                double zoomFactor = 1.10;
	                double deltaY = event.getDeltaY();
	                if (deltaY < 0){
	                  zoomFactor = 2.0 - zoomFactor;
	                }
	                	               	               
	               Iterator <Node> iter =  page.getChildren().iterator();			
	    		   while(iter.hasNext()) {		
	    			 	    
	    				Node node = (Node) iter.next();
	    				if(node instanceof Pane){
	    			         Pane pane = (Pane) node;
	    			         if (pane.getId().equals("map")) {
	    			        	 
	    			        	 double scale = pane.getScaleX() * zoomFactor;
	    			        	 log.info("scale="+scale);
            			         pane.setScaleX(scale);
	    			             pane.setScaleY(scale);
	    			              			        
	    			             MyFactory.getLandDAO().scaleMap(scale);
	    			        	 
	    			        	 event.consume();
	    			         }
	    			    }      
	    		   }
	           }
	      });
	  }*/


  public static Scene getScene() {
    return scene;
  }

  public static Stage getStage() {
    return stage;
  }

  public static void setStage(Stage stage) {
    Navigator.stage = stage;
  }
}

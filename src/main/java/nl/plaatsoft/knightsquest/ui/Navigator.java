/**
 *  @file
 *  @brief 
 *  @author wplaat
 *
 *  Copyright (C) 2008-2016 PlaatSoft
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, version 3.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package nl.plaatsoft.knightsquest.ui;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.plaatsoft.knightsquest.tools.MyFactory;

/**
 * The Class Navigator.
 */
public class Navigator {
		
	/** The Constant log. */
	private static final Logger log = LogManager.getLogger( Navigator.class);
	
	/** The intro 1. */
	private static Intro1 intro1;
	
	/** The intro 2. */
	private static Intro2 intro2;
	
	/** The home. */
	private static Home home;	
	
	/** The map selector. */
	private static MapSelector mapSelector;	
	
	/** The game. */
	private static Game game;	
	
	/** The donate. */
	private static Donate donate;
	
	/** The high score 1. */
	private static HighScore1 highScore1;
	
	/** The high score 2. */
	private static HighScore2 highScore2;
	
	/** The credits. */
	private static Credits credits;	
	
	/** The release notes. */
	private static ReleaseNotes releaseNotes;
	
	/** The help. */
	private static Help help;
	
	/** The settings. */
	private static Settings settings;
	
	/** The mode selector. */
	private static ModeSelector modeSelector;
	
	/** The communication. */
	private static Communication communication;	
	
	/** The scene. */
	private static Scene scene;	
	
	/** The stage. */
	private static Stage stage;
			
	/** The Constant NONE. */
	public static final int NONE = 0;
	
	/** The Constant INTRO1. */
	public static final int INTRO1 = 1;
	
	/** The Constant INTRO2. */
	public static final int INTRO2 = 2;
	
	/** The Constant HOME. */
	public static final int HOME = 3;
	
	/** The Constant GAME. */
	public static final int GAME = 4;
	
	/** The Constant MODE_SELECTOR. */
	public static final int MODE_SELECTOR = 5;
	
	/** The Constant MAP_SELECTOR. */
	public static final int MAP_SELECTOR = 6;
	
	/** The Constant DONATE. */
	public static final int DONATE = 7;
	
	/** The Constant LOCAL_HIGHSCORE. */
	public static final int LOCAL_HIGHSCORE = 8;
	
	/** The Constant GLOBAL_HIGHSCORE. */
	public static final int GLOBAL_HIGHSCORE = 9;
	
	/** The Constant CREDITS. */
	public static final int CREDITS = 10;
	
	/** The Constant RELEASE_NOTES. */
	public static final int RELEASE_NOTES = 11;
	
	/** The Constant HELP. */
	public static final int HELP = 12;
	
	/** The Constant SETTINGS. */
	public static final int SETTINGS = 13;
	
	/** The Constant COMMUNICATION. */
	public static final int COMMUNICATION = 14;
	
	/** The Constant EXIT. */
	public static final int EXIT = 15;
			
	/**
	 * Go.
	 *
	 * @param page the page
	 */
	public static void go(int page) {
				
	  switch (page ) {

		case INTRO1:
			intro1 = new Intro1();
			intro1.draw();
			scene = new Scene(intro1, MyFactory.getSettingDAO().getSettings().getWidth(), MyFactory.getSettingDAO().getSettings().getHeight());	
			break;
						
		case INTRO2:
			intro2 = new Intro2();				
			intro2.draw();
			scene.setRoot(intro2);
			break;
			
		case HOME:
			home = new Home();
			home.draw();
			scene.setRoot(home);	
			break;		
			
		case MODE_SELECTOR:
			modeSelector = new ModeSelector();				
			modeSelector.draw();						
			scene.setRoot(modeSelector);	
			break;
			
		case MAP_SELECTOR:
			mapSelector = new MapSelector();				
			mapSelector.init();						
			scene.setRoot(mapSelector);	
			break;	
									
		case GAME:
			game = new Game();
			game.init();		
			game.start();						
			scene.setRoot(game);	
			//setSceneEvents(scene, game);
			break;	
						
		case LOCAL_HIGHSCORE:			
			highScore1 = new HighScore1();
			highScore1.draw();			
			scene.setRoot(highScore1);
			break;
			
		case GLOBAL_HIGHSCORE:
			highScore2 = new HighScore2();			
			highScore2.draw();
			scene.setRoot(highScore2);
			break;	
			
		case DONATE:
			donate = new Donate();
			donate.draw();
			scene.setRoot(donate);
			break;
							
		case CREDITS:
			credits = new Credits();
			credits.draw();			
			scene.setRoot(credits);
			break;	
			
		case RELEASE_NOTES:
			releaseNotes = new ReleaseNotes();
			releaseNotes.draw();
			scene.setRoot(releaseNotes);				
			break;		
			
		case SETTINGS:
			settings = new Settings();
			settings.draw();
			scene.setRoot(settings);			
			break;	
			
		case HELP:
			help = new Help();
			help.draw();
			scene.setRoot(help);			
			break;
			
		case COMMUNICATION:
			communication = new Communication();
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
	
	
	/**
	 * Gets the scene.
	 *
	 * @return the scene
	 */
	public static Scene getScene() {
		return scene;
	}

	/**
	 * Gets the stage.
	 *
	 * @return the stage
	 */
	public static Stage getStage() {
		return stage;
	}

	/**
	 * Sets the stage.
	 *
	 * @param stage the new stage
	 */
	public static void setStage(Stage stage) {
		Navigator.stage = stage;
	}
	
	
}

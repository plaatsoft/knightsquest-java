package nl.plaatsoft.knightsquest.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import nl.plaatsoft.knightsquest.tools.MyData;
import nl.plaatsoft.knightsquest.tools.MyFactory;

/**
 * The Class PlayerDAO.
 * 
 * @author wplaat
 */
public class PlayerDAO {

	/** The players. */
	private List <Player> players = new ArrayList<Player>();
	
	/**
	 * Gets the texture.
	 *
	 * @param gc the gc
	 * @param player the player
	 * @return the texture
	 */
	public void getTexture(GraphicsContext gc, int player) { 
		
		switch(player) {
		
			case 1: // Player 1
					gc.setFill(Color.YELLOW);
					break;
				
			case 2: // Player 2
					gc.setFill(Color.RED);
					break;
			
			case 3: // Player 3
					gc.setFill(Color.CYAN);
					break;
		
			case 4: // Player 4
					gc.setFill(Color.MAGENTA);
					break;
					
			case 5: // Player 5
					gc.setFill(Color.BROWN);
					break;
					
			case 6: // Player 6
					gc.setFill(Color.LIGHTBLUE);
					break;
		}		
	}	
	
	/**
	 * Gets the color.
	 *
	 * @param player the player
	 * @return the color
	 */
	public String getColor(int player) { 
		
		switch(player) {
		
			case 1: // Player 1
					return "yellow";

			case 2: // Player 2
					return "red";
			
			case 3: // Player 3
					return "cyan";
		
			case 4: // Player 4
					return "magenta";
					
			case 5: // Player 5
					return "brown";
					
			case 6: // Player 6
					return "lightblue";
		}		
		return "";
	}	
	
	/**
	 * Creates the player.
	 *
	 * @param gc the gc
	 * @param id the id
	 * @param pane the pane
	 * @param type the type
	 * @return the player
	 */
	public Player createPlayer(GraphicsContext gc, int id, Pane pane, PlayerEnum type) {
			
		Player player = new Player(id, type);
		MyFactory.getPlayerDAO().getPlayers().add(player);
		
		for (int i=0; i<MyData.getTowers(); i++) {
						
			MyFactory.getRegionDAO().createStartRegion(i+1, player, pane);		
		}				
		return player;
	}
	
	/**
	 * Gets the human player.
	 *
	 * @return the human player
	 */
	public Player getHumanPlayer() {
		
		Iterator<Player> iter = players.iterator();  
		while (iter.hasNext()) {
			Player player = (Player) iter.next();
			if (player.getType()==PlayerEnum.HUMAN_LOCAL) {
				return player;
			}
		}
		return null;
	}
		
	/**
	 * Checks for player no moves.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean hasPlayerNoMoves(Player player) {
	
		/* Check if human player has moves lefts in this turn */
		Iterator<Region> iter2 = player.getRegion().iterator();  
		while (iter2.hasNext()) {
			Region region = (Region) iter2.next();
				
			Iterator<Land> iter3 = region.getLands().iterator();  
			while (iter3.hasNext()) {
				Land land = (Land) iter3.next();
					
				if ((land.getSoldier()!=null) && land.getSoldier().isEnabled()) {
					return false;
				}
			}
		}
		
		return true;
	}
		
	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public List <Player> getPlayers() {
		return players;
	}

	/**
	 * Sets the players.
	 *
	 * @param players the new players
	 */
	public void setPlayers(List <Player> players) {
		this.players = players;
	}
}

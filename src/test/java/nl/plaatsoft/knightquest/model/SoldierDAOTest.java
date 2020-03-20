package nl.plaatsoft.knightquest.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.plaatsoft.knightsquest.model.Land;
import nl.plaatsoft.knightsquest.model.LandDAO;
import nl.plaatsoft.knightsquest.model.LandEnum;
import nl.plaatsoft.knightsquest.model.Player;
import nl.plaatsoft.knightsquest.model.PlayerEnum;
import nl.plaatsoft.knightsquest.model.Region;
import nl.plaatsoft.knightsquest.model.SoldierDAO;
import nl.plaatsoft.knightsquest.tools.MyData;

/**
 * The Class SoldierDAOTest.
 * 
 * @author wplaat
 */
public class SoldierDAOTest {
		
	/**
	 * Test 1.
	 */
	@Test
	public void test1() {
		
		int size = 10;				
							
		MyData.setLevel(1);
		MyData.setMap(1);
		
		LandDAO landDAO = new LandDAO();				
		Land[][] lands = landDAO.getLands();
		
		Player player = new Player(size,  PlayerEnum.BOT);		
		Region region = new Region(1, player);
		
		for (int x=0; x<5;x++) {
			for (int y=0; y<5;y++) {
				lands[x][y] = new Land(null, x, y, size, LandEnum.GRASS);
				region.getLands().add(lands[x][y]);
			}			
		}		
						
		SoldierDAO soldierDAO = new SoldierDAO();
		soldierDAO.createBotSoldier(region);
		
		assertEquals("Region contain 25 land tills", region.getLands().size(), 25);
	}
}

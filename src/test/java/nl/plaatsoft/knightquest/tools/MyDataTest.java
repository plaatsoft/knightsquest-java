package nl.plaatsoft.knightquest.tools;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.plaatsoft.knightsquest.tools.MyData;

/**
 * The Class MyDataTest.
 * 
 * @author wplaat
 */
public class MyDataTest {

	/**
	 * Next map test 1.
	 */
	@Test
	public void nextMapTest1() {
		
		assertEquals("Next map not right!", 2, MyData.getNextMap(1));
	}
		
	/**
	 * Next map test 2.
	 */
	@Test
	public void nextMapTest2() {
		
		assertEquals("Next map not right!", 11, MyData.getNextMap(6));
	}
	
	/**
	 * Next map test 3.
	 */
	@Test
	public void nextMapTest3() {
		
		assertEquals("Last map not right!", 0, MyData.getNextMap(96));
	}
		
}

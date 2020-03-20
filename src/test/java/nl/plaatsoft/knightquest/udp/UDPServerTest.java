package nl.plaatsoft.knightquest.udp;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nl.plaatsoft.knightsquest.network.UDPServer;
import nl.plaatsoft.knightsquest.ui.Constants;

/**
 * The Class UDPServerTest.
 */
public class UDPServerTest {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger( UDPServerTest.class);
	
	/** The id. */
	private String id;
	
	/** The server. */
	private UDPServer server;
	
	/**
	 * Before.
	 */
	@Before
	public void before() {
		
		try {
			server = new UDPServer();
			server.init(Constants.APP_UDP_PORT);
		} catch (Exception e) {
			log.error(e.getMessage());			
		}
	}
	
	/**
	 * Ping test.
	 */
	@Test
	public void pingTest() {
				
		server.ping();
		String json = server.receive();
		assertEquals("No message", true, json.length()>0);
	}
	
	/**
	 * Join test.
	 */
	@Test
	public void joinTest() {
				
		server.join(1,1);
		String json = server.receive();
		assertEquals("No message", true, json.length()>0);
	}
	
	/**
	 * Move test.
	 */
	@Test
	public void moveTest() {
				
		server.move(1, 2, 1, 2);
		String json = server.receive();
		assertEquals("No message", true, json.length()>0);
	}
	
	/**
	 * Pong test.
	 */
	@Test
	public void pongTest() {
				
		server.pong();
		String json = server.receive();
		assertEquals("No message", true, json.length()>0);
	}
	
	/**
	 * Turn test.
	 */
	@Test
	public void turnTest() {
				
		server.turn();
		String json = server.receive();
		assertEquals("No message", true, json.length()>0);
	}
	
	/**
	 * Filter test.
	 */
	@Test
	public void filterTest() {
				
		server.turn();
		String json = server.receive();
		json = server.filter(json, id);
		assertEquals("Filter is not working", null, json);
	}	
	
	/**
	 * After.
	 */
	@After
	public void after() {
		
		server.close();
	}
}

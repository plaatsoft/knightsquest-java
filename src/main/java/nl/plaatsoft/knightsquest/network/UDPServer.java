package nl.plaatsoft.knightsquest.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import nl.plaatsoft.knightsquest.ui.Constants;

/**
 * The Class UDPServer
 * 
 * @author wplaat
 */
public class UDPServer {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(UDPServer.class);
	
	/** The socket. */
	private DatagramSocket socket = null;
	
	/** The group. */
	private InetAddress group = null;
	
	/** The id. */
	private String id = UUID.randomUUID().toString();
	
	/**
	 * Gets the broadcast address.
	 *
	 * @return the broadcast address
	 * @throws SocketException the socket exception
	 */
	private String getBroadcastAddress() throws SocketException {
		
		@SuppressWarnings("rawtypes")
		Enumeration e = NetworkInterface.getNetworkInterfaces();		
		while(e.hasMoreElements()) {
			NetworkInterface n = (NetworkInterface) e.nextElement();
			Iterator<InterfaceAddress> iter1 = n.getInterfaceAddresses().iterator();
			while (iter1.hasNext()) {
				InterfaceAddress address = (InterfaceAddress) iter1.next();
				String ip = address.getAddress().toString();
					
				int n1 = ip.indexOf(".", 4);
				if (n1>0) {			
					if (!ip.equals("/127.0.0.1") && (!ip.startsWith("/169.254"))) {
						return (address.getBroadcast().toString().substring(1));
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Inits the.
	 *
	 * @param port the port
	 */
	public void init(int port) {
		
		try {
			if (socket==null) {
				socket = new DatagramSocket(port);		
				socket.setSoTimeout(2000); 
				group = InetAddress.getByName(getBroadcastAddress());
			}			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * Show.
	 *
	 * @param send the send
	 * @param packet the packet
	 */
	private void show(boolean send, DatagramPacket packet) {
		String data = new String(packet.getData());

		String text = "";
		if (send) {
			text +=" TX:";
		} else {
			text +=" RX:";
		}
		if (packet.getAddress()!=null) {
			text += " ["+packet.getAddress().toString().substring(1)+":"+packet.getPort()+"] "+data;
			log.info(text);
		}
	}
		
	/**
	 * Sent.
	 *
	 * @param data the data
	 */
	private void sent(byte[] data) {
		
		try {
			DatagramPacket packet = new DatagramPacket(data, data.length, group, 20000);
			show(true, packet);
			socket.send(packet);
						
		} catch (IOException e) {
			log.error(e.getMessage());
		}		
	}
	
	/**
	 * Receive.
	 *
	 * @return the string
	 */
	public String receive() {
				
		String json = null;
		try {		
			byte[] receiveData = new byte[1024];			
			DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
			show(false, packet);
			
			socket.receive(packet);				
			json = new String(packet.getData(), StandardCharsets.UTF_8);				
				
		} catch (IOException e) {
			log.error(e.getMessage());			
		}			
		return json;
	}
	
	
	/**
	 * Filter.
	 *
	 * @param json the json
	 * @param id the id
	 * @return the string
	 */
	public String filter(String json, String id) {
		
		try {
			JSONObject obj = new JSONObject(json);
			String id2 = obj.getString("id");
			
			if (id.equals(id2)) {			
				return null;
			}
			return json;
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}			
	}
	
	/**
	 * Close.
	 */
	public void close() {
		socket.close();		
	}
	
	/**
	 * Creates the header.
	 *
	 * @return the JSON object
	 * @throws JSONException the JSON exception
	 */
	private JSONObject createHeader() throws JSONException {
		Date now = new Date();
		JSONObject msg = new JSONObject();
		
		msg.put("product", Constants.APP_NAME);
		msg.put("version", Constants.APP_VERSION);
		msg.put("timestamp", now.getTime());
		msg.put("id", id); 
		msg.put("name", CloudUser.getNickname()); 
		
		return msg;		
	}
	
	/**
	 * Ping.
	 */
	public void ping() {
		
		JSONObject msg = null; 
		
		try {
			msg = createHeader();
			msg.put("action", "ping"); 
		} catch (JSONException e) {
			log.error(e.getMessage());
		} 
		sent(msg.toString().getBytes());		
	}
	
	 /**
 	 * Pong.
 	 */
 	public void pong() {
		 JSONObject msg = null; 
		try {
			msg = createHeader();
			msg.put("action", "pong"); 
		} catch (JSONException e) {
			log.error(e.getMessage());
		} 
		sent( msg.toString().getBytes());	
	}
		
	/**
	 * Join.
	 *
	 * @param map the map
	 * @param level the level
	 */
	public void join(int map, int level) {
		JSONObject msg = null; 
		try {
			msg = createHeader();
			msg.put("action", "join");
			msg.put("map", map);
			msg.put("level", level); 
		} catch (JSONException e) {
			log.error(e.getMessage());
		} 
		sent(msg.toString().getBytes());
	}
	
	/**
	 * Abort.
	 */
	public void abort() {
		JSONObject msg = null; 
		try {
			msg = createHeader();
			msg.put("action", "abort"); 
		} catch (JSONException e) {
			log.error(e.getMessage());
		} 
		sent(msg.toString().getBytes());
	}
		
	/**
	 * Move.
	 *
	 * @param x1 the x 1
	 * @param y1 the y 1
	 * @param x2 the x 2
	 * @param y2 the y 2
	 */
	public void move(int x1, int y1, int x2, int y2) {
		JSONObject msg = null; 
		try {
			msg = createHeader();
			msg.put("action", "move");
			msg.put("x1", x1); 
			msg.put("y1", y1); 
			msg.put("x2", x2); 
			msg.put("y2", y2); 
		} catch (JSONException e) {
			log.error(e.getMessage());
		} 
		sent(msg.toString().getBytes());
	}
	
	/**
	 * Creates the.
	 *
	 * @param x2 the x 2
	 * @param y2 the y 2
	 */
	public void create(int x2, int y2) {
		JSONObject msg = null; 
		try {
			msg = createHeader();
			msg.put("action", "create");
			msg.put("x2", x2); 
			msg.put("y2", y2); 
		} catch (JSONException e) {
			log.error(e.getMessage());
		} 
		sent(msg.toString().getBytes());
	}
	
	/**
	 * Turn.
	 */
	public void turn() {
		JSONObject msg = null; 
		try {
			msg = createHeader();
			msg.put("action", "turn"); 
		} catch (JSONException e) {
			log.error(e.getMessage());
		} 
		sent(msg.toString().getBytes());
	}
	
}

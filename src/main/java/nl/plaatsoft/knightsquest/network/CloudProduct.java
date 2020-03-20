package nl.plaatsoft.knightsquest.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;

import nl.plaatsoft.knightsquest.ui.Constants;

/**
 * The Class CloudProduct.
 * 
 * @author wplaat
 */
public class CloudProduct {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger( CloudProduct.class);
	
	/** The pid. */
	private static int pid=0;
	
	/**
	 * Instantiates a new cloud product.
	 */
	private CloudProduct() {
	    throw new IllegalStateException("CloudProduct class");
    }
		
	/**
	 * Fetch.
	 */
	public static void fetch() {

		String parameters = "action=getProduct"+
				"&product=" + Constants.APP_WS_NAME+
				"&version=" + Constants.APP_VERSION+
				"&os="+System.getProperty("os.name").replace(" ","");
						
		log.info("TX: {}?{}",Constants.APP_WS_URL, parameters);
		String json = CloudUtils.executePost(Constants.APP_WS_URL, parameters);
		log.info("RX: {}", json);
				
		try {
			JSONObject obj = new JSONObject(json);
			pid = obj.getInt("pid");		
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * Gets the pid.
	 *
	 * @return the pid
	 */
	public static int getPid() {
		if (pid==0) {
			fetch();
		}
		return pid;
	}
}

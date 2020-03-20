package nl.plaatsoft.knightsquest.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import nl.plaatsoft.knightsquest.ui.Constants;

/**
 * The Class CloudNewVersion
 * 
 * @author wplaat
 */
public class CloudNewVersion {
	
	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(CloudNewVersion.class);
		
	/**
	 * Instantiates a new.
	 */
	private CloudNewVersion() {
	    throw new IllegalStateException("CloudNewVersion class");
    }
		
	/**
	 * Gets the.
	 *
	 * @return the string
	 */
	public static String get() {

		String returnValue="";		
		String parameters = "action=getVersion&product="+Constants.APP_WS_NAME; 
						
		log.info("TX: {}?{}", Constants.APP_WS_URL, parameters);		
		String json = CloudUtils.executePost(Constants.APP_WS_URL, parameters);
		log.info("RX: {}", json);
		
		try {
			JSONObject obj = new JSONObject(json);
			String newVersion = obj.getString("version");
			if (newVersion.compareTo(Constants.APP_VERSION)>0) {
				returnValue = Constants.APP_NAME+" v"+newVersion+" available.";
			}
			
		} catch (Exception e) {
			returnValue = "No Internet connection!";
			log.error(e.getMessage());			
		}

		return returnValue;			
	}
}

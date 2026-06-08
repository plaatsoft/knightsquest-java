package nl.plaatsoft.knightsquest.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import nl.plaatsoft.knightsquest.common.AppConstants;

public class CloudVersion {
	
	private static final Logger log = LogManager.getLogger(CloudVersion.class);

	private CloudVersion() {
	    throw new IllegalStateException("CloudNewVersion class");
    }
		
	public static String get() {

		String returnValue="";		
		String parameters = "action=getVersion&product="+ AppConstants.APP_WS_NAME;
						
		log.info("TX: {}?{}", AppConstants.APP_WS_URL, parameters);
		String json = CloudUtils.executePost(AppConstants.APP_WS_URL, parameters);
		log.info("RX: {}", json);
		
		try {
			JSONObject obj = new JSONObject(json);
			String newVersion = obj.getString("version");
			if (newVersion.compareTo(AppConstants.APP_VERSION)>0) {
				returnValue = AppConstants.APP_NAME+" v"+newVersion+" available.";
			}
			
		} catch (Exception e) {
			returnValue = "No Internet connection!";
			log.error(e.getMessage());			
		}

		return returnValue;			
	}
}

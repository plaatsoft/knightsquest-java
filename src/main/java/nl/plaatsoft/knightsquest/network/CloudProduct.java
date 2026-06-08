package nl.plaatsoft.knightsquest.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;

import nl.plaatsoft.knightsquest.common.AppConstants;

public class CloudProduct {

	private static final Logger log = LogManager.getLogger( CloudProduct.class);
	
	private static int pid=0;
	
	private CloudProduct() {
	    throw new IllegalStateException("CloudProduct class");
    }
		
	public static void fetch() {

		String parameters = "action=getProduct"+
				"&product=" + AppConstants.APP_WS_NAME+
				"&version=" + AppConstants.APP_VERSION+
				"&os="+System.getProperty("os.name").replace(" ","");
						
		log.info("TX: {}?{}", AppConstants.APP_WS_URL, parameters);
		String json = CloudUtils.executePost(AppConstants.APP_WS_URL, parameters);
		log.info("RX: {}", json);
				
		try {
			JSONObject obj = new JSONObject(json);
			pid = obj.getInt("pid");		
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public static int getPid() {
		if (pid==0) {
			fetch();
		}
		return pid;
	}
}

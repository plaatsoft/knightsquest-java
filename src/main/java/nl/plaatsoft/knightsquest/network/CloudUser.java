package nl.plaatsoft.knightsquest.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import nl.plaatsoft.knightsquest.common.AppConstants;

public class CloudUser {

	private static final Logger log = LogManager.getLogger(CloudUser.class);
	
	private static int uid=0;
	
	private static String nickName = System.getProperty("user.name");
	
	private CloudUser() {
	    throw new IllegalStateException("CloudUser class");
    }
	
	public static void get() {

		String parameters = "action=getUser"+
				"&username=" + System.getProperty("user.name") +
				"&nickname=" + nickName;

		log.info("TX: {}?{}", AppConstants.APP_WS_URL, parameters);
		String json = CloudUtils.executePost(AppConstants.APP_WS_URL, parameters);
		log.info("RX: {}", json);
		
		try {
			JSONObject obj = new JSONObject(json);
			uid = obj.getInt("uid");
			nickName = obj.getString("nickname");		
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * Sets the.
	 *
	 * @param newNickName the new nick name
	 */
	public static void set(String newNickName) {

		String parameters = "action=setUser"+
				"&username=" + System.getProperty("user.name") +
				"&nickname=" + newNickName;
						
		log.info("TX: {}?{}", AppConstants.APP_WS_URL, parameters);
		String json = CloudUtils.executePost(AppConstants.APP_WS_URL, parameters);
		log.info("RX: {}", json);
		
		try {
			JSONObject obj = new JSONObject(json);
			uid = obj.getInt("uid");
			nickName = obj.getString("nickname");		
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static int getUid() {
		if (uid==0) {
			get();
		}
		return uid;
	}
	
	public static String getNickname() {
		return nickName;
	}
}


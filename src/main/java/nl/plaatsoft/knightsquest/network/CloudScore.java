package nl.plaatsoft.knightsquest.network;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import nl.plaatsoft.knightsquest.model.Score;
import nl.plaatsoft.knightsquest.tools.MyFactory;
import nl.plaatsoft.knightsquest.common.AppConstants;

public class CloudScore {

	private static final Logger log = LogManager.getLogger( CloudScore.class);
	
	private CloudScore() {
	    throw new IllegalStateException("CloudScore class");
    }
	
	public static void set(String product, String version, Score score) {
					
		String parameters;
		parameters  = "action=setScore&";
		parameters += "pid=" + CloudProduct.getPid()+ "&";
		parameters += "uid=" + CloudUser.getUid()  + "&";
		/* Remove milli seconds */
		parameters += "dt=" + (score.getTimestamp().getTime()/1000) + "&";
		parameters += "score=" + score.getScore() + "&";
		parameters += "level=" + score.getLevel();
				
		log.info("TX: {}?{}", AppConstants.APP_WS_URL, parameters);
		String json = CloudUtils.executePost(AppConstants.APP_WS_URL, parameters);
		log.info("RX: {}", json);
	}
	
	public static void getLocal() {
		
		String parameters;
		parameters  = "action=getLocalScore&";
		parameters += "pid=" + CloudProduct.getPid() + "&";
		parameters += "uid=" + CloudUser.getUid();
		
		log.info("TX: {}?{}", AppConstants.APP_WS_URL, parameters);
		String json = CloudUtils.executePost(AppConstants.APP_WS_URL, parameters);
		log.info("RX: {}", json);
		
		try {
			JSONArray jsonarray = new JSONArray(json);
			for (int i = 0; i < jsonarray.length(); i++) {
			    JSONObject jsonobject = jsonarray.getJSONObject(i);
			    long dt = (jsonobject.getLong("dt")*1000);
			    int points = jsonobject.getInt("score");
			    int level = jsonobject.getInt("level");
			    String nickname = "";
			    String country = "";
			    
			    Date date = new Date();
			    date.setTime(dt);
			    			    			    
				Score score = new Score(date, points, level, nickname, country);
				MyFactory.getScoreDAO().addLocal(score);  	   
			}			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public static void getGlobal() {
		
		String parameters;
		parameters  = "action=getGlobalScore&";
		parameters += "pid=" + CloudProduct.getPid();
		
		log.info("TX: {}?{}", AppConstants.APP_WS_URL, parameters);
		String json = CloudUtils.executePost(AppConstants.APP_WS_URL, parameters);
		log.info("RX: {}", json);
						
		try {
			JSONArray jsonarray = new JSONArray(json);
			for (int i = 0; i < jsonarray.length(); i++) {
			    JSONObject jsonobject = jsonarray.getJSONObject(i);
			    long dt = jsonobject.getLong("dt")*1000;
			    int points = jsonobject.getInt("score");
			    int level = jsonobject.getInt("level");
			    
			    JSONObject object2 = jsonobject.getJSONObject("user");		    
			    String nickname = object2.getString("nickname");
			    String country = object2.getString("country");
			    			    
			    Date date = new Date();
			    date.setTime(dt);
			    
				Score score = new Score(date, points, level, nickname, country);
				MyFactory.getScoreDAO().addGlobal(score);   	   
			}			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
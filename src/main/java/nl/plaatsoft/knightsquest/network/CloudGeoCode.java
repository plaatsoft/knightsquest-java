package nl.plaatsoft.knightsquest.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;

/**
 * The Class CloudGeoCode.
 */
public class CloudGeoCode {

	/** The Constant log. */
	private static final  Logger log = LogManager.getLogger( CloudGeoCode.class);
	
	/** The country. */
	private static String country;
	
	/** The city. */
	private static String city;
	
	/** The ip. */
	private static String ip;
			
	/**
	 * Instantiates a new cloud geo code.
	 */
	private CloudGeoCode() {
	    throw new IllegalStateException("CloudGeoCode class");
    }
	
	/**
	 * Fetch.
	 */
	private static void fetch() {
		
		String url = "https://freegeoip.live/json";
			
		log.info("TX: {}", url);		
		String json = CloudUtils.executeGet(url);
		log.info("RX: {}", json);
		
		try {
			JSONObject obj = new JSONObject(json);
			country = obj.getString("country_code");
			city = obj.getString("city");
			ip = obj.getString("ip");
				
		} catch (Exception e) {
			log.error(e.getMessage());
		}		
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public static String getCountry() {
		
		if (country==null) {
			fetch();
		}
		return country.toLowerCase();
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public static String getCity() {
		if (city==null) {
			fetch();
		}
		return city.toLowerCase();
	}
	
	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public static String getIp() {
		if (ip==null) {
			fetch();
		}
		return ip;
	}
}

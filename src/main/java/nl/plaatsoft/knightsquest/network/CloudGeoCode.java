/**
 *  @file
 *  @brief 
 *  @author wplaat
 *
 *  Copyright (C) 2008-2016 PlaatSoft
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, version 3.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package nl.plaatsoft.knightsquest.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;

/**
 * The Class CloudGeoCode.
 */
public class CloudGeoCode {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(CloudGeoCode.class);
	
	/** The country. */
	private static String country;
	
	/** The city. */
	private static String city;
			
	/**
	 * Instantiates a new cloud geo code.
	 */
	private CloudGeoCode() {
	    throw new IllegalStateException("CloudGeoCode class");
    }
	
	/**
	 * Fetch.
	 */
	static private void fetch() {
		
		String url = "https://freegeoip.live/json";
		
		log.info("TX: {}", url);		
		String json = CloudUtils.executeGet(url);
		log.info("RX: {}", json);
		
		try {
			JSONObject obj = new JSONObject(json);
			country = obj.getString("country_code");
			city = obj.getString("city");
				
		} catch (Exception e) {
			log.error(e.getMessage());
		}		
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	static public String getCountry() {
		
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
	static public String getCity() {
		if (city==null) {
			fetch();
		}
		return city.toLowerCase();
	}
}

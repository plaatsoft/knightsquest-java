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

import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class CloudCheck.
 * 
 * @author wplaat
 */
public class CloudCheck {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(CloudCheck.class);
		
	/**
	 * Instantiates a new cloud geo code.
	 */
	private CloudCheck() {
	    throw new IllegalStateException("CloudCheck class");
    }
	
	/**
	 * Checks if is reachable by TCP.
	 *
	 * @param host the host
	 * @return true, if is reachable by TCP
	 */
	public static boolean isReachableByTCP(String host)
    {
        boolean status = false;
        Socket sock = new Socket();
        InetSocketAddress address = new InetSocketAddress(host, 80);
        try {
           sock.connect(address, 2000);
           if(sock.isConnected()) {
               status=true;
           }
        }
        catch(Exception e) {
        	log.error(e.getMessage());
        }
        finally {
            try {
                sock.close();
            }
            catch(Exception e) {
            	log.error(e.getMessage());
            }
        }
        return status;
    }

	/**
	 * Checks if is reachable by ping.
	 *
	 * @param host the host
	 * @return true, if is reachable by ping
	 */
	public static boolean isReachableByPing(String host) {
		try {
			String cmd = "";
			if (System.getProperty("os.name").startsWith("Windows")) {
				// For Windows
				cmd = "ping -n 1 " + host;
			} else {
				// For Linux and OSX
				cmd = "ping -c 1 " + host;
			}

			Process myProcess = Runtime.getRuntime().exec(cmd);
			myProcess.waitFor();

			return (myProcess.exitValue()==0);
			
		} catch (Exception e) {

			log.error(e.getMessage());
			return false;
		}
	}
}

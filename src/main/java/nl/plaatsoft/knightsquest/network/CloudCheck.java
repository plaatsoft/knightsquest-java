package nl.plaatsoft.knightsquest.network;

import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CloudCheck {

  private static final Logger log = LogManager.getLogger(CloudCheck.class);

  private CloudCheck() {
    throw new IllegalStateException("CloudCheck class");
  }

  public static boolean isReachableByTCP(String host) {
    boolean status = false;
    Socket sock = new Socket();
    InetSocketAddress address = new InetSocketAddress(host, 80);
    try {
      sock.connect(address, 2000);
      if (sock.isConnected()) {
        status = true;
      }
    } catch (Exception e) {
      log.error(e.getMessage());
    } finally {
      try {
        sock.close();
      } catch (Exception e) {
        log.error(e.getMessage());
      }
    }
    return status;
  }

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

      return (myProcess.exitValue() == 0);

    } catch (Exception e) {

      log.error(e.getMessage());
      return false;
    }
  }
}

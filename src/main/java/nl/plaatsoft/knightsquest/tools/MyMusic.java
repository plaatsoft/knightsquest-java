package nl.plaatsoft.knightsquest.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MyMusic {

  private static final Logger log = LogManager.getLogger(MyMusic.class);

  private static MediaPlayer mp;

  public static void init() {
    log.info("init");
    String path = MyMusic.class.getResource("/sounds/intro.mp3").toExternalForm();
    Media media = new Media(path);
    mp = new MediaPlayer(media);
    mp.setCycleCount(MediaPlayer.INDEFINITE);
  }

  public static void play() {
    if (mp == null) {
      init();
    }
    if (MyFactory.getSettingDAO().getSettings().isMusicOn()) {
      mp.play();
    }
  }

  public static void stop() {
    mp.stop();
  }
}

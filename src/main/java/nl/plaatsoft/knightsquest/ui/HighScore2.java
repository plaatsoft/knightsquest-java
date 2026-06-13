package nl.plaatsoft.knightsquest.ui;

import javafx.concurrent.Task;
import javafx.concurrent.Worker.State;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import nl.plaatsoft.knightsquest.network.CloudScore;
import nl.plaatsoft.knightsquest.model.Score;
import nl.plaatsoft.knightsquest.tools.MyButton;
import nl.plaatsoft.knightsquest.tools.MyFactory;
import nl.plaatsoft.knightsquest.tools.MyImageView;
import nl.plaatsoft.knightsquest.tools.MyLabel;
import nl.plaatsoft.knightsquest.tools.MyPanel;

import java.text.SimpleDateFormat;

public class HighScore2 extends MyPanel {

  private final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
  private int y;

  private void showTable() {

    int offset = ((MyFactory.getSettingDAO().getSettings().getWidth() - 640) / 2);
    int x1 = 30 + offset;
    int x2 = 80 + offset;
    int x3 = 300 + offset;
    int x4 = 390 + offset;
    int x5 = 447 + offset;
    int x6 = 480 + offset;

    y = 120;

    int lines = 1;
    for (Score value : MyFactory.getScoreDAO().getGlobal()) {
      y += 18;

      Score score = value;
      getChildren().add(new MyLabel(x1, y, "" + lines, 18));
      getChildren().add(new MyLabel(x2, y, formatter.format(score.getTimestamp()), 18));
      getChildren().add(new MyLabel(x3, y, "" + score.getScore(), 18));
      getChildren().add(new MyLabel(x4, y, "" + score.getLevel(), 18));

      if (!score.getCountry().isEmpty()) {
        try {
          getChildren().add(new MyImageView(x5, y + 4, "images/flags/" + score.getCountry() + ".png", 0.6));
        } catch (Exception e) {
          // flag filename not found
        }
      }
      getChildren().add(new MyLabel(x6, y, score.getNickname(), 20));

      if (++lines > 15) {
        break;
      }
    }
  }

  public void draw() {

    MyFactory.getScoreDAO().clearGlobal();

    Image image1 = new Image("images/background4.jpg");
    BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
    BackgroundImage backgroundImage = new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    setBackground(new Background(backgroundImage));

    int offset = ((MyFactory.getSettingDAO().getSettings().getWidth() - 640) / 2);
    int x1 = 30 + offset;
    int x2 = 80 + offset;
    int x3 = 300 + offset;
    int x4 = 390 + offset;
    int x5 = 450 + offset;

    y = 20;
    getChildren().add(new MyLabel(0, y, "Worldwide High Score", 50, "white", "-fx-font-weight: bold;"));

    y += 80;
    getChildren().add(new MyLabel(x1, y, "Nr", 25));
    getChildren().add(new MyLabel(x2, y, "Date", 25));
    getChildren().add(new MyLabel(x3, y, "Score", 25));
    getChildren().add(new MyLabel(x4, y, "Map", 25));
    getChildren().add(new MyLabel(x5, y, "Nickname", 25));

    MyButton button1 = new MyButton(0, MyFactory.getSettingDAO().getSettings().getHeight() - 70, "Close", 18, Navigator.HOME);

    getChildren().add(button1);

    Task<Void> task = new Task<>() {
      public Void call() {
        CloudScore.getGlobal();
        return null;
      }
    };

    task.stateProperty().addListener((observable, oldValue, newState) -> {
      if (newState == State.SUCCEEDED) {
        showTable();
      }
    });

    new Thread(task).start();
  }
}

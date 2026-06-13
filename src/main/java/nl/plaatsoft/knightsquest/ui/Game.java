package nl.plaatsoft.knightsquest.ui;

import java.util.Date;

import nl.plaatsoft.knightsquest.common.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import nl.plaatsoft.knightsquest.network.CloudScore;
import nl.plaatsoft.knightsquest.network.CloudUser;
import nl.plaatsoft.knightsquest.model.Land;
import nl.plaatsoft.knightsquest.model.Player;
import nl.plaatsoft.knightsquest.model.PlayerEnum;
import nl.plaatsoft.knightsquest.model.Region;
import nl.plaatsoft.knightsquest.model.Score;
import nl.plaatsoft.knightsquest.tools.MyButton;
import nl.plaatsoft.knightsquest.tools.MyData;
import nl.plaatsoft.knightsquest.tools.MyFactory;
import nl.plaatsoft.knightsquest.tools.MyLabel;
import nl.plaatsoft.knightsquest.tools.MySound;

public class Game extends StackPane {
  private static final Logger log = LogManager.getLogger(Game.class);
  private GraphicsContext gc;
  private Canvas canvas;
  private double offsetX = 0;
  private double offsetY = 0;
  private AnimationTimer timer;
  private boolean gameOver;
  private int turn;
  private Pane pane3;
  private MyLabel label1;
  private MyLabel label2;
  private MyLabel label3;
  private MyLabel label4;
  private MyLabel[] label5;
  private MyButton btn;

  public void redraw() {

    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

    MyFactory.getLandDAO().draw();
    for (Player player : MyFactory.getPlayerDAO().getPlayers()) {
      player.draw();
    }
  }

  public int storeScore(int points, int level) {

    Score score = new Score(new Date(), points, level, CloudUser.getNickname(), "");
    int rank = MyFactory.getScoreDAO().addLocal(score);

    /* Sent score to cloud server */
    Task<Void> task = new Task<>() {
      public Void call() {
        CloudScore.set(AppConstants.APP_WS_NAME, AppConstants.APP_VERSION, score);
        return null;
      }
    };
    new Thread(task).start();
    return rank;
  }

  public int calculateScore(Player player, boolean won) {
    int score = 0;

    if (won) {
      score += 1000;
    }

    int tmp = (100 - turn) * 5;
    if (tmp > 0) {
      score += tmp;
    }

    score += player.getMoves();
    score += player.getCreates() * 2;
    score += player.getConquer() * 5;
    score += player.getUpgrades() * 10;

    return score;
  }

  public void playerWin(Player player) {

    if (!gameOver) {

      MySound.play(player, MySound.CLIP_END);

      label1.setText("Game Over");
      label2.setText("You win");

      int score = calculateScore(player, true);
      label4.setText("Total score = " + score);

      btn.setText("End [" + turn + "]");

      if (MyData.getMode() == MyData.MODE_1P) {

        /* Next map unlocked! */
        int nextMap = MyData.getNextMap(MyData.getMap());
        if ((nextMap > 0) && (!MyFactory.getSettingDAO().getSettings().getMapUnlocked(nextMap))) {
          label3.setText("Map " + nextMap + " is now unlocked!");
          MyFactory.getSettingDAO().getSettings().setMapUnlocked(MyData.getNextMap(MyData.getMap()), true);
        }

        if (score > MyFactory.getSettingDAO().getSettings().getScore(MyData.getMap())) {
          MyFactory.getSettingDAO().getSettings().setScore(MyData.getMap(), score);
        }
        MyFactory.getSettingDAO().save();

        storeScore(score, MyData.getMap());
      }

      gameOver = true;
    }
  }

  public void playerLose(Player player) {

    MySound.play(player, MySound.CLIP_END);

    Player human = MyFactory.getPlayerDAO().getHumanPlayer();

    if (!gameOver) {
      label1.setText("Game Over");
      label2.setText("You lose");

      int score = calculateScore(human, false);

      label4.setText("Total score = " + score);

      btn.setText("End [" + turn + "]");

      if (MyData.getMode() == MyData.MODE_1P) {
        if (score > MyFactory.getSettingDAO().getSettings().getScore(MyData.getMap())) {
          MyFactory.getSettingDAO().getSettings().setScore(MyData.getMap(), score);
          MyFactory.getSettingDAO().save();
        }
        storeScore(score, MyData.getMap());
      }

      gameOver = true;
    }
  }

  public void checkGameOver() {
    int count = 0;
    boolean humanAlive = true;
    Player winner = null;

    for (Player player : MyFactory.getPlayerDAO().getPlayers()) {
      if (player.getType() == PlayerEnum.HUMAN_LOCAL) {
        if (player.getRegion().isEmpty()) {
          humanAlive = false;
        }
      }

      if (!player.getRegion().isEmpty()) {
        winner = player;
        count++;
      }
    }

    if (count > 1) {

      if (!humanAlive) {
        // Human dead
        playerLose(MyFactory.getPlayerDAO().getHumanPlayer());

        // Bots alive, continue game in auto mode.
        timer.start();
      }

    } else {

      timer.stop();

      if (humanAlive) {
        playerWin(winner);
      } else {
        playerLose(MyFactory.getPlayerDAO().getHumanPlayer());
      }
    }
  }

  public void drawPlayerScore() {
    for (Player player : MyFactory.getPlayerDAO().getPlayers()) {
      label5[player.getId()].setText("Player " + player.getId() + ": " + player.getLandSize());
    }
  }

  private void decodeMessage(String json) {

    try {
      JSONObject obj = new JSONObject(json);
      String action = obj.getString("action");

      if (action.equals("abort")) {
        // Player left
      }

      if (action.equals("move")) {
        //int x1 = obj.getInt("x1");
        //int y1 = obj.getInt("y1");
        //int x2 = obj.getInt("x2");
        //int y2 = obj.getInt("y2");
      }

      if (action.equals("turn")) {


      }
    } catch (JSONException e) {
      log.error(e.getMessage());
    }

  }

  public void nextTurn() {

    MySound.play(MyFactory.getPlayerDAO().getHumanPlayer(), MySound.CLIP_TURN);

    if (MyData.getMode() == MyData.MODE_2P) {
      MyFactory.getUDPServer().turn();
    }

    MyFactory.getLandDAO().resetSelected();

    for (Player player : MyFactory.getPlayerDAO().getPlayers()) {
      int amount = MyFactory.getSoldierDAO().enableSoldier(player);

      for (Region region : player.getRegion()) {
        if (player.getType() == PlayerEnum.BOT) {

          for (int i = 0; i < amount; i++) {
            MyFactory.getSoldierDAO().moveBotSoldier(region);
          }

          MyFactory.getSoldierDAO().createBotSoldier(region);

        } else {

          // Human Player
          MyFactory.getSoldierDAO().newSoldierArrive(region);
        }
      }
    }

    /* Rebuild all regions */
    int regions = MyFactory.getRegionDAO().detectedRegions();
    MyFactory.getRegionDAO().rebuildRegions(regions);

    redraw();
    checkGameOver();
    drawPlayerScore();
  }

  public void init() {
    // Clear previous game
    MyFactory.clearFactory();

    label5 = new MyLabel[MyData.getPlayers() + 1];

    gameOver = false;
    turn = 1;

    // ------------------------------------------------------
    // BACKGROUND LAYER 1
    // ------------------------------------------------------

    Pane pane1 = new Pane();
    pane1.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    pane1.setId("background");

    getChildren().add(pane1);

    // ------------------------------------------------------
    // MAP LAYER 2
    // ------------------------------------------------------

    Pane pane2 = new Pane();
    pane2.setScaleX(AppConstants.SCALE);
    pane2.setScaleY(AppConstants.SCALE);
    pane2.setId("map");

    int size = 0;
    if (MyFactory.getSettingDAO().getSettings().getWidth() == 640) {

      size = AppConstants.SEGMENT_SIZE_640;
      MyFactory.getSoldierDAO().init(size);
      MyFactory.getBuildingDAO().init(size);

      canvas = new Canvas((size * 4 * (AppConstants.SEGMENT_X + 1)), (size * 2 * AppConstants.SEGMENT_Y));
      canvas.setLayoutX(AppConstants.OFFSET_X_640);
      canvas.setLayoutY(AppConstants.OFFSET_Y_480);

    } else if (MyFactory.getSettingDAO().getSettings().getWidth() == 800) {

      size = AppConstants.SEGMENT_SIZE_800;
      MyFactory.getSoldierDAO().init(size);
      MyFactory.getBuildingDAO().init(size);

      canvas = new Canvas((size * 4 * (AppConstants.SEGMENT_X + 1)), (size * 2 * AppConstants.SEGMENT_Y));
      canvas.setLayoutX(AppConstants.OFFSET_X_800);
      canvas.setLayoutY(AppConstants.OFFSET_Y_600);

    } else {

      size = AppConstants.SEGMENT_SIZE_1024;
      MyFactory.getSoldierDAO().init(size);
      MyFactory.getBuildingDAO().init(size);

      canvas = new Canvas((size * 4 * (AppConstants.SEGMENT_X + 1)), (size * 2 * AppConstants.SEGMENT_Y));
      canvas.setLayoutX(AppConstants.OFFSET_X_1024);
      canvas.setLayoutY(AppConstants.OFFSET_Y_768);
    }

    gc = canvas.getGraphicsContext2D();

    /* create land */
    MyFactory.getLandDAO().createMap(gc, size, MyData.getLevel() + 1);

    pane2.getChildren().add(canvas);
    getChildren().add(pane2);

    // ------------------------------------------------------
    // Control LAYER 3
    // ------------------------------------------------------

    pane3 = new Pane();
    pane3.setScaleX(AppConstants.SCALE);
    pane3.setScaleY(AppConstants.SCALE);
    pane3.setId("control");

    label1 = new MyLabel(0, (MyFactory.getSettingDAO().getSettings().getHeight() / 2) - 160, "", 80, "white", "-fx-font-weight: bold;");
    label2 = new MyLabel(0, (MyFactory.getSettingDAO().getSettings().getHeight() / 2) - 60, "", 40, "white", "-fx-font-weight: bold;");
    label3 = new MyLabel(0, (MyFactory.getSettingDAO().getSettings().getHeight() / 2), "", 40, "white", "-fx-font-weight: bold;");
    label4 = new MyLabel(0, (MyFactory.getSettingDAO().getSettings().getHeight() / 2) + 60, "", 40, "white", "-fx-font-weight: bold;");

    btn = new MyButton(10, 10, "Exit", 18, Navigator.NONE);
    btn.setPrefWidth(140);
    pane3.getChildren().add(label1);
    pane3.getChildren().add(label2);
    pane3.getChildren().add(label3);
    pane3.getChildren().add(label4);

    // ------------------------------------------------------
    // Player score board
    // ------------------------------------------------------

    Rectangle r = new Rectangle();
    r.setX(MyFactory.getSettingDAO().getSettings().getWidth() - 135);
    r.setY(10);
    r.setWidth(115);
    r.setHeight(MyData.getPlayers() * 20);
    r.setArcWidth(20);
    r.setArcHeight(20);
    r.setFill(Color.rgb(0, 0, 0, 0.7));
    pane3.getChildren().add(r);

    int y = 10;
    for (int id = 1; id <= MyData.getPlayers(); id++) {
      label5[id] = new MyLabel(MyFactory.getSettingDAO().getSettings().getWidth() - 125, y, "", 15, MyFactory.getPlayerDAO().getColor(id), "-fx-font-weight: bold;");
      pane3.getChildren().add(label5[id]);
      y += 18;
    }

    pane3.getChildren().add(btn);
    getChildren().add(pane3);

    // ------------------------------------------------------
    // Create players
    // ------------------------------------------------------

    for (int id = 1; id <= MyData.getPlayers(); id++) {

      PlayerEnum type;
      if (id == 1) {
        type = PlayerEnum.HUMAN_LOCAL;
      } else {
        type = PlayerEnum.BOT;
      }
      MyFactory.getPlayerDAO().createPlayer(gc, id, pane2, type);
    }

    /* Create Harbors */
    MyFactory.getBuildingDAO().createHarbors();

    /* Draw everything (map, player, buildings,etc..) */
    redraw();

    /* Draw score board */
    drawPlayerScore();

    if (MyData.getMode() == MyData.MODE_2P) {
      Task<Void> task1 = new Task<Void>() {
        public Void call() throws Exception {
          while (true) {
            String json = MyFactory.getUDPServer().receive();
            decodeMessage(json);
            Thread.sleep(1000);
          }
        }
      };
      new Thread(task1).start();
    }
  }

  public void start() {

    // ------------------------------------------------------
    // Human Player Actions
    // ------------------l------------------------------------

    // Mouse select land piece on map
    pane3.setOnMouseReleased(me -> {

      Land land = MyFactory.getLandDAO().getPlayerSelectedLand(offsetX, offsetY);
      if (land != null) {
        //log.info("land ["+land.getX()+","+land.getY()+" scale="+land.getScale()+"] selected");
        MyFactory.getLandDAO().doPlayerActions(land, MyFactory.getPlayerDAO().getHumanPlayer());

        if (MyFactory.getPlayerDAO().hasPlayerNoMoves(MyFactory.getPlayerDAO().getHumanPlayer())) {
          turn++;
          btn.setText("Turn [" + turn + "]");
          nextTurn();
        }

        redraw();
        drawPlayerScore();
        checkGameOver();
      }
    });

    // Scroll the map
    pane3.setOnMousePressed(me -> {
      offsetX = me.getSceneX() - canvas.getLayoutX();
      offsetY = me.getSceneY() - canvas.getLayoutY();
    });

    // Scroll the map
    pane3.setOnMouseDragged(me -> {

      double tmpX = me.getSceneX() - offsetX;
      double tmpY = me.getSceneY() - offsetY;

      canvas.setLayoutX(tmpX);
      canvas.setLayoutY(tmpY);
    });

    btn.setOnAction(event -> {

      // Human action button
      if ((turn == 1) || (gameOver)) {
        if (MyData.getMode() == MyData.MODE_1P) {
          Navigator.go(Navigator.MAP_SELECTOR);
        } else {
          Navigator.go(Navigator.COMMUNICATION);
        }
        timer.stop();
        return;
      }
      turn++;
      btn.setText("Turn [" + turn + "]");

      nextTurn();
    });

    timer = new AnimationTimer() {
      public void handle(long now) {

        /* Human dead, bots play on */
        turn++;
        btn.setText("End [" + turn + "]");

        nextTurn();
      }
    };
  }
}

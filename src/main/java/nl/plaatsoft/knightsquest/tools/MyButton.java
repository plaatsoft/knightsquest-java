package nl.plaatsoft.knightsquest.tools;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import nl.plaatsoft.knightsquest.ui.Navigator;

public class MyButton extends Button {

  public MyButton(double x, double y, String value, int fontSize, final int page) {

    int width = 180;
    if (x == 0) {
      x = (MyFactory.getSettingDAO().getSettings().getWidth() / 2) - (width / 2);
    }
    setText(value);
    setPrefWidth(width);
    setStyle("-fx-font-size:" + fontSize + "px;");

    setLayoutX(x);
    setLayoutY(y);

    setOnAction(event -> Navigator.go(page));
  }
}

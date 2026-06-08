package nl.plaatsoft.knightsquest.tools;

import javafx.scene.control.ComboBox;

public class MyComboBox extends ComboBox<Object> {

  public MyComboBox(int x, int y, String selected, String[] options, int fontSize) {

    setLayoutX(x);
    setLayoutY(y);

    setValue(selected);

    setStyle("-fx-font-size:" + fontSize + "px;");

    for (int i = 0; i < options.length; i++) {
      getItems().add(options[i]);
    }
  }
}

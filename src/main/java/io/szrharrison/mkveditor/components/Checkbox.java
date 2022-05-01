package io.szrharrison.mkveditor.components;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

public class Checkbox extends GridPane {
  private final CheckBox checkBox;
  public Checkbox(String label, Boolean initialValue) {
    checkBox = new CheckBox(label);
    checkBox.setStyle("-fx-text-fill: inherit;");
    checkBox.setSelected(initialValue);
    add(checkBox, 0, 0);
    setPadding(new Insets(0, 10, 10, 0));
  }

  public Checkbox(String label) {
    this(label, false);
  }

  public void setValue(Boolean value) {
    checkBox.setSelected(value);
  }

  public Boolean getValue() {
    return checkBox.isSelected();
  }

  public void setOnChange(ChangeListener<? super Boolean> handleChange) {
    checkBox.selectedProperty().addListener(handleChange);
  }
}

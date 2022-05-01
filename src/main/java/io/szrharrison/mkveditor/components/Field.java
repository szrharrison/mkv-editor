package io.szrharrison.mkveditor.components;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Getter;


public class Field extends VBox {
  @Getter
  private final TextField textField;

  public Field(String label, String initialValue) {
    getStyleClass().add("Field");
    Label labelElement = new Label(label);
    labelElement.getStyleClass().add("Field-label");
    getChildren().add(labelElement);
    textField = new TextField();
    textField.getStyleClass().add("Field-input");
    labelElement.setLabelFor(textField);
    getChildren().add(textField);
    setValue(initialValue);
    setPadding(new Insets(0, 10, 10, 0));
  }

  public Field(String label) {
    this(label, "");
  }

  public String getValue() {
    return textField.textProperty().getValue();
  }

  public void setValue(String value) {
    textField.setText(value);
  }

  public void setOnChange(ChangeListener<? super String> handleChange) {
    textField.textProperty().addListener(handleChange);
  }
}

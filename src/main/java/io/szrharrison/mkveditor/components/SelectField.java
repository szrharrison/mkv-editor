package io.szrharrison.mkveditor.components;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.Collection;

public class SelectField<T> extends GridPane {
  private final ComboBox<T> comboBox;

  public SelectField(String label, T initialValue, Collection<T> options) {
    Label labelElement = new Label(label);
    labelElement.setStyle("-fx-text-fill: inherit;");
    add(labelElement, 0, 0);
    comboBox = new ComboBox<>();
    comboBox.getItems().addAll(options);
    add(comboBox, 0, 1);
    setValue(initialValue);
    setPadding(new Insets(0, 10, 10, 0));
  }

  public SelectField(String label, T... options) {
    this(label, options[0], Arrays.asList(options));
  }

  public SelectField(String label, T initialValue, T... options) {
    this(label, initialValue, Arrays.asList(options));
  }

  public void setValue(T value) {
    comboBox.setValue(value);
  }

  public T getValue() {
    return comboBox.getValue();
  }

  public void setOnChange(ChangeListener<? super T> handleChange) {
    comboBox.valueProperty().addListener(handleChange);
  }
}

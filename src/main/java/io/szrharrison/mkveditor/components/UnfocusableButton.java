package io.szrharrison.mkveditor.components;

import javafx.scene.control.Button;

public class UnfocusableButton extends Button {
  public UnfocusableButton() {
    setFocusTraversable(false);
  }
}

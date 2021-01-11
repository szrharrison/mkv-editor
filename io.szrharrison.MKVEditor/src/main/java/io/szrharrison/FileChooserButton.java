package io.szrharrison;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileChooserButton extends Button {
  private final Stage stage;
  private final FileChooser fileChooser;
  private File selectedFile;
  private FileHandler onChoose;

  public FileChooserButton(String buttonLabel, Stage stage) {
    super(buttonLabel);
    this.stage = stage;
    this.fileChooser = new FileChooser();
    this.setOnAction(this.makeOnClickHandler());
  }

  public void addExtensions(FileChooser.ExtensionFilter... extensionFilters) {
    fileChooser.getExtensionFilters().addAll(extensionFilters);
  }

  public void setInitialDirectory(File directory) {
    fileChooser.setInitialDirectory(directory);
  }

  public void setOnChoose(FileHandler fileHandler) {
    this.onChoose = fileHandler;
  }


  private EventHandler<ActionEvent> makeOnClickHandler() {
    return (ActionEvent event) -> {
      selectedFile = fileChooser.showOpenDialog(stage);
      if (onChoose != null) {
        onChoose.call(selectedFile);
      }
    };
  }

  public interface FileHandler {
    public void call(File file);
  }
}

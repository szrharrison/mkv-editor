package io.szrharrison;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class MKVEditor extends Application {
  @Override
  public void start(Stage stage) throws Exception {
    initUI(stage);
  }

  private void initUI(Stage stage) {
    var root = new StackPane();
    var videoRoot = new BorderPane();

    Scene scene = new Scene(root, 1200, 675, Color.BLACK);

    var button = new FileChooserButton("Select a video", stage);
    mediaPlayer = new VideoPlayer();
    root.getChildren().add(videoRoot);
    button.setOnChoose((File file) -> {
      System.out.println("You picked this file:" + file.getAbsolutePath());
      mediaPlayer.start(videoRoot, file.getAbsolutePath());
    });
    root.getChildren().add(button);

    stage.setTitle("MKV Editor");
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void stop() {
    mediaPlayer.stop();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private VideoPlayer mediaPlayer;
}

package io.szrharrison;

import io.szrharrison.video_player.MediaPlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;

public class MKVEditor extends Application {
  private MediaPlayer mediaPlayer;

  @Override
  public void start(Stage stage) throws Exception {
    initUI(stage);
  }

  private void initUI(Stage stage) {
    var root = new StackPane();
    Scene scene = new Scene(root, 1200, 675, Color.BLACK);
    Font defaultFont = Font.getDefault();
    Font font = Font.loadFont("file:resources/fonts/MaterialIcons-Regular.ttf", defaultFont.getSize());
    System.out.println(font.getFamily());

    MenuItem openMenuItem = new MenuItem("Open");
    Menu fileMenu = new Menu("File");
    MenuBar menu = new MenuBar();

    fileMenu.getItems().add(openMenuItem);
    menu.getMenus().add(fileMenu);

    var button = new FileChooserButton("Select a video", stage);
    button.setOnChoose((File file) -> {
      if (file != null) {
        mediaPlayer.init(file.getAbsolutePath());
        root.getChildren().remove(button);
      }
    });

    openMenuItem.setOnAction(button.makeOnClickHandler());

    mediaPlayer = new MediaPlayer();
    mediaPlayer.setTop(menu);
    root.getChildren().add(mediaPlayer);

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
}

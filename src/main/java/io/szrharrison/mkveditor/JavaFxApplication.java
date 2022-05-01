package io.szrharrison.mkveditor;

import io.szrharrison.mkveditor.components.MediaInfo;
import io.szrharrison.mkveditor.components.video_player.MediaPlayer;
import io.szrharrison.mkveditor.components.video_player.control_bar.VideoBar;
import io.szrharrison.mkveditor.services.MkvReader;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Getter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import java.io.File;

public class JavaFxApplication extends Application {
  private ConfigurableApplicationContext context;
  private MediaPlayer mediaPlayer;
  private MediaInfo mediaInfo;

  @Override
  public void init() {
    ApplicationContextInitializer<GenericApplicationContext> initializer = genericApplicationContext -> {
      genericApplicationContext.registerBean(Application.class, () -> JavaFxApplication.this);
      genericApplicationContext.registerBean(Parameters.class, this::getParameters);
      genericApplicationContext.registerBean(HostServices.class, this::getHostServices);
    };

    this.context = new SpringApplicationBuilder().sources(MkvEditorApplication.class)
        .initializers(initializer)
        .build().run(getParameters().getRaw().toArray(new String[0]));
  }

  @Override
  public void start(Stage primaryStage) {
    var root = new BorderPane();
    this.context.publishEvent(new StageReadyEvent(primaryStage, root));
    initUI(primaryStage, root);
  }

  @Override
  public void stop() {
    mediaPlayer.stop();
    this.context.close();
    Platform.exit();
  }

  private void initUI(Stage stage, BorderPane root) {
    MenuItem openMenuItem = new MenuItem("Open");
    Menu fileMenu = new Menu("File");
    MenuBar menu = new MenuBar();

    fileMenu.getItems().add(openMenuItem);
    menu.getMenus().add(fileMenu);
    FileChooser fileChooser = new FileChooser();

    openMenuItem.setOnAction((ActionEvent event) -> {
      File selectedFile = fileChooser.showOpenDialog(stage);
      if (selectedFile != null) {
        mediaPlayer.init(selectedFile.getAbsolutePath());
        mediaInfo.loadFile(selectedFile.getAbsolutePath());
      }
    });

    VBox vBox = new VBox();

    mediaPlayer = new MediaPlayer(
        (MediaPlayerFactory) context.getBean("mediaPlayerFactory"),
        (EmbeddedMediaPlayer) context.getBean("mediaPlayer"),
        (VideoBar) context.getBean("videoBar")
    );
    mediaInfo = new MediaInfo(
        (MkvReader) context.getBean("mkvReader")
    );

    vBox.getChildren().add(mediaPlayer);
    VBox.setVgrow(mediaPlayer, Priority.ALWAYS);
    vBox.getChildren().add(mediaInfo);

    root.setTop(menu);
    root.setCenter(vBox);
  }

  static class StageReadyEvent extends ApplicationEvent {
    @Getter
    private final Pane root;

    public Stage getStage() {
      return (Stage) getSource();
    }

    public StageReadyEvent(Object source, Pane root) {
      super(source);
      this.root = root;
    }
  }
}

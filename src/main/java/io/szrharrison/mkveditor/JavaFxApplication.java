package io.szrharrison.mkveditor;

import io.szrharrison.mkveditor.video_player.MediaPlayer;
import io.szrharrison.mkveditor.video_player.control_bar.VideoBar;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
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
    var root = new StackPane();
    this.context.publishEvent(new StageReadyEvent(primaryStage, root));
    initUI(primaryStage, root);
  }

  @Override
  public void stop() {
    mediaPlayer.stop();
    this.context.close();
    Platform.exit();
  }

  private void initUI(Stage stage, StackPane root) {

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

    mediaPlayer = new MediaPlayer(
        (MediaPlayerFactory) context.getBean("mediaPlayerFactory"),
        (EmbeddedMediaPlayer) context.getBean("mediaPlayer"),
        (VideoBar) context.getBean("videoBar")
    );
    mediaPlayer.setTop(menu);
    root.getChildren().add(mediaPlayer);
  }

  static class StageReadyEvent extends ApplicationEvent {
    @Getter
    private final StackPane root;

    public Stage getStage() {
      return (Stage) getSource();
    }

    public StageReadyEvent(Object source, StackPane root) {
      super(source);
      this.root = root;
    }
  }
}

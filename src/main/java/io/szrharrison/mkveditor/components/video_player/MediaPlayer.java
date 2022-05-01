package io.szrharrison.mkveditor.components.video_player;

import io.szrharrison.mkveditor.components.video_player.control_bar.VideoBar;
import io.szrharrison.mkveditor.models.Node;
import io.szrharrison.mkveditor.services.MkvInfoCommander;
import io.szrharrison.mkveditor.services.MkvReader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static uk.co.caprica.vlcj.javafx.videosurface.ImageViewVideoSurfaceFactory.videoSurfaceForImageView;

@Service("MkvEditorMediaPlayer")
public class MediaPlayer extends BorderPane {
  private final EmbeddedMediaPlayer mediaPlayer;
  private final MediaPlayerFactory mediaPlayerFactory;

  @Autowired
  public MediaPlayer(
      MediaPlayerFactory mediaPlayerFactory,
      EmbeddedMediaPlayer mediaPlayer,
      VideoBar videoBar
  ) {
    this.mediaPlayerFactory = mediaPlayerFactory;
    this.mediaPlayer = mediaPlayer;

    Pane mediaPane = new Pane();
    ImageView videoImageView = new ImageView();

    videoImageView.setPreserveRatio(true);
    videoImageView.fitWidthProperty().bind(mediaPane.widthProperty());
    videoImageView.fitHeightProperty().bind(mediaPane.heightProperty());
    mediaPane.getChildren().add(videoImageView);
    setCenter(mediaPane);
    setMinHeight(200d);

    mediaPlayer.videoSurface().set(videoSurfaceForImageView(videoImageView));

    setStyle("-fx-background-color: black;");
    setBottom(videoBar);
  }

  public final void init(String videoMRL) {
    mediaPlayer.media().prepare(videoMRL);
  }

  public final void stop() {
    mediaPlayer.controls().stop();
    mediaPlayer.release();
    mediaPlayerFactory.release();
  }
}

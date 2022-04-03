package io.szrharrison.mkveditor.video_player;

import io.szrharrison.mkveditor.video_player.control_bar.VideoBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import static uk.co.caprica.vlcj.javafx.videosurface.ImageViewVideoSurfaceFactory.videoSurfaceForImageView;

@Service("MkvEditorMediaPlayer")
public class MediaPlayer extends BorderPane {
  private final EmbeddedMediaPlayer mediaPlayer;
  private final MediaPlayerFactory mediaPlayerFactory;

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

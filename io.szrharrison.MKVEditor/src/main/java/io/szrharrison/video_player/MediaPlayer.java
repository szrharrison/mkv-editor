package io.szrharrison.video_player;

import io.szrharrison.video_player.control_bar.VideoBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import static uk.co.caprica.vlcj.javafx.videosurface.ImageViewVideoSurfaceFactory.videoSurfaceForImageView;

public class MediaPlayer extends BorderPane {
  private final MediaPlayerFactory mediaPlayerFactory;
  private final EmbeddedMediaPlayer embeddedMediaPlayer;

  public MediaPlayer() {
    mediaPlayerFactory = new MediaPlayerFactory();
    embeddedMediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer();
    VideoBar videoBar = new VideoBar(embeddedMediaPlayer);
    Pane mediaPane = new Pane();
    ImageView videoImageView = new ImageView();

    videoImageView.setPreserveRatio(true);
    videoImageView.fitWidthProperty().bind(mediaPane.widthProperty());
    videoImageView.fitHeightProperty().bind(mediaPane.heightProperty());
    mediaPane.getChildren().add(videoImageView);
    setCenter(mediaPane);

    embeddedMediaPlayer.videoSurface().set(videoSurfaceForImageView(videoImageView));

    setStyle("-fx-background-color: black;");
    setBottom(videoBar);
  }

  public final void init(String videoMRL) {
    embeddedMediaPlayer.media().prepare(videoMRL);
  }

  public final void stop() {
    embeddedMediaPlayer.controls().stop();
    embeddedMediaPlayer.release();
    mediaPlayerFactory.release();
  }
}

package io.szrharrison;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.util.List;

import static uk.co.caprica.vlcj.javafx.videosurface.ImageViewVideoSurfaceFactory.videoSurfaceForImageView;

public class VideoPlayer {
  private final MediaPlayerFactory mediaPlayerFactory;
  private final EmbeddedMediaPlayer embeddedMediaPlayer;
  private ImageView videoImageView;

  public VideoPlayer() {
    this.mediaPlayerFactory = new MediaPlayerFactory();
    this.embeddedMediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer();
    this.embeddedMediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
      @Override
      public void playing(MediaPlayer mediaPlayer) {
      }

      @Override
      public void paused(MediaPlayer mediaPlayer) {
      }

      @Override
      public void stopped(MediaPlayer mediaPlayer) {
      }

      @Override
      public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
      }
    });
  }

  public void init() {
    this.videoImageView = new ImageView();
    this.videoImageView.setPreserveRatio(true);

    embeddedMediaPlayer.videoSurface().set(videoSurfaceForImageView(this.videoImageView));
  }

  public final void start(BorderPane root, String videoMRL) {
    root.setStyle("-fx-background-color: black;");

    videoImageView.fitWidthProperty().bind(root.widthProperty());
    videoImageView.fitHeightProperty().bind(root.heightProperty());

//    root.widthProperty().addListener((observableValue, oldValue, newValue) -> {
//      // If you need to know about resizes
//    });
//
//    root.heightProperty().addListener((observableValue, oldValue, newValue) -> {
//      // If you need to know about resizes
//    });

    root.setCenter(videoImageView);

    embeddedMediaPlayer.media().play(videoMRL);

    embeddedMediaPlayer.controls().setPosition(0.4f);
  }

  public final void stop() {
    embeddedMediaPlayer.controls().stop();
    embeddedMediaPlayer.release();
    mediaPlayerFactory.release();
  }
}

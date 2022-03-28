package io.szrharrison.video_player.control_bar;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

public class PlayPauseButton extends Button {
  private final MediaPlayer mediaPlayer;
  private PlayPauseButton.Status status;

  PlayPauseButton(MediaPlayer mediaPlayer) {
    this.mediaPlayer = mediaPlayer;

    setText("play_arrow");
//    setFont(Font.font("MaterialIcons"));
    this.mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
      @Override
      public void playing(MediaPlayer mediaPlayer) {
        startPlaying();
      }

      @Override
      public void paused(MediaPlayer mediaPlayer) {
        stopPlaying();
      }

      @Override
      public void stopped(MediaPlayer mediaPlayer) {
        stopPlaying();
      }

      @Override
      public void finished(MediaPlayer mediaPlayer) {
        stopPlaying();
      }
    });

    setOnAction(event -> {
      if (this.status == Status.PLAYING) {
        this.mediaPlayer.controls().pause();
      } else {
        this.mediaPlayer.controls().play();
      }
    });
  }

  private void startPlaying() {
    Platform.runLater(() -> {
      status = Status.PLAYING;
      setText("pause");
    });
  }

  private void stopPlaying() {
    Platform.runLater(() -> {
      status = Status.NOT_PLAYING;
      setText("play_arrow");
    });
  }

  private enum Status {
    PLAYING,
    NOT_PLAYING
  }
}

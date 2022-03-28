package io.szrharrison.video_player.control_bar.time_controller;

import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

public class TimeSlider extends Slider {
  public TimeSlider(MediaPlayer mediaPlayer) {
    super(0, mediaPlayer.status().length(), 0);

    HBox.setHgrow(this, Priority.ALWAYS);

    mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
      @Override
      public void timeChanged(MediaPlayer mediaPlayer, long time) {
        Platform.runLater(() -> setValue(time));
      }

      @Override
      public void lengthChanged(MediaPlayer mediaPlayer, long newLength) {
        Platform.runLater(() -> setMax(newLength));
      }
    });

    valueProperty().addListener(observable -> {
      if (isPressed()) {
        mediaPlayer.controls().setTime(Double.valueOf(getValue()).longValue());
      }
    });
  }
}

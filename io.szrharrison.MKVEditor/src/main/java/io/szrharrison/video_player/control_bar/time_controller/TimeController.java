package io.szrharrison.video_player.control_bar.time_controller;

import io.szrharrison.models.Time;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

public class TimeController extends HBox {
  private final Time currentTime;
  private final Time length;
  private final Label label;

  public TimeController(MediaPlayer mediaPlayer) {
    setAlignment(Pos.CENTER);
    HBox.setHgrow(this, Priority.ALWAYS);

    currentTime = new Time(0);
    length = new Time(mediaPlayer.status().length());
    label = new Label(getLabelText());
    mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
      @Override
      public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
        currentTime.set(newTime);
        Platform.runLater(() -> label.setText(getLabelText()));
      }

      @Override
      public void lengthChanged(MediaPlayer mediaPlayer, long newLength) {
        length.set(newLength);
        Platform.runLater(() -> label.setText(getLabelText()));
      }
    });

    final TimeSlider timeSlider = new TimeSlider(mediaPlayer);
    label.setLabelFor(timeSlider);
    getChildren().add(timeSlider);
    getChildren().add(label);
  }

  private String getLabelText() {
    return currentTime.toString().split("\\.")[0] + "/" + length.toString();
  }
}

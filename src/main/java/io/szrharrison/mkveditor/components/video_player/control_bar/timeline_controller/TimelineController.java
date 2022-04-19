package io.szrharrison.mkveditor.components.video_player.control_bar.timeline_controller;

import io.szrharrison.mkveditor.models.Time;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

@Service
public class TimelineController extends HBox {
  private final Time currentTime;
  private final Time length;
  private final Label label;

  @Autowired
  public TimelineController(MediaPlayer mediaPlayer, TimeSlider timeSlider) {
    setAlignment(Pos.CENTER);
    HBox.setHgrow(this, Priority.ALWAYS);

    currentTime = new Time(0);
    length = new Time(mediaPlayer.status().length());
    label = new Label(getLabelText());
    mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
      @Override
      public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
        setCurrentTime(newTime);
      }

      @Override
      public void lengthChanged(MediaPlayer mediaPlayer, long newLength) {
        setLength(newLength);
      }
    });

    label.setLabelFor(timeSlider);
    getChildren().add(timeSlider);
    getChildren().add(label);
  }

  private String getLabelText() {
    return currentTime.toString().split("\\.")[0] + "/" + length.toString();
  }

  private void setCurrentTime(long currentTime) {
    this.currentTime.set(currentTime);
    Platform.runLater(() -> label.setText(getLabelText()));
  }

  private void setLength(long length) {
    this.length.set(length);
    Platform.runLater(() -> label.setText(getLabelText()));
  }
}

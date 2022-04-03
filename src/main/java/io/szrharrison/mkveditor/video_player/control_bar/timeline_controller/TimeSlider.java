package io.szrharrison.mkveditor.video_player.control_bar.timeline_controller;

import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.caprica.vlcj.player.base.ChapterDescription;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventListener;

@Service
public class TimeSlider extends Slider {
  @Autowired
  TimeSlider(MediaPlayer mediaPlayer) {
    super(0, mediaPlayer.status().length(), 0);

    HBox.setHgrow(this, Priority.ALWAYS);

    mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
      @Override
      public void stopped(MediaPlayer mediaPlayer) {
        Platform.runLater(() -> setValue(0));
      }

      @Override
      public void finished(MediaPlayer mediaPlayer) {
        Platform.runLater(() -> setValue(0));
      }

      @Override
      public void timeChanged(MediaPlayer mediaPlayer, long time) {
        Platform.runLater(() -> setValue(time));
      }

      @Override
      public void lengthChanged(MediaPlayer mediaPlayer, long newLength) {
        Platform.runLater(() -> setMax(newLength));
      }

      @Override
      public void chapterChanged(MediaPlayer mediaPlayer, int newChapter) {
        ChapterDescription description = mediaPlayer.chapters().descriptions().get(newChapter);
        if (description != null) {
          Platform.runLater(() -> setValue(description.offset()));
        }
      }
    });

    valueProperty().addListener(observable -> {
      if (isPressed()) {
        mediaPlayer.controls().setTime(Double.valueOf(getValue()).longValue());
      }
    });
  }
}

package io.szrharrison.mkveditor.components.video_player.control_bar.volume_controller;

import javafx.scene.control.Slider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

@Service
public class VolumeSlider extends Slider {
  @Autowired
  VolumeSlider(MediaPlayer mediaPlayer) {
    setPrefWidth(70);
    setMinWidth(30);
    setValue(100);

    mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
      @Override
      public void muted(MediaPlayer mediaPlayer, boolean muted) {
        setValue(0);
      }

      @Override
      public void volumeChanged(MediaPlayer mediaPlayer, float volume) {
        setValue(volume);
      }
    });

    valueProperty().addListener(observable -> {
      if (isPressed()) {
        mediaPlayer.audio().setVolume(Double.valueOf(getValue()).intValue());
      }
    });
  }
}

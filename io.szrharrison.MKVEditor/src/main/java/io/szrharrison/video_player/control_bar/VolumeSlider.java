package io.szrharrison.video_player.control_bar;

import javafx.scene.control.Slider;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

public class VolumeSlider extends Slider {
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
        mediaPlayer.audio().setVolume(Double.valueOf(getValue()).intValue()); // It would set the volume
      }
    });
  }
}

package io.szrharrison.video_player.control_bar;

import javafx.scene.control.Button;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

public class StopButton extends Button {
  MediaPlayer mediaPlayer;

  StopButton(MediaPlayer mediaPlayer) {
    this.mediaPlayer = mediaPlayer;

    setText("stop");
//    setFont();
    setOnAction(event -> {
      this.mediaPlayer.controls().stop();
    });
  }
}

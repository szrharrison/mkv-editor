package io.szrharrison.video_player.control_bar;

import io.szrharrison.video_player.control_bar.time_controller.TimeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

public class VideoBar extends HBox {
  public VideoBar(MediaPlayer mediaPlayer) {
    PlayPauseButton playPauseButton = new PlayPauseButton(mediaPlayer);
    TimeController timeController = new TimeController(mediaPlayer);
    // Slider for volume
    VolumeSlider volumeSlider = new VolumeSlider(mediaPlayer);

    setAlignment(Pos.CENTER); // setting the HBox to center
    setPadding(new Insets(5, 10, 5, 10));

    // Adding the components to the bottom
    getChildren().add(playPauseButton);
    getChildren().add(timeController);
    getChildren().add(new Label("Volume:"));
    getChildren().add(volumeSlider);
  }
}

package io.szrharrison.mkveditor.video_player.control_bar.time_controls;

import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeControls extends HBox {
  @Autowired
  public TimeControls(PlayPauseButton playPauseButton,
                      SkipPreviousButton skipPreviousButton,
                      SkipNextButton skipNextButton) {
    getChildren().add(skipPreviousButton);
    getChildren().add(playPauseButton);
    getChildren().add(skipNextButton);
    getStyleClass().add("ButtonGroup");
  }
}

package io.szrharrison.mkveditor.components.video_player.control_bar;

import io.szrharrison.mkveditor.components.video_player.control_bar.time_controls.TimeControls;
import io.szrharrison.mkveditor.components.video_player.control_bar.timeline_controller.TimelineController;
import io.szrharrison.mkveditor.components.video_player.control_bar.volume_controller.VolumeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoBar extends HBox {

  @Autowired
  public VideoBar(TimeControls timeControls,
                  StopButton stopButton,
                  TimelineController timelineController,
                  VolumeController volumeController) {
    setAlignment(Pos.CENTER); // setting the HBox to center
    setPadding(new Insets(5, 10, 5, 10));
    setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(0d), new BorderWidths(2))));

    // Adding the components to the bottom
    getChildren().add(timeControls);
    getChildren().add(stopButton);
    getChildren().add(timelineController);
    getChildren().add(volumeController);
  }
}

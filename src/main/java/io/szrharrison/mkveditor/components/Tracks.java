package io.szrharrison.mkveditor.components;

import io.szrharrison.mkveditor.models.track.Track;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class Tracks extends ScrollPane {
  private final VBox tracksContainer;
  private final Map<Track, TrackInfo> trackInfoByTrack;

  public Tracks() {
    trackInfoByTrack = new HashMap<>();
    tracksContainer = new VBox();
    setContent(tracksContainer);
    hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
    vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    fitToHeightProperty().set(true);
    fitToWidthProperty().set(true);
    setStyle("-fx-background-color: #333; -fx-text-fill: white;");
    tracksContainer.setStyle("-fx-background-color: #333; -fx-text-fill: white;");
  }

  public void addTrack(Track track) {
    trackInfoByTrack.put(track, new TrackInfo(track));
    tracksContainer.getChildren().add(trackInfoByTrack.get(track));
  }
}

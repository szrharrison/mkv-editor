package io.szrharrison.mkveditor.components;

import io.szrharrison.mkveditor.models.MkvInfo;
import io.szrharrison.mkveditor.models.Node;
import io.szrharrison.mkveditor.services.MkvReader;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("MkvEditorMediaInfo")
public class MediaInfo extends VBox {
  private final MkvReader mkvReader;
  private final List<Node> files;
  private final Field field;
  private final VBox tracksContainer;

  @Autowired
  public MediaInfo(MkvReader mkvReader) {
    this.mkvReader = mkvReader;
    this.files = new ArrayList<>();
    tracksContainer = new VBox();
    ScrollPane tracksPane = new ScrollPane();
    tracksPane.setContent(tracksContainer);
    tracksPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
    tracksPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    tracksPane.fitToHeightProperty().set(true);
    tracksPane.fitToWidthProperty().set(true);
    tracksPane.setStyle("-fx-background-color: #333; -fx-text-fill: white;");
    tracksContainer.setStyle("-fx-background-color: #333; -fx-text-fill: white;");

    setAlignment(Pos.CENTER);
    setStyle("-fx-background-color: #333; -fx-text-fill: white;");


    field = new Field("title");
    field.getTextField().prefWidthProperty().bind(widthProperty());
    getChildren().add(field);
    getChildren().add(tracksPane);
  }

  public void loadFile(String fileUri) {
    files.add(mkvReader.read(fileUri));
    MkvInfo mkvInfo = MkvInfo.fromNode(files.get(0));
    field.setValue(mkvInfo.getTitle());
    mkvInfo.getTracks().forEach(track -> {
      tracksContainer.getChildren().add(new TrackInfo(track));
    });
  }
}

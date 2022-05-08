package io.szrharrison.mkveditor.components;

import io.szrharrison.mkveditor.models.MkvInfo;
import io.szrharrison.mkveditor.models.Node;
import io.szrharrison.mkveditor.models.chapter.Chapter;
import io.szrharrison.mkveditor.models.chapter.Edition;
import io.szrharrison.mkveditor.services.MkvReader;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service("MkvEditorMediaInfo")
public class MediaInfo extends VBox {
  private final MkvReader mkvReader;
  private final List<Node> files;
  private final Field field;
  private final Tracks tracks;
  private final Chapters chapters;


  @Autowired
  public MediaInfo(MkvReader mkvReader) {
    this.mkvReader = mkvReader;
    this.tracks = new Tracks();
    this.chapters = new Chapters();
    this.files = new ArrayList<>();
    setAlignment(Pos.CENTER);
    setStyle("-fx-background-color: #333; -fx-text-fill: white;");

    field = new Field("title");
    field.getTextField().prefWidthProperty().bind(widthProperty());
    TabPane tabPane = new TabPane();
    Tab tracksTab = new Tab("Tracks", tracks);
    Tab chaptersTab = new Tab("Chapters", chapters);
    tabPane.getTabs().addAll(tracksTab, chaptersTab);
    setVgrow(tabPane, Priority.ALWAYS);

    getChildren().add(field);
    getChildren().add(tabPane);
  }

  public void loadFile(String fileUri) {
    files.add(mkvReader.read(fileUri));
    MkvInfo mkvInfo = MkvInfo.fromNode(files.get(0));
    field.setValue(mkvInfo.getTitle());
    mkvInfo.getTracks().forEach(tracks::addTrack);
    mkvInfo.getEditions().stream()
        .map(Edition::getChapters)
        .flatMap(List::stream)
        .filter(Objects::nonNull)
        .sorted(Comparator.comparing(Chapter::getTimeStart))
        .forEach(chapters::addChapter);
  }
}

package io.szrharrison.mkveditor.components;

import io.szrharrison.mkveditor.models.chapter.Chapter;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class Chapters extends ScrollPane {
  private final VBox chaptersContainer;
  private final Map<Chapter, ChapterInfo> chapterInfoByChapter;

  public Chapters() {
    chapterInfoByChapter = new HashMap<>();
    chaptersContainer = new VBox();
    setContent(chaptersContainer);
    hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
    vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    fitToHeightProperty().set(true);
    fitToWidthProperty().set(true);
    setStyle("-fx-background-color: #333; -fx-text-fill: white;");
    chaptersContainer.setStyle("-fx-background-color: #333; -fx-text-fill: white;");
  }

  public void addChapter(Chapter chapter) {
    chapterInfoByChapter.put(chapter,new ChapterInfo(chapter));
    chaptersContainer.getChildren().add(chapterInfoByChapter.get(chapter));
  }
}
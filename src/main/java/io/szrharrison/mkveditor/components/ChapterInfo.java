package io.szrharrison.mkveditor.components;


import com.neovisionaries.i18n.LanguageAlpha3Code;
import io.szrharrison.mkveditor.models.Flag;
import io.szrharrison.mkveditor.models.Time;
import io.szrharrison.mkveditor.models.chapter.Chapter;
import io.szrharrison.mkveditor.models.chapter.ChapterDisplay;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.util.Optional;

public class ChapterInfo extends HBox {
  private final Chapter chapter;
  private final Chapter.ChapterBuilder chapterBuilder = Chapter.builder();

  public ChapterInfo(Chapter chapter) {
    this.chapter = chapter;
    getStyleClass().add("TrackInfo");
    setBorder(new Border(new BorderStroke(
        Paint.valueOf("transparent"),
        Paint.valueOf("transparent"),
        Paint.valueOf("#888"),
        Paint.valueOf("transparent"),
        BorderStrokeStyle.SOLID,
        BorderStrokeStyle.SOLID,
        BorderStrokeStyle.SOLID,
        BorderStrokeStyle.SOLID,
        CornerRadii.EMPTY,
        new BorderWidths(0d, 0d, 2d, 0d),
        new Insets(10)
    )));

    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER_LEFT);
    Field nameField = new Field("name", Optional.ofNullable(chapter.getDisplay()).map(ChapterDisplay::getName).orElse(null));
    Checkbox isHiddenField = new Checkbox(
        "hidden",
        Optional.ofNullable(chapter.getHidden()).map(Flag::getValue).orElse(null)
    );
    Checkbox isEnabledField = new Checkbox(
        "enabled",
        Optional.ofNullable(chapter.getEnabled()).map(Flag::getValue).orElse(null)
    );
    SelectField<LanguageAlpha3Code> languageField = new SelectField<>(
        "language",
        Optional.ofNullable(chapter.getDisplay()).map(ChapterDisplay::getLanguage).orElse(null),
        LanguageAlpha3Code.values()
    );
    Field startTimeField = new Field("start time", Optional.ofNullable(chapter.getTimeStart()).map(Time::toString).orElse(null));
    Field endTimeField = new Field("end time", Optional.ofNullable(chapter.getTimeEnd()).map(Time::toString).orElse(null));

    gridPane.add(nameField, 0, 0);
    gridPane.add(isHiddenField, 1, 0);
    gridPane.add(isEnabledField, 2, 0);
    gridPane.add(languageField, 0, 1);
    gridPane.add(startTimeField, 1, 1);
    gridPane.add(endTimeField, 2, 1);

    nameField.setOnChange(makeHandleNameChange());
    isHiddenField.setOnChange(makeHandleIsHiddenChange());
    isEnabledField.setOnChange(makeHandleIsEnabledChange());
    languageField.setOnChange(makeHandleLanguageChange());
    startTimeField.setOnChange(makeHandleStartTimeChange());
    endTimeField.setOnChange(makeHandleEndTimeChange());

    getChildren().add(gridPane);
  }

  private ChangeListener<String> makeHandleNameChange() {
    return (value, oldValue, newValue) -> chapterBuilder.display(new ChapterDisplay(chapterBuilder.build()
        .getDisplay()
        .getLanguage(), newValue));
  }

  private ChangeListener<Boolean> makeHandleIsHiddenChange() {
    return (value, oldValue, newValue) -> chapterBuilder.hidden(Flag.valueOf(newValue));
  }

  private ChangeListener<Boolean> makeHandleIsEnabledChange() {
    return (value, oldValue, newValue) -> chapterBuilder.enabled(Flag.valueOf(newValue));
  }

  private ChangeListener<LanguageAlpha3Code> makeHandleLanguageChange() {
    return (value, oldValue, newValue) -> chapterBuilder.display(new ChapterDisplay(
        newValue,
        chapterBuilder.build().getDisplay().getName()
    ));
  }

  private ChangeListener<String> makeHandleStartTimeChange() {
    return (value, oldValue, newValue) -> chapterBuilder.timeStart(Time.valueOf(newValue));
  }

  private ChangeListener<String> makeHandleEndTimeChange() {
    return (value, oldValue, newValue) -> chapterBuilder.timeEnd(Time.valueOf(newValue));
  }
}

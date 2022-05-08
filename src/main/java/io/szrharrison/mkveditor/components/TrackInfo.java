package io.szrharrison.mkveditor.components;


import com.neovisionaries.i18n.LanguageAlpha3Code;
import io.szrharrison.mkveditor.models.Flag;
import io.szrharrison.mkveditor.models.track.Track;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.util.Locale;
import java.util.Optional;

public class TrackInfo extends HBox {
  private final Track track;
  private final Track.TrackBuilder trackBuilder = Track.builder();

  public TrackInfo(Track track) {
    this.track = track;
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
    Field nameField = new Field("name", track.getName());
    Checkbox isDefaultField = new Checkbox("default", Optional.ofNullable(track.getIsDefault()).map(Flag::getValue).orElse(null));
    Checkbox isEnabledField = new Checkbox("enabled", Optional.ofNullable(track.getEnabled()).map(Flag::getValue).orElse(null));
    SelectField<LanguageAlpha3Code> languageField = new SelectField<>(
        "language",
        track.getLanguage(),
        LanguageAlpha3Code.values()
    );

    final Label trackLabel = new Label("track " + track.getNumber() + ": " + track.getType()
        .getValue()
        .toLowerCase(Locale.ROOT));
    trackLabel.setStyle("-fx-text-fill: inherit;");
    gridPane.add(trackLabel, 0, 0);
    gridPane.add(nameField, 0, 1);
    gridPane.add(isDefaultField, 1, 1);
    gridPane.add(isEnabledField, 2, 1);
    gridPane.add(languageField, 0, 2);

    nameField.setOnChange(makeHandleNameChange());
    isDefaultField.setOnChange(makeHandleIsDefaultChange());
    isEnabledField.setOnChange(makeHandleIsEnabledChange());
    languageField.setOnChange(makeHandleLanguageChange());

    getChildren().add(gridPane);
  }

  private ChangeListener<String> makeHandleNameChange() {
    return (value, oldValue, newValue) -> trackBuilder.name(newValue);
  }

  private ChangeListener<Boolean> makeHandleIsDefaultChange() {
    return (value, oldValue, newValue) -> trackBuilder.isDefault(Flag.valueOf(newValue));
  }

  private ChangeListener<Boolean> makeHandleIsEnabledChange() {
    return (value, oldValue, newValue) -> trackBuilder.enabled(Flag.valueOf(newValue));
  }

  private ChangeListener<LanguageAlpha3Code> makeHandleLanguageChange() {
    return (value, oldValue, newValue) -> trackBuilder.language(newValue);
  }
}

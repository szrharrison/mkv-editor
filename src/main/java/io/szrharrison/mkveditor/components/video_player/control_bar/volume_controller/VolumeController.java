package io.szrharrison.mkveditor.components.video_player.control_bar.volume_controller;

import io.szrharrison.mkveditor.models.FontAwesomeGlyphs;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolumeController extends HBox {
  @Autowired
  public VolumeController(GlyphFont fontAwesome, VolumeSlider volumeSlider) {
    setAlignment(Pos.CENTER);
    setSpacing(4);
    HBox.setHgrow(this, Priority.ALWAYS);

    Label startLabel = new Label("", fontAwesome.create(FontAwesome.Glyph.VOLUME_OFF.toString()).color(Color.WHITE));
    startLabel.accessibleTextProperty().setValue("mute");
    Label endLabel = new Label("", fontAwesome.create(FontAwesomeGlyphs.VOLUME_HIGH.toString()).color(Color.WHITE));
    endLabel.accessibleTextProperty().setValue("max volume");

    getChildren().add(startLabel);
    getChildren().add(volumeSlider);
    getChildren().add(endLabel);
  }
}

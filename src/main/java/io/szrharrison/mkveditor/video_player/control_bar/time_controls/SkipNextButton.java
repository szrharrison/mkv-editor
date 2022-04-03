package io.szrharrison.mkveditor.video_player.control_bar.time_controls;

import io.szrharrison.mkveditor.models.FontAwesomeGlyphs;
import javafx.scene.control.Button;
import org.controlsfx.glyphfont.GlyphFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

@Service
public class SkipNextButton extends Button {
  @Autowired
  SkipNextButton(MediaPlayer mediaPlayer, GlyphFont fontAwesome) {
    setStyle("-fx-background-radius: 0 4 4 0");
    setGraphic(fontAwesome.create(FontAwesomeGlyphs.FORWARD_STEP.toString()));
    setOnAction(event -> mediaPlayer.chapters().next());
  }
}

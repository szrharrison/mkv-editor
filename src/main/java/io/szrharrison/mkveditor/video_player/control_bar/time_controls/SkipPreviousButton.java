package io.szrharrison.mkveditor.video_player.control_bar.time_controls;

import io.szrharrison.mkveditor.models.FontAwesomeGlyphs;
import javafx.scene.control.Button;
import org.controlsfx.glyphfont.GlyphFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

@Service
public class SkipPreviousButton extends Button {
  @Autowired
  SkipPreviousButton(MediaPlayer mediaPlayer, GlyphFont fontAwesome) {
    setStyle("-fx-background-radius: 4 0 0 4");
    setGraphic(fontAwesome.create(FontAwesomeGlyphs.BACKWARD_STEP.toString()));
    setOnAction(event -> {
      System.out.println(mediaPlayer.chapters().count() + " chapters in this video");
      mediaPlayer.chapters().previous();
    });
  }
}

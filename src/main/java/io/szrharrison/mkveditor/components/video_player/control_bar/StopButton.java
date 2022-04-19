package io.szrharrison.mkveditor.components.video_player.control_bar;

import io.szrharrison.mkveditor.components.UnfocusableButton;
import io.szrharrison.mkveditor.models.FontAwesomeGlyphs;
import org.controlsfx.glyphfont.GlyphFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

@Service
public class StopButton extends UnfocusableButton {

@Autowired
  StopButton(MediaPlayer mediaPlayer, GlyphFont fontAwesome) {
    setGraphic(fontAwesome.create(FontAwesomeGlyphs.STOP.toString()));
    setOnAction(event -> {
      mediaPlayer.controls().stop();
    });
  }
}

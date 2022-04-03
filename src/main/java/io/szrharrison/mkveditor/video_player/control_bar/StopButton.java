package io.szrharrison.mkveditor.video_player.control_bar;

import io.szrharrison.mkveditor.models.FontAwesomeGlyphs;
import javafx.scene.control.Button;
import org.controlsfx.glyphfont.GlyphFont;
import org.girod.javafx.svgimage.SVGImage;
import org.girod.javafx.svgimage.SVGLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

@Service
public class StopButton extends Button {

@Autowired
  StopButton(MediaPlayer mediaPlayer, GlyphFont fontAwesome) {
    setGraphic(fontAwesome.create(FontAwesomeGlyphs.STOP.toString()));
    setOnAction(event -> {
      mediaPlayer.controls().stop();
    });
  }
}

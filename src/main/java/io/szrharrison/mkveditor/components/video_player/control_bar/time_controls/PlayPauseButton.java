package io.szrharrison.mkveditor.components.video_player.control_bar.time_controls;

import io.szrharrison.mkveditor.components.UnfocusableButton;
import io.szrharrison.mkveditor.models.FontAwesomeGlyphs;
import javafx.application.Platform;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

@Service
public class PlayPauseButton extends UnfocusableButton {
  private final MediaPlayer mediaPlayer;
  private PlayPauseButton.Status status;
  private final Glyph playArrow;
  private final Glyph pause;

  @Autowired
  PlayPauseButton(MediaPlayer mediaPlayer, GlyphFont fontAwesome) {
    setStyle("-fx-background-radius: 0");
    this.mediaPlayer = mediaPlayer;
    pause = fontAwesome.create(FontAwesomeGlyphs.PAUSE);
    playArrow = fontAwesome.create(FontAwesomeGlyphs.PLAY);
    setGraphic(playArrow);
    this.mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
      @Override
      public void playing(MediaPlayer mediaPlayer) {
        startPlaying();
      }

      @Override
      public void paused(MediaPlayer mediaPlayer) {
        stopPlaying();
      }

      @Override
      public void stopped(MediaPlayer mediaPlayer) {
        stopPlaying();
      }

      @Override
      public void finished(MediaPlayer mediaPlayer) {
        stopPlaying();
      }
    });

    setOnAction(event -> {
      if (this.status == Status.PLAYING) {
        this.mediaPlayer.controls().pause();
      } else {
        this.mediaPlayer.controls().play();
      }
    });
  }

  private void startPlaying() {
    Platform.runLater(() -> {
      status = Status.PLAYING;
      setGraphic(pause);
    });
  }

  private void stopPlaying() {
    Platform.runLater(() -> {
      status = Status.NOT_PLAYING;
      setGraphic(playArrow);
    });
  }

  private enum Status {
    PLAYING,
    NOT_PLAYING
  }
}

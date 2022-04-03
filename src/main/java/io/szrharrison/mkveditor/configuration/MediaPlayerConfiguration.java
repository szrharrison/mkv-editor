package io.szrharrison.mkveditor.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

@Configuration
public class MediaPlayerConfiguration {
  private MediaPlayerFactory mediaPlayerFactory;

  @Bean
  MediaPlayerFactory mediaPlayerFactory() {
    if (mediaPlayerFactory == null) {
      mediaPlayerFactory = new MediaPlayerFactory();
    }

    return mediaPlayerFactory;
  }

  @Bean(name = "mediaPlayer")
  EmbeddedMediaPlayer mediaPlayer() {
    return mediaPlayerFactory().mediaPlayers().newEmbeddedMediaPlayer();
  }
}

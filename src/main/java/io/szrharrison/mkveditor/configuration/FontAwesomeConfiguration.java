package io.szrharrison.mkveditor.configuration;

import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FontAwesomeConfiguration {

  @Bean(name = "fontAwesome")
  GlyphFont fontAwesome() {
    return GlyphFontRegistry.font("FontAwesome");
  }
}

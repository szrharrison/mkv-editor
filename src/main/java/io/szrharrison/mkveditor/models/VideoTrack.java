package io.szrharrison.mkveditor.models;

import com.neovisionaries.i18n.LanguageAlpha3Code;

import java.util.Locale;

public class VideoTrack extends Track {
  private final Flag interlaced;
  private final Integer pixelWidth;
  private final Integer pixelHeight;
  private final Integer pixelCropBottom;
  private final Integer pixelCropTop;
  private final Integer pixelCropLeft;
  private final Integer pixelCropRight;


  public VideoTrack(Integer number,
                    String uid,
                    TrackType type,
                    Flag enabled,
                    Flag isDefault,
                    Flag forced,
                    Flag hearingImpaired,
                    Flag visualImpaired,
                    Flag textDescriptions,
                    Flag original,
                    Flag commentary,
                    Flag lacing,
                    String name,
                    LanguageAlpha3Code language,
                    Locale languageIETF,
                    String codecId,
                    Flag interlaced,
                    Integer pixelWidth,
                    Integer pixelHeight,
                    Integer pixelCropBottom,
                    Integer pixelCropTop,
                    Integer pixelCropLeft,
                    Integer pixelCropRight) {
    super(
        number,
        uid,
        type,
        enabled,
        isDefault,
        forced,
        hearingImpaired,
        visualImpaired,
        textDescriptions,
        original,
        commentary,
        lacing,
        name,
        language,
        languageIETF,
        codecId
    );
    this.interlaced = interlaced;
    this.pixelWidth = pixelWidth;
    this.pixelHeight = pixelHeight;
    this.pixelCropBottom = pixelCropBottom;
    this.pixelCropTop = pixelCropTop;
    this.pixelCropLeft = pixelCropLeft;
    this.pixelCropRight = pixelCropRight;
  }
}

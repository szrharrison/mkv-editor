package io.szrharrison.mkveditor.models;

public enum FontAwesomeGlyphs {
  ARROW_DOWN_A_Z("\uf15d"),
  ARROW_DOWN_Z_A("\uf881"),
  ARROW_RIGHT_FROM_BRACKET("\uf08b"),
  ARROW_RIGHT_TO_BRACKET("\uf090"),
  BACKWARD("\uf04a"),
  BACKWARD_FAST("\uf049"),
  BACKWARD_STEP("\uf048"),
  BAN("\uf05e"),
  BOOKMARK("\uf02e"),
  CHECK("\uf00c"),
  CIRCLE_MINUS("\uf056"),
  CLONE("\uf24d"),
  COMPRESS("\uf066"),
  DELETE_LEFT("\uf55a"),
  EJECT("\uf052"),
  ELLIPSIS("\uf141"),
  ELLIPSIS_VERTICAL("\uf142"),
  ERASER("\uf12d"),
  EXPAND("\uf065"),
  FILE("\uf15b"),
  FILE_AUDIO("\uf1c7"),
  FILE_LINES("\uf15c"),
  FILE_VIDEO("\uf1c8"),
  FILM("\uf008"),
  FORWARD("\uf04e"),
  FORWARD_FAST("\uf050"),
  FORWARD_STEP("\uf051"),
  MINUS("\uf068"),
  PAUSE("\uf04c"),
  PLAY("\uf04b"),
  REPEAT("\uf363"),
  SHUFFLE("\uf074"),
  SLIDERS("\uf1de"),
  SQUARE_MINUS("\uf146"),
  STOP("\uf04d"),
  TRASH("\uf1f8"),
  VOLUME_HIGH("\uf028"),
  VOLUME_LOW("\uf027"),
  VOLUME_OFF("\uf026"),
  VOLUME_XMARK("\uf6a9");

//  VOLUME("f6a8"),
//  VOLUME_SLASH("f2e2"),
//  SQUARE_SLIDERS("f3f0"),
//  SLIDERS_UP("f3f1"),
//  SQUARE_SLIDERS_UP("f3f2"),
//  SCRUBBER("f2f8"),
//  REPEAT_1("f365"),
//  REPEAT_1_ALT("f366"),
//  REPEAT_ALT("f364"),
//  PLAY_PAUSE("e22f"),
//  FILM_SIMPLE("f3a0"),
//  FILM_SLASH("e179"),
//  WAVEFORM("f8f1"),
//  WAVEFORM_LINES("f8f2"),

  private final String value;

  FontAwesomeGlyphs(String unicode) {
    this.value = unicode;
  }

  @Override
  public String toString() {
    return value;
  }
}

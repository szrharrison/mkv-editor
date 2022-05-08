package io.szrharrison.mkveditor.models.track;

import lombok.Getter;

@Getter
public enum TrackType {
  video("video", 1),
  audio("audio", 2),
  complex("complex", 3),
  logo("logo", 16),
  subtitle("subtitle", 17),
  subtitles("subtitles", 17),
  buttons("buttons", 18),
  control("control", 32),
  metadata("metadata", 33);

  private final String value;
  private final int code;

  TrackType(String value, int code) {
    this.value = value;
    this.code = code;
  }
}

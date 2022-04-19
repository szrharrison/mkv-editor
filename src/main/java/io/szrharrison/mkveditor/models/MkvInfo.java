package io.szrharrison.mkveditor.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MkvInfo {
  private final String uid;
  private final Time duration;
  private final List<Track> tracks;
  private final List<Tag> tags;
}

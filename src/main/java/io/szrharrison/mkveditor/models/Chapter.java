package io.szrharrison.mkveditor.models;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Chapter {
  private final Integer uid;
  private final Long timeStart;
  private final Long timeEnd;
  private final Flag hidden;
  private final Flag enabled;
  private final PhysicalTypes physicalEquivalent;
  private final List<Integer> trackUids;
}

package io.szrharrison.mkveditor.models.chapter;

import io.szrharrison.mkveditor.models.Flag;
import io.szrharrison.mkveditor.models.Node;
import io.szrharrison.mkveditor.models.PhysicalTypes;
import io.szrharrison.mkveditor.models.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class Chapter {
  private final String uid;
  private final Time timeStart;
  private final Time timeEnd;
  private final Flag hidden;
  private final Flag enabled;
  private final ChapterDisplay display;
  private final PhysicalTypes physicalEquivalent;
  private final List<Integer> trackUids;

  public static Chapter fromNode(Node node) {
    return new Chapter(
        node.get("Chapter UID").getValue(),
        Time.valueOf(node.get("Chapter time start").getValue()),
        Time.valueOf(node.get("Chapter time end").getValue()),
        Flag.FALSE,
        Flag.TRUE,
        ChapterDisplay.fromNode(node.get("Chapter display")),
        null,
        null
    );
  }
}

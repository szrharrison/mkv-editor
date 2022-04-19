package io.szrharrison.mkveditor.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class Tag {
  public static Tag fromNode(Node tagNode) {
    String targetTrackUid = Optional.ofNullable(tagNode.get("Targets"))
        .map(targets -> targets.get("Track UID"))
        .map(Node::getValue)
        .orElse(null);
    final Node simple = tagNode.get("Simple");

    return new Tag(
        targetTrackUid,
        simple.get("Name").getValue(),
        simple.get("String").getValue()
    );
  }

  private final String targetTrackUid;
  private final String name;
  private final String value;
}

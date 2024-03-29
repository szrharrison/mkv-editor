package io.szrharrison.mkveditor.models;

import io.szrharrison.mkveditor.models.chapter.Edition;
import io.szrharrison.mkveditor.models.track.Track;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Getter
@Builder
@AllArgsConstructor
public class MkvInfo {
  private final String uid;
  private final String title;
  private final Time duration;
  private final List<Track> tracks;
  private final List<Tag> tags;
  private final List<Edition> editions;

  public static MkvInfo fromNode(Node node) {
    final List<Track> tracks = node.get("Segment").get("Tracks").getChildren().stream().map(Track::fromNode).toList();
    final List<Tag> tags = Optional.ofNullable(node.get("Segment").get("Tags"))
        .map(Node::getChildren)
        .map(Nodes::stream)
        .map(stream -> stream.map(Tag::fromNode).toList())
        .orElse(emptyList());
    final List<Edition> editions = node.get("Segment").get("Chapters").getChildren().stream().map(Edition::fromNode).toList();
    return new MkvInfo(
        node.get("Segment").get("Segment information").get("Segment UID").getValue(),
        node.get("Segment").get("Segment information").get("Title").getValue(),
        Time.valueOf(node.get("Segment").get("Segment information").get("Duration").getValue()),
        tracks,
        tags,
        editions
    );
  }
}

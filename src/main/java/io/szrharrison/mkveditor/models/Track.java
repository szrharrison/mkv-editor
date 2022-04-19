package io.szrharrison.mkveditor.models;

import com.neovisionaries.i18n.LanguageAlpha3Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Locale;
import java.util.Optional;

import static io.szrharrison.mkveditor.models.Flag.FALSE;
import static io.szrharrison.mkveditor.models.Flag.TRUE;

@Getter
@AllArgsConstructor
public class Track {
  public static Track fromNode(Node trackNode) {
    final TrackType track_type = TrackType.valueOf(trackNode.get("Track type").getValue());
    if (track_type == TrackType.video) {
      Optional<Node> videoTrack = Optional.ofNullable(trackNode.get("Video track"));
      return new VideoTrack(
          Integer.parseInt(trackNode.get("Track number").getValue().split(" ")[0]),
          trackNode.get("Track UID").getValue(),
          track_type,
          TRUE,
          FALSE,
          FALSE,
          FALSE,
          FALSE,
          FALSE,
          FALSE,
          FALSE,
          Flag.fromString(Optional.ofNullable(trackNode.get("\"Lacing\" flag")).map(Node::getValue).orElse("0")),
          null,
          LanguageAlpha3Code.getByCodeIgnoreCase(trackNode.get("Language").getValue()),
          null,
          trackNode.get("Codec ID").getValue(),
          FALSE,
          videoTrack.map(node -> node.get("Pixel width")).map(Node::getValue).map(Integer::parseInt).orElse(null),
          videoTrack.map(node -> node.get("Pixel height")).map(Node::getValue).map(Integer::parseInt).orElse(null),
          videoTrack.map(node -> node.get("Pixel crop bottom")).map(Node::getValue).map(Integer::parseInt).orElse(null),
          videoTrack.map(node -> node.get("Pixel crop top")).map(Node::getValue).map(Integer::parseInt).orElse(null),
          videoTrack.map(node -> node.get("Pixel crop left")).map(Node::getValue).map(Integer::parseInt).orElse(null),
          videoTrack.map(node -> node.get("Pixel crop right")).map(Node::getValue).map(Integer::parseInt).orElse(null)
      );
    } else {
      return new Track(
          Integer.parseInt(trackNode.get("Track number").getValue().split(" ")[0]),
          trackNode.get("Track UID").getValue(),
          track_type,
          TRUE,
          FALSE,
          FALSE,
          FALSE,
          FALSE,
          FALSE,
          FALSE,
          FALSE,
          Flag.fromString(Optional.ofNullable(trackNode.get("\"Lacing\" flag")).map(Node::getValue).orElse("0")),
          null,
          LanguageAlpha3Code.getByCodeIgnoreCase(Optional.ofNullable(trackNode.get("Language")).map(Node::getValue).orElse("und")),
          null,
          trackNode.get("Codec ID").getValue()
      );
    }
  }

  private final Integer number;
  private final String uid;
  private final TrackType type;
  private final Flag enabled;
  private final Flag isDefault;
  private final Flag forced;
  private final Flag hearingImpaired;
  private final Flag visualImpaired;
  private final Flag textDescriptions;
  private final Flag original;
  private final Flag commentary;
  private final Flag lacing;
  private final String name;
  private final LanguageAlpha3Code language;
  private final Locale languageIETF;
  private final String codecId;
}

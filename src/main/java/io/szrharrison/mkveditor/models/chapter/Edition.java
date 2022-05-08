package io.szrharrison.mkveditor.models.chapter;

import io.szrharrison.mkveditor.models.Flag;
import io.szrharrison.mkveditor.models.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Edition {
  private final String uid;
  private final Flag hidden;
  private final Flag isDefault;
  private final Flag ordered;
  private final List<Chapter> chapters;

  public static Edition fromNode(Node node) {
    List<Chapter> chapters = node.getChildren().stream().filter(child -> child.getKey().equals("Chapter atom")).map(Chapter::fromNode).toList();
    return new Edition(
        node.get("Edition UID").getValue(),
        Flag.fromString(node.get("Edition flag hidden").getValue()),
        Flag.fromString(node.get("Edition flag default").getValue()),
        Flag.FALSE,
        chapters
    );
  }
}

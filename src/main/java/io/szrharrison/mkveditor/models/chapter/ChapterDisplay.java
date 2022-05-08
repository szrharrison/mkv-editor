package io.szrharrison.mkveditor.models.chapter;

import com.neovisionaries.i18n.LanguageAlpha3Code;
import io.szrharrison.mkveditor.models.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChapterDisplay {
  private final LanguageAlpha3Code language;
  private final String name;

  public static ChapterDisplay fromNode(Node node) {
    return new ChapterDisplay(
        LanguageAlpha3Code.getByCodeIgnoreCase(node.get("Chapter language").getValue()),
        node.get("Chapter string").getValue()
    );
  }
}

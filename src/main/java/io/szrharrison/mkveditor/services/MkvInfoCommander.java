package io.szrharrison.mkveditor.services;

import io.szrharrison.mkveditor.models.*;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;

import static java.util.Collections.emptyList;

@Log
@Service
public class MkvInfoCommander {
  private final ProcessBuilder processBuilder = new ProcessBuilder("mkvinfo");

  public MkvInfo run(String fileLocation) throws IOException {
    processBuilder.command("mkvinfo", fileLocation);
    Process process = processBuilder.start();
    InputStream inputStream = process.getInputStream();
    Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
    StringBuilder stringBuilder = new StringBuilder();
    Node root = new Node("File", fileLocation);
    Node current = root;
    Integer lastLevel = -1;
    while (scanner.hasNext()) {
      String line = scanner.next();
      if (line.length() <= 0) {
        continue;
      }
      Integer level = getLevel(line);
      String rep = line.replace("+", "").replace("|", "");
      int keyValueSeparatorIndex = rep.indexOf(':');
      if (keyValueSeparatorIndex < 0) {
        keyValueSeparatorIndex = rep.indexOf(',');
      }

      String key = null;
      String value = null;
      if (keyValueSeparatorIndex < 0) {
        key = rep.trim();
      } else {
        key = rep.substring(0, keyValueSeparatorIndex).trim();
        value = rep.substring(keyValueSeparatorIndex + 1).trim();
      }
      Node newNode = new Node(key, value);
      if (level > lastLevel) {
        current.add(newNode);
        current = newNode;
      } else if (level < lastLevel) {
        int down = lastLevel - level + 1;
        Node node = current;
        while (down-- > 0) {
          node = node.getParent();
        }
        node.add(newNode);
        current = newNode;
      } else {
        Node parent = current.getParent();
        parent.add(newNode);
        current = newNode;
      }
      lastLevel = level;

      stringBuilder.append('\n');
      stringBuilder.append(line);
    }

    String results = stringBuilder.toString();
    log.info( results);
    log.info( root.toString());

    final List<Track> tracks = root.get("Segment").get("Tracks").getChildren().stream().map(Track::fromNode).toList();
    final List<Tag> tags = Optional.ofNullable(root.get("Segment").get("Tags"))
        .map(Node::getChildren)
        .map(Nodes::stream)
        .map(stream -> stream.map(Tag::fromNode).toList())
        .orElse(emptyList());
    return new MkvInfo(
        root.get("Segment").get("Segment information").get("Segment UID").getValue(),
        Time.valueOf(root.get("Segment").get("Segment information").get("Duration").getValue()),
        tracks,
        tags
    );
  }

  private Integer getLevel(String line) {
    int index = line.indexOf('+');
    if (index < 0) {
      return -1;
    } else {
      String ls = line.substring(0, index + 1);
      if (ls.contains("|")) {
        String blanks = ls.substring(1);
        return blanks.length();
      } else {
        return 0;
      }
    }
  }
}

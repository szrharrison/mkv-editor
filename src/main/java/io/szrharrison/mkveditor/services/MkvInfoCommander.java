package io.szrharrison.mkveditor.services;

import io.szrharrison.mkveditor.models.Node;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Log
@Service
public class MkvInfoCommander {
  private final ProcessBuilder processBuilder = new ProcessBuilder("mkvinfo");

  public Node run(String fileLocation) throws IOException {
    processBuilder.command("mkvinfo", fileLocation);
    Process process = processBuilder.start();
    InputStream inputStream = process.getInputStream();
    Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
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
    }

    log.info("\n" + root);
    return root;
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

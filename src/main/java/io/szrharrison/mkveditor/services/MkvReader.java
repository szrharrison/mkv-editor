package io.szrharrison.mkveditor.services;

import io.szrharrison.mkveditor.models.Node;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class MkvReader {
  private final MkvInfoCommander mkvInfoCommander;

  public Node read(String mkvFilePath) {
    Node info = null;
    try {
       info = mkvInfoCommander.run(mkvFilePath);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return info;
  }
}

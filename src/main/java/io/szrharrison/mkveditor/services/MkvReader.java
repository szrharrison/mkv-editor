package io.szrharrison.mkveditor.services;

import io.szrharrison.mkveditor.models.MkvInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class MkvReader {
  private final MkvInfoCommander mkvInfoCommander;

  public void read(String mkvFilePath) {
    try {
      MkvInfo info = mkvInfoCommander.run(mkvFilePath);
      if (!info.getTags().isEmpty()) {
        System.out.println("Language: " + info.getTracks().get(0).getLanguage());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

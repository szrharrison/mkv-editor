package io.szrharrison.mkveditor.models;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum Flag {
  TRUE(1),
  FALSE(0);

  private final int value;

  Flag(int value) {
    this.value = value;
  }

  public static Flag fromString(String flag) {
    flag = flag.trim();
    if (Objects.equals(flag, "true") || Objects.equals(flag, "1")) {
      return TRUE;
    } else if (Objects.equals(flag, "false") || Objects.equals(flag, "0")) {
      return FALSE;
    } else {
      return null;
    }
  }
}

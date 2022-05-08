package io.szrharrison.mkveditor.models;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum Flag {
  TRUE(true),
  FALSE(false);

  private final Boolean value;

  Flag(boolean value) {
    this.value = value;
  }

  public static Flag valueOf(Boolean value) {
    if (value) {
      return TRUE;
    } else {
      return FALSE;
    }
  }

  public static Flag fromString(String flag) {
    if (flag != null) {
      flag = flag.trim();
      if (Objects.equals(flag.toLowerCase(), "true") || Objects.equals(flag, "1")) {
        return TRUE;
      } else if (Objects.equals(flag.toLowerCase(), "false") || Objects.equals(flag, "0")) {
        return FALSE;
      } else {
        return null;
      }
    } else {
      return null;
    }
  }
}

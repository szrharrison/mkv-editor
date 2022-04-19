package io.szrharrison.mkveditor.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Attachment {
  private final String description;
  private final String name;
  private final String mimeType;
  private final Integer uid;
}

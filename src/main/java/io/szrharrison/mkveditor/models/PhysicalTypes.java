package io.szrharrison.mkveditor.models;

public enum PhysicalTypes {
  COLLECTION(70),
  MEDIUM(60),
  SIDE(50),
  LAYER(40),
  SESSION(30),
  TRACK(20),
  INDEX(10);

  private final Integer value;

  PhysicalTypes(Integer value) {
    this.value = value;
  }
}

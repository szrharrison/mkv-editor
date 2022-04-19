package io.szrharrison.mkveditor.models;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.reverse;

public class Time implements Cloneable {
  private final StringBuilder stringBuilder = new StringBuilder();
  private long milliseconds;

  public static Time valueOf(String input) {
    if (input.contains(":")) {
      List<String> timePieces = asList(input.split(":"));
      reverse(timePieces);
      long milliseconds = 0;
      if (timePieces.get(0).contains(".")) {
        milliseconds = Long.parseLong(timePieces.get(0).split("\\.")[1].substring(0, 3));
      }
      Time returnTime = new Time(milliseconds);
      returnTime.setSecondsPart(Integer.parseInt(timePieces.get(0).split("\\.")[0]));
      if (timePieces.size() >= 2) {
        returnTime.setMinutesPart(Integer.parseInt(timePieces.get(1)));
      }
      if (timePieces.size() >= 3) {
        returnTime.setHoursPart(Integer.parseInt(timePieces.get(2)));
      }
      return returnTime;
    } else {
      return null;
    }
  }

  public Time(long milliseconds) {
    this.milliseconds = milliseconds;
  }

  public long inMilliseconds() {
    return milliseconds;
  }

  public double inSeconds() {
    return milliseconds / 1000d;
  }

  public double inMinutes() {
    return inSeconds() / 60;
  }

  public double inHours() {
    return inMinutes() / 60;
  }

  public int hoursPart() {
    return Double.valueOf(Math.floor(inHours())).intValue();
  }

  public int minutesPart() {
    return Double.valueOf(Math.floor(inMinutes())).intValue() - 60 * hoursPart();
  }

  public int secondsPart() {
    return Double.valueOf(Math.floor(inSeconds()) - 60 * Math.floor(inMinutes())).intValue();
  }

  public int millisecondsPart() {
    return Double.valueOf(milliseconds - 1000 * Math.floor(inSeconds())).intValue();
  }

  public Time set(long milliseconds) {
    this.milliseconds = milliseconds;
    return this;
  }

  public Time add(Time time) {
    this.milliseconds += time.milliseconds;
    return this;
  }

  public Time subtract(Time time) {
    this.milliseconds -= time.milliseconds;
    return this;
  }

  public Time setSecondsPart(int seconds) {
    return this.subtract(fromSeconds(secondsPart())).add(fromSeconds(seconds));
  }

  public Time setMinutesPart(int minutes) {
    return this.subtract(fromMinutes(minutesPart())).add(fromMinutes(minutes));
  }

  public Time setHoursPart(int hours) {
    return this.subtract(fromHours(hoursPart())).add(fromHours(hours));
  }

  public Time copy(Time time) {
    this.milliseconds = time.milliseconds;
    return this;
  }

  @SuppressWarnings("MethodDoesntCallSuperMethod")
  @Override
  public Time clone() {
    return new Time(this.milliseconds);
  }

  public static Time fromSeconds(int seconds) {
    return new Time(seconds * 1000L);
  }

  public static Time fromMinutes(int minutes) {
    return fromSeconds(minutes * 60);
  }

  public static Time fromHours(int hours) {
    return fromMinutes(hours * 60);
  }

  @Override
  public String toString() {
    if (hoursPart() != 0) {
      stringBuilder.append(hoursPart());
      stringBuilder.append(':');
    }
    if (minutesPart() != 0 || hoursPart() != 0) {
      stringBuilder.append(minutesPart());
      stringBuilder.append(":");
    }

    stringBuilder.append(secondsPart());
    stringBuilder.append('.');
    stringBuilder.append(millisecondsPart());
    final String string = stringBuilder.toString();
    stringBuilder.setLength(0);
    return string;
  }
}

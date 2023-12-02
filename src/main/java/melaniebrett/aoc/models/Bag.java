package melaniebrett.aoc.models;

import static java.lang.Math.max;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public record Bag(int blueCount, int redCount, int greenCount) {

  public static Bag countAll(String input) {
    return new Bag(
        countColour(input, "blue"), countColour(input, "red"), countColour(input, "green"));
  }

  private static int countColour(String input, String colour) {
    Pattern colourCountPattern =
        Pattern.compile(String.format("(\\d+)(?= %s)", colour), Pattern.CASE_INSENSITIVE);
    return colourCountPattern
        .matcher(input)
        .results()
        .map(MatchResult::group)
        .map(Integer::parseInt)
        .findFirst()
        .orElse(0);
  }

  public boolean contains(Bag other) {
    return this.blueCount >= other.blueCount
        && this.redCount >= other.redCount
        && this.greenCount >= other.greenCount;
  }

  public Bag add(Bag other) {
    return new Bag(
        this.blueCount + other.blueCount,
        this.redCount + other.redCount,
        this.greenCount + other.greenCount);
  }

  public Bag increaseBag(Bag other) {
    if (this.contains(other)) {
      return this;
    }
    return new Bag(
        max(this.blueCount, other.blueCount),
        max(this.redCount, other.redCount),
        max(this.greenCount, other.greenCount));
  }

  public int power() {
    return this.blueCount() * this.redCount() * this.greenCount();
  }
}

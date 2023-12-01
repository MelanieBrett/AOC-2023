package melaniebrett.aoc.models;

public enum Numbers {
  ONE("1"),
  TWO("2"),
  THREE("3"),
  FOUR("4"),
  FIVE("5"),
  SIX("6"),
  SEVEN("7"),
  EIGHT("8"),
  NINE("9");

  private final String value;

  Numbers(final String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static String replaceValues(String input) {
    for (Numbers number : Numbers.values()) {
      input = input.replaceAll(number.name().toLowerCase(), number.getValue());
    }
    return input;
  }
}

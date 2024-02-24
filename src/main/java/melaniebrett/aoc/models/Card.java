package melaniebrett.aoc.models;

import java.util.HashMap;
import java.util.Map;

public enum Card {
  ACE("A"),
  KING("K"),
  QUEEN("Q"),
  JACK("J"),
  TEN("T"),
  NINE("9"),
  EIGHT("8"),
  SEVEN("7"),
  SIX("6"),
  FIVE("5"),
  FOUR("4"),
  THREE("3"),
  TWO("2");

  private final String value;

  // Reverse-lookup map for getting a day from an abbreviation
  private static final Map<String, Card> lookup = new HashMap<>();

  static {
    for (Card c : Card.values()) {
      lookup.put(c.getValue(), c);
    }
  }

  Card(final String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Card of(String abbreviation) {
    return lookup.get(abbreviation);
  }
}

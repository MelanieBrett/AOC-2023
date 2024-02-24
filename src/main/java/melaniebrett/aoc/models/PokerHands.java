package melaniebrett.aoc.models;

import java.util.List;
import java.util.Map;
import java.util.Set;

public enum PokerHands {
  FIVE_OF_A_KIND,
  FOUR_OF_A_KIND,
  FULL_HOUSE,
  THREE_OF_A_KIND,
  TWO_PAIR,
  ONE_PAIR,
  HIGH_CARD;

  public static PokerHands getStrength(Hand hand) {
    Map<String, Long> cards = hand.cardCount();

    if (isFiveOfAKind(cards)) {
      return FIVE_OF_A_KIND;
    } else if (isFourOfAKind(cards)) {
      return FOUR_OF_A_KIND;
    } else if (isFullHouse(cards)) {
      return FULL_HOUSE;
    } else if (isThreeOfAKind(cards)) {
      return THREE_OF_A_KIND;
    } else if (isTwoPair(cards)) {
      return TWO_PAIR;
    } else if (isOnePair(cards)) {
      return ONE_PAIR;
    } else if (isHighCard(cards)) {
      return HIGH_CARD;
    } else System.out.println("Hand not valid: " + hand);
    return null;
  }

  private static boolean isFiveOfAKind(Map<String, Long> hand) {
    return hand.size() == 1;
  }

  private static boolean isFourOfAKind(Map<String, Long> hand) {
    return hand.size() == 2 && hand.containsValue(4L);
  }

  private static boolean isFullHouse(Map<String, Long> hand) {
    return hand.size() == 2 && hand.values().containsAll(Set.of(2L, 3L));
  }

  private static boolean isThreeOfAKind(Map<String, Long> hand) {
    return hand.size() == 3 && hand.containsValue(3L);
  }

  private static boolean isTwoPair(Map<String, Long> hand) {
    return hand.size() == 3 && hand.values().containsAll(List.of(2L, 2L, 1L));
  }

  private static boolean isOnePair(Map<String, Long> hand) {
    return hand.size() == 4;
  }

  private static boolean isHighCard(Map<String, Long> hand) {
    return hand.size() == 5;
  }
}

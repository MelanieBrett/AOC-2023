package melaniebrett.aoc.models;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Hand(Card first, Card second, Card third, Card fourth, Card fifth) {
  public List<Card> cardList() {
    return List.of(first, second, third, fourth, fifth);
  }

  public static Hand of(String cards) {
    return of(Arrays.stream(cards.split("")).map(Card::of).toList());
  }

  public static Hand of(List<Card> cards) {
    return new Hand(cards.get(0), cards.get(1), cards.get(2), cards.get(3), cards.get(4));
  }

  public Map<String, Long> cardCount() {
    return Stream.of(first, second, third, fourth, fifth)
        .map(Card::getValue)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }

  public static class HandComparator {
    private static final Comparator<Hand> byRanking = Comparator.comparing(PokerHands::getStrength);
    private static final Comparator<Hand> byRankingWild =
        Comparator.comparing(PokerHands::getStrengthWild);
    public static final Comparator<Hand> byRankingByCards =
        byRanking.thenComparing(HandComparator::compareByCards);
    public static final Comparator<Hand> byRankingWildByCards =
        byRankingWild.thenComparing(HandComparator::compareByCards);

    private static int compareByCards(Hand hand1, Hand hand2) {
      List<Card> cards1 = hand1.cardList();
      List<Card> cards2 = hand2.cardList();

      int i = 0;
      while (cards1.get(i).equals(cards2.get(i))) {
        i++;
      }
      return cards1.get(i).compareTo(cards2.get(i));
    }
  }
}

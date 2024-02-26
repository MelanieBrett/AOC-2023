package melaniebrett.aoc;

import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toMap;
import static melaniebrett.aoc.Utils.DECIMAL_FORMAT;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import melaniebrett.aoc.models.Hand;
import melaniebrett.aoc.models.Hand.HandComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day07 {
  private static final String DAY = "07";
  private static final Logger logger = LoggerFactory.getLogger(Day07.class);
  private static final Path inputPath = new Utils().loadFileAsPath(String.format("Day%s.txt", DAY));

  public static void solution() {
    List<String> inputs = Utils.getInputLines(inputPath);

    logger.info("Day " + DAY);
    logger.info("Part 1: {}", DECIMAL_FORMAT.format(part1(inputs)));
    //    Part 1: 254024898
    logger.info("Part 2: {}", DECIMAL_FORMAT.format(part2(inputs)));
    //    Part 2: 254115617
  }

  public static double part1(List<String> inputs) {
    Map<Hand, Long> handToBid = getHandToBid(inputs);

    List<Hand> sortedHands =
        handToBid.keySet().stream().sorted(HandComparator.byRankingByCards).toList();

    double result = 0;
    for (int i = sortedHands.size() - 1; i >= 0; i--) {
      result += handToBid.get(sortedHands.get(i)) * (sortedHands.size() - i);
    }

    return result;
  }

  public static double part2(List<String> inputs) {
    List<String> inputsWild = inputs.stream().map(s -> s.replace("J", "W")).toList();
    Map<Hand, Long> handToBid = getHandToBid(inputsWild);

    List<Hand> sortedHands =
        handToBid.keySet().stream().sorted(HandComparator.byRankingWildByCards).toList();

    double result = 0;
    for (int i = sortedHands.size() - 1; i >= 0; i--) {
      result += handToBid.get(sortedHands.get(i)) * (sortedHands.size() - i);
    }

    return result;
  }

  private static Map<Hand, Long> getHandToBid(List<String> inputs) {
    return inputs.stream()
        .collect(toMap(i -> Hand.of(i.split(" ")[0]), i -> parseLong(i.split(" ")[1])));
  }
}

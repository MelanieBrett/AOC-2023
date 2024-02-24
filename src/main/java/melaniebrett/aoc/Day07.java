package melaniebrett.aoc;

import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toMap;

import java.nio.file.Path;
import java.text.DecimalFormat;
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

    DecimalFormat df = new DecimalFormat("#");
    df.setMaximumFractionDigits(8);

    logger.info("Day " + DAY);
    logger.info("Part 1: {}", df.format(part1(inputs)));
    //    Part 1:
    logger.info("Part 2: {}", part2(inputs));
    //    Part 2:
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

  public static int part2(List<String> inputs) {
    return 0;
  }

  private static Map<Hand, Long> getHandToBid(List<String> inputs) {
    return inputs.stream()
        .collect(toMap(i -> Hand.of(i.split(" ")[0]), i -> parseLong(i.split(" ")[1])));
  }
}

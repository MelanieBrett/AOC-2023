package melaniebrett.aoc;

import static java.lang.StrictMath.pow;
import static java.util.stream.Collectors.toMap;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day04 {
  private static final String DAY = "04";
  private static final Logger logger = LoggerFactory.getLogger(Day04.class);
  private static final Path inputPath = new Utils().loadFileAsPath(String.format("Day%s.txt", DAY));

  public static void solution() {
    List<String> inputs = Utils.getInputLines(inputPath);

    logger.info("Day " + DAY);
    logger.info("Part 1: {}", part1(inputs));
    //    Part 1: 21138
    logger.info("Part 2: {}", part2(inputs));
    //    Part 2:
  }

  public static int part1(List<String> inputs) {
    return getGameToMatches(inputs).values().stream()
        .map(l -> pow(2, l - 1))
        .map(Double::intValue)
        .reduce(0, Integer::sum);
  }

  public static int part2(List<String> inputs) {
    Map<Integer, Long> games = getGameToMatches(inputs);
    int usedCards = 0;
    Deque<Integer> stack = new LinkedList<>(games.keySet());

    while (!stack.isEmpty()) {
      int game = stack.pop();
      int matches = games.get(game).intValue();

      while (matches > 0) {
        stack.push(game + matches);
        matches = matches - 1;
      }
      usedCards = usedCards + 1;
    }

    return usedCards;
  }

  private static Map<Integer, Long> getGameToMatches(List<String> inputs) {
    return inputs.stream()
        .collect(
            toMap(
                i -> Integer.parseInt(i.split(":")[0].replaceAll(" ", "").replaceAll("Card", "")),
                i -> {
                  String[] game = i.split(":")[1].split("\\|");
                  Set<Integer> winningNumbers =
                      Arrays.stream(game[0].split(" "))
                          .filter(x -> !x.isEmpty())
                          .map(Integer::parseInt)
                          .collect(Collectors.toSet());
                  Set<Integer> cardNumbers =
                      Arrays.stream(game[1].split(" "))
                          .filter(x -> !x.isEmpty())
                          .map(Integer::parseInt)
                          .collect(Collectors.toSet());
                  return cardNumbers.stream().filter(winningNumbers::contains).count();
                }));
  }
}

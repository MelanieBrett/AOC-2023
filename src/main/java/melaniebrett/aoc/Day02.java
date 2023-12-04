package melaniebrett.aoc;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import melaniebrett.aoc.models.Bag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day02 {
  private static final String DAY = "02";
  private static final Logger logger = LoggerFactory.getLogger(Day02.class);
  private static final Path inputPath = new Utils().loadFileAsPath(String.format("Day%s.txt", DAY));

  public static void solution() {
    List<String> inputs = Utils.getInputLines(inputPath);

    logger.info("Day " + DAY);
    logger.info("Part 1: {}", part1(inputs));
    //    Part 1: 2101
    logger.info("Part 2: {}", part2(inputs));
    //    Part 2: 58269
  }

  public static int part1(List<String> inputs) {
    Bag maxBag = new Bag(14, 12, 13);

    Map<Integer, List<Bag>> gamesToBags = parseGamesToBags(inputs);

    return gamesToBags.entrySet().stream()
        .filter(e -> e.getValue().stream().allMatch(maxBag::contains))
        .map(Entry::getKey)
        .reduce(Integer::sum)
        .orElse(0);
  }

  public static int part2(List<String> inputs) {
    Map<Integer, List<Bag>> gamesToBags = parseGamesToBags(inputs);

    Bag emptyBag = new Bag(0, 0, 0);

    return gamesToBags.values().stream()
        .map(bags -> bags.stream().reduce(emptyBag, Bag::increaseBag))
        .map(Bag::power)
        .reduce(Integer::sum)
        .orElse(0);
  }

  private static Map<Integer, List<Bag>> parseGamesToBags(List<String> inputs) {
    return inputs.stream()
        .collect(
            Collectors.toMap(
                k -> Integer.parseInt(k.split(":")[0].replaceAll("Game ", "")),
                v -> Stream.of(v.split(":")[1].split(";")).map(Bag::countAll).toList()));
  }
}

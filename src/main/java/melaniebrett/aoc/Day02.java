package melaniebrett.aoc;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import melaniebrett.aoc.models.Bag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day02 {
  static final Logger logger = LoggerFactory.getLogger(Day02.class);
  static final Path inputPath = new Utils().loadFileAsPath("Day02.txt");
  static Pattern gamePattern = Pattern.compile("(\\d+)(?! game)", Pattern.CASE_INSENSITIVE);

  public static void day02() {
    List<String> inputs = Utils.getInputLines(inputPath);

    logger.info("Day 02");
    logger.info("Part 1: {}", part1(inputs));
    //    Part 1: 54697
    logger.info("Part 2: {}", part2(inputs));
    //    Part 2: 54885
  }

  public static int part1(List<String> inputs) {
    Bag maxBag = new Bag(14, 12, 13);

    //    split inputs into game and rounds
    List<String[]> y = inputs.stream().map(x -> x.split(";")).toList();

    // for each round, parse bag and asser true
    return y.stream()
        .map(
            game -> {
              int gameCount =
                  gamePattern
                      .matcher(game[0])
                      .results()
                      .map(MatchResult::group)
                      .map(Integer::parseInt)
                      .findFirst()
                      .orElse(0);

              boolean f =
                  Arrays.stream(game).map(Bag::countAll).allMatch(bag -> maxBag.contains(bag));

              return f ? gameCount : 0;
            })
        .reduce(Integer::sum)
        .orElse(0);
  }

  public static int part2(List<String> inputs) {
    return 0;
  }
}

package melaniebrett.aoc;

import java.nio.file.Path;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day00 {
  private static final String DAY = "00";
  private static final Logger logger = LoggerFactory.getLogger(Day00.class);
  private static final Path inputPath = new Utils().loadFileAsPath(String.format("Day%s.txt", DAY));

  public static void solution() {
    List<String> inputs = Utils.getInputLines(inputPath);

    logger.info("Day " + DAY);
    logger.info("Part 1: {}", part1(inputs));
    //    Part 1:
    logger.info("Part 2: {}", part2(inputs));
    //    Part 2:
  }

  public static int part1(List<String> inputs) {
    return 0;
  }

  public static int part2(List<String> inputs) {
    return 0;
  }
}

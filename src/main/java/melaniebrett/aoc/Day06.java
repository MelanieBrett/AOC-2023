package melaniebrett.aoc;

import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day06 {
  private static final String DAY = "06";
  private static final Logger logger = LoggerFactory.getLogger(Day06.class);
  private static final Path inputPath = new Utils().loadFileAsPath(String.format("Day%s.txt", DAY));

  public static void solution() {
    List<String> inputs = Utils.getInputLines(inputPath);

    logger.info("Day " + DAY);
    logger.info("Part 1: {}", part1(inputs));
    //    Part 1: 293046
    logger.info("Part 2: {}", part2(inputs));
    //    Part 2: 35150181
  }

  public static int part1(List<String> inputs) {
    Map<Integer, Integer> timeDistanceMap = getTimeToDistance(inputs);
    return timeDistanceMap.entrySet().stream()
        .map(e -> getWinningPossibilities(e.getKey(), e.getValue()))
        .reduce(1, (a, b) -> a * b);
  }

  public static String part2(List<String> inputs) {
    double time = Double.parseDouble(inputs.get(0).replaceAll("Time:", "").replaceAll(" ", ""));
    double distance =
        Double.parseDouble(inputs.get(1).replaceAll("Distance:", "").replaceAll(" ", ""));

    /*
        s = speed
        race time RT = T - s
        distance = s * RT = s(T - s) = sT - s^2
        0 = -s^2 + sT - D
    */
    double range = solveQuadraticRange(-1, time, -distance);

    DecimalFormat df = new DecimalFormat("#");
    df.setMaximumFractionDigits(8);
    return df.format(range);
  }

  private static Map<Integer, Integer> getTimeToDistance(List<String> inputs) {
    List<Integer> time =
        Arrays.stream(inputs.get(0).replaceAll("Time:", "").split(" "))
            .filter(x -> !x.isEmpty())
            .map(Integer::parseInt)
            .toList();
    List<Integer> distance =
        Arrays.stream(inputs.get(1).replaceAll("Distance:", "").split(" "))
            .filter(x -> !x.isEmpty())
            .map(Integer::parseInt)
            .toList();

    return IntStream.range(0, time.size())
        .boxed()
        .collect(Collectors.toMap(time::get, distance::get));
  }

  private static Integer getWinningPossibilities(Integer time, Integer record) {
    Integer possibilities = 0;
    for (int speed = 0; speed < time; speed++) {
      int distance = speed * (time - speed);
      if (distance > record) {
        possibilities++;
      }
    }
    return possibilities;
  }

  private static double solveQuadraticRange(double a, double b, double c) {
    double result = b * b - 4.0 * a * c;
    double r1 = (-b + Math.pow(result, 0.5)) / (2.0 * a);
    double r2 = (-b - Math.pow(result, 0.5)) / (2.0 * a);

    return Math.ceil(r2) - Math.ceil(r1);
  }
}

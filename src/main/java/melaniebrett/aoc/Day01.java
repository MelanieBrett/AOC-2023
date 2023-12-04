package melaniebrett.aoc;

import java.nio.file.Path;
import java.util.List;
import melaniebrett.aoc.models.Numbers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day01 {
  static final Logger logger = LoggerFactory.getLogger(Day01.class);
  static final Path inputPath = new Utils().loadFileAsPath("Day01.txt");

  public static void solution() {
    List<String> inputs = Utils.getInputLines(inputPath);

    logger.info("Day 01");
    logger.info("Part 1: {}", part1(inputs));
    //    Part 1: 54697
    logger.info("Part 2: {}", part2(inputs));
    //    Part 2: 54885
  }

  public static int part1(List<String> inputs) {
    return inputs.stream().map(Day01::parseFirstAndLastDigit).reduce(Integer::sum).orElse(0);
  }

  public static int part2(List<String> inputs) {
    return inputs.stream()
        .map(Day01::parseStringDigitsInOrder)
        .map(Day01::parseFirstAndLastDigit)
        .reduce(Integer::sum)
        .orElse(0);
  }

  private static int parseFirstAndLastDigit(String input) {
    List<Character> digits =
        input.chars().mapToObj(ch -> (char) ch).filter(Character::isDigit).toList();

    String first = String.valueOf(digits.get(0));
    String last = String.valueOf(digits.get(digits.size() - 1));
    int joined = Integer.parseInt(first + last);

    logger.debug("input: {}, first: {}, last: {}, joined: {}", input, first, last, joined);

    return joined;
  }

  private static String parseStringDigitsInOrder(String input) {
    int left = 0;
    int right = 1;

    if (input.length() <= 1) {
      return input;
    }

    StringBuilder output = new StringBuilder();
    String temp = "";

    while (right <= input.length()) {
      String window = input.substring(left, right);
      temp = Numbers.replaceValues(window);

      if (window.equals(temp)) {
        right++;
      } else {
        output.append(temp);
        temp = "";
        left = right - 1;
        right++;
      }
    }
    output.append(temp);

    return output.toString();
  }
}

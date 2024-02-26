package melaniebrett.aoc;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

class Day06Test {
  final List<String> input = Utils.getInputLines(Path.of("src/test/resources/Day06.txt"));

  @Test
  public void part1() {
    int answer = Day06.part1(input);

    assertEquals(288, answer);
  }

  @Test
  public void part2() {
    double answer = Double.parseDouble(Day06.part2(input));

    assertEquals(71503, answer);
  }
}

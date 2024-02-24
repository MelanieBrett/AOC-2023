package melaniebrett.aoc;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

class Day07Test {
  final List<String> input = Utils.getInputLines(Path.of("src/test/resources/Day07.txt"));

  @Test
  public void part1() {
    double answer = Day07.part1(input);

    assertEquals(6440L, answer);
  }

  @Test
  public void part2() {
    int answer = Day00.part2(input);

    assertEquals(0, answer);
  }
}

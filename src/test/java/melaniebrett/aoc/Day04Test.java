package melaniebrett.aoc;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

class Day04Test {
  final List<String> input = Utils.getInputLines(Path.of("src/test/resources/Day04.txt"));

  @Test
  public void part1() {
    int answer = Day04.part1(input);

    assertEquals(13, answer);
  }

  @Test
  public void part2() {
    int answer = Day04.part2(input);

    assertEquals(30, answer);
  }
}

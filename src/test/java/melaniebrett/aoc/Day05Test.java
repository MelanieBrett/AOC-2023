package melaniebrett.aoc;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

class Day05Test {
  final List<String> input = Utils.getInputLines(Path.of("src/test/resources/Day05.txt"));
  Day05 day = new Day05();

  @Test
  public void part1_test() {
    Double answer = day.part1(input);

    assertEquals(35, answer);
  }

  @Test
  public void part2_test() {
    int answer = day.part2(input);

    assertEquals(0, answer);
  }
}

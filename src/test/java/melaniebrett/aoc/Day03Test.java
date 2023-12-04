package melaniebrett.aoc;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

class Day03Test {

  @Test
  public void day03_part1() {
    List<String> input = Utils.getInputLines(Path.of("src/test/resources/Day03_part1.txt"));

    int answer = Day03.part1(input);

    assertEquals(4361, answer);
  }

  @Test
  public void day03_part2() {
    List<String> input = Utils.getInputLines(Path.of("src/test/resources/Day03_part1.txt"));

    int answer = Day03.part2(input);

    assertEquals(467835, answer);
  }
}

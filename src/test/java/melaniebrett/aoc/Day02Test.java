package melaniebrett.aoc;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

class Day02Test {

  @Test
  public void day02_part1() {
    List<String> input = Utils.getInputLines(Path.of("src/test/resources/Day02_part1.txt"));

    int answer = Day02.part1(input);

    assertEquals(8, answer);
  }

  //  @Test
  //  public void day01_part2() {
  //    List<String> input = Utils.getInputLines(Path.of("src/test/resources/Day01_part2.txt"));
  //
  //    int answer = Day01.part2(input);
  //
  //    assertEquals(281, answer);
  //  }

}

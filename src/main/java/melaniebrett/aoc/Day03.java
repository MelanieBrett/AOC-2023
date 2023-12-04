package melaniebrett.aoc;

import static java.lang.Character.isDigit;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import melaniebrett.aoc.models.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day03 {
  static final Logger logger = LoggerFactory.getLogger(Day03.class);
  static final Path inputPath = new Utils().loadFileAsPath("Day03.txt");

  public static void solution() {
    List<String> inputs = Utils.getInputLines(inputPath);

    logger.info("Day 03");
    logger.info("Part 1: {}", part1(inputs));
    //    Part 1: 559667
    logger.info("Part 2: {}", part2(inputs));
    //    Part 2: 86841457
  }

  public static int part1(List<String> inputs) {
    PartMap partMap = parseInputToMap(inputs);

    List<String> partNumbers =
        partMap.symbols.keySet().stream()
            .flatMap(symbol -> findSurroundingParts(symbol, partMap.digits).stream())
            .toList();

    return partNumbers.stream().map(Integer::parseInt).reduce(0, Integer::sum);
  }

  public static int part2(List<String> inputs) {
    PartMap partMap = parseInputToMap(inputs);
    return partMap.symbols.entrySet().stream()
        .filter(entry -> entry.getValue() == '*')
        .map(Map.Entry::getKey)
        .map(symbol -> findSurroundingParts(symbol, partMap.digits))
        .filter(part -> part.size() == 2)
        .map(part -> part.stream().map(Integer::parseInt).reduce(1, (a, b) -> a * b))
        .reduce(0, Integer::sum);
  }

  private static List<String> findSurroundingParts(
      Coordinate centre, Map<Coordinate, Integer> digits) {
    Set<Coordinate> surroundingPartCoordinates = new HashSet<>();
    surroundingPartCoordinates.add(centre);
    List<String> surroundingParts = new ArrayList<>();

    for (int r = centre.row() - 1; r <= centre.row() + 1; r++) {
      for (int c = centre.col() - 1; c <= centre.col() + 1; c++) {
        Coordinate current = new Coordinate(r, c);
        if (digits.containsKey(current) && !surroundingPartCoordinates.contains(current)) {
          PartNumber part = findPartNumber(current, digits);
          surroundingPartCoordinates.addAll(part.location());
          surroundingParts.add(part.partNumber());
        }
      }
    }
    return surroundingParts;
  }

  private static PartNumber findPartNumber(Coordinate start, Map<Coordinate, Integer> digits) {
    Set<Coordinate> location = new HashSet<>();
    StringBuilder partNumber = new StringBuilder();
    Coordinate current = start;

    while (digits.containsKey(current)) {
      partNumber.append(digits.get(current));
      location.add(current);
      current = current.addColumn();
    }

    current = start.subtractColumn();
    while (digits.containsKey(current)) {
      partNumber.insert(0, digits.get(current));
      location.add(current);
      current = current.subtractColumn();
    }

    return new PartNumber(partNumber.toString(), location);
  }

  private static PartMap parseInputToMap(List<String> inputs) {
    int totalRows = inputs.size();
    int totalCols = inputs.get(0).length();
    Map<Coordinate, Integer> digits = new HashMap<>();
    Map<Coordinate, Character> symbols = new HashMap<>();

    for (int row = 0; row < totalRows; row++) {
      for (int col = 0; col < totalCols; col++) {
        char current = inputs.get(row).charAt(col);
        if (current != '.') {
          if (isDigit(current)) {
            digits.put(new Coordinate(row, col), Character.getNumericValue(current));
          } else {
            symbols.put(new Coordinate(row, col), current);
          }
        }
      }
    }
    return new PartMap(symbols, digits);
  }

  private record PartNumber(String partNumber, Set<Coordinate> location) {}

  private record PartMap(Map<Coordinate, Character> symbols, Map<Coordinate, Integer> digits) {}
}

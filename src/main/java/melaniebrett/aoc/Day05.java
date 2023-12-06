package melaniebrett.aoc;

import static java.util.stream.Collectors.toMap;

import com.google.common.collect.Range;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day05 {
  private static final String DAY = "05";
  private static final Logger logger = LoggerFactory.getLogger(Day05.class);
  private static final Path inputPath = new Utils().loadFileAsPath(String.format("Day%s.txt", DAY));

  public void solution() {
    List<String> inputs = Utils.getInputLines(inputPath);

    logger.info("Day " + DAY);
    logger.info("Part 1: {}", String.format("%f", this.part1(inputs)));
    //    Part 1: 173706076
    logger.info("Part 2: {}", this.part2(inputs));
    //    Part 2:
  }

  public Double part1(List<String> inputs) {
    String input = String.join("\n", inputs);

    List<Double> seeds =
        Stream.of(inputs.get(0).replaceAll("seeds: ", "").split(" "))
            .map(Double::parseDouble)
            .toList();

    Map<Range<Double>, Double> seedToSoil = getMapInput("seed-to-soil", input);
    Map<Range<Double>, Double> soilToFertilizer = getMapInput("soil-to-fertilizer", input);
    Map<Range<Double>, Double> fertilizerToWater = getMapInput("fertilizer-to-water", input);
    Map<Range<Double>, Double> waterToLight = getMapInput("water-to-light", input);
    Map<Range<Double>, Double> lightToTemperature = getMapInput("light-to-temperature", input);
    Map<Range<Double>, Double> temperatureToHumidity =
        getMapInput("temperature-to-humidity", input);
    Map<Range<Double>, Double> humidityToLocation = getMapInput("humidity-to-location", input);

    return seeds.stream()
        .map(seed -> getDestinationFromSource(seed, seedToSoil))
        .map(soil -> getDestinationFromSource(soil, soilToFertilizer))
        .map(fertilizer -> getDestinationFromSource(fertilizer, fertilizerToWater))
        .map(water -> getDestinationFromSource(water, waterToLight))
        .map(light -> getDestinationFromSource(light, lightToTemperature))
        .map(temperature -> getDestinationFromSource(temperature, temperatureToHumidity))
        .map(humidity -> getDestinationFromSource(humidity, humidityToLocation))
        .mapToDouble(l -> l)
        .min()
        .orElse(0);
  }

  public int part2(List<String> inputs) {
    return 0;
  }

  private static Map<Range<Double>, Double> getMapInput(String mapType, String input) {
    Pattern mapPattern =
        Pattern.compile(
            String.format("(?s)(?<=%s map:\\n).*?(?=\\n\\n|\\Z)", mapType), Pattern.DOTALL);

    return mapPattern
        .matcher(input)
        .results()
        .map(MatchResult::group)
        .flatMap(x -> getSourceRangeToDestination(List.of(x.split("\n"))).entrySet().stream())
        .collect(toMap(Entry::getKey, Entry::getValue));
  }

  private static Map<Range<Double>, Double> getSourceRangeToDestination(List<String> inputs) {
    return inputs.stream()
        .collect(
            Collectors.toMap(
                entry -> {
                  String[] entries = entry.split(" ");
                  double source = Double.parseDouble(entries[1]);
                  double range = Double.parseDouble(entries[2]);
                  return Range.closed(source, source + range);
                },
                entry -> {
                  String[] entries = entry.split(" ");
                  return Double.parseDouble(entries[0]);
                }));
  }

  private static Double getDestinationFromSource(
      Double source, Map<Range<Double>, Double> RangeMap) {
    return RangeMap.entrySet().stream()
        .filter(entry -> entry.getKey().contains(source))
        .map(
            entry -> {
              Double start = entry.getKey().lowerEndpoint();
              Double placeInRange = source - start;
              return entry.getValue() + placeInRange;
            })
        .findFirst()
        .orElse(source);
  }
}

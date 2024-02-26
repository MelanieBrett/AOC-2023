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
  List<String> inputs;
  private final Map<Range<Double>, Double> seedToSoil;
  private final Map<Range<Double>, Double> soilToFertilizer;
  private final Map<Range<Double>, Double> fertilizerToWater;
  private final Map<Range<Double>, Double> waterToLight;
  private final Map<Range<Double>, Double> lightToTemperature;
  private final Map<Range<Double>, Double> temperatureToHumidity;
  private final Map<Range<Double>, Double> humidityToLocation;

  public Day05() {
    this.inputs = Utils.getInputLines(inputPath);
    String input = String.join("\n", inputs);
    this.seedToSoil = getMapInput("seed-to-soil", input);
    this.soilToFertilizer = getMapInput("soil-to-fertilizer", input);
    this.fertilizerToWater = getMapInput("fertilizer-to-water", input);
    this.waterToLight = getMapInput("water-to-light", input);
    this.lightToTemperature = getMapInput("light-to-temperature", input);
    this.temperatureToHumidity = getMapInput("temperature-to-humidity", input);
    this.humidityToLocation = getMapInput("humidity-to-location", input);
  }

  public void solution() {

    logger.info("Day " + DAY);
    logger.info("Part 1: {}", String.format("%f", part1()));
    //    Part 1: 173706076
    logger.info("Part 2: {}", part2(inputs));
    //    Part 2:
  }

  public Double part1() {
    List<Double> seeds =
        Stream.of(inputs.get(0).replaceAll("seeds: ", "").split(" "))
            .map(Double::parseDouble)
            .toList();

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

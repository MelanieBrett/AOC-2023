package melaniebrett.aoc;

import static java.util.stream.Collectors.toMap;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import melaniebrett.aoc.models.DoublePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day05 {
  private static final String DAY = "05";
  private static final Logger logger = LoggerFactory.getLogger(Day05.class);
  private static final Path inputPath = new Utils().loadFileAsPath(String.format("Day%s.txt", DAY));

  public void solution() {
    List<String> inputs = Utils.getInputLines(inputPath);

    logger.info("Day " + DAY);
    logger.info("Part 1: {}", this.part1(inputs));
    //    Part 1:
    logger.info("Part 2: {}", this.part2(inputs));
    //    Part 2:
  }

  public Double part1(List<String> inputs) {
    String input = String.join("\n", inputs);

    List<Double> seeds =
        Stream.of(inputs.get(0).replaceAll("seeds: ", "").split(" "))
            .map(Double::parseDouble)
            .toList();

    Map<Double, Double> seedToSoil = getMapInput("seed-to-soil", input);
    Map<Double, Double> soilToFertilizer = getMapInput("soil-to-fertilizer", input);
    Map<Double, Double> fertilizerToWater = getMapInput("fertilizer-to-water", input);
    Map<Double, Double> waterToLight = getMapInput("water-to-light", input);
    Map<Double, Double> lightToTemperature = getMapInput("light-to-temperature", input);
    Map<Double, Double> temperatureToHumidity = getMapInput("temperature-to-humidity", input);
    Map<Double, Double> humidityToLocation = getMapInput("humidity-to-location", input);

    return seeds.stream()
        .map(seed -> seedToSoil.getOrDefault(seed, seed))
        .map(soil -> soilToFertilizer.getOrDefault(soil, soil))
        .map(fertilizer -> fertilizerToWater.getOrDefault(fertilizer, fertilizer))
        .map(water -> waterToLight.getOrDefault(water, water))
        .map(light -> lightToTemperature.getOrDefault(light, light))
        .map(temperature -> temperatureToHumidity.getOrDefault(temperature, temperature))
        .map(humidity -> humidityToLocation.getOrDefault(humidity, humidity))
        .mapToDouble(l -> l)
        //        .mapToInt(l -> l)
        .min()
        .orElse(0);
  }

  public int part2(List<String> inputs) {
    return 0;
  }

  private static Map<Double, Double> getMapInput(String mapType, String input) {
    Pattern mapPattern =
        Pattern.compile(String.format("(?s)(?<=%s map:\\n).*?(?=\\n\\n)", mapType), Pattern.DOTALL);

    return mapPattern
        .matcher(input)
        .results()
        .map(MatchResult::group)
        .flatMap(x -> getSourceToDestinationMap(List.of(x.split("\n"))).entrySet().stream())
        .collect(toMap(Entry::getKey, Entry::getValue));
  }

  private static Map<Double, Double> getSourceToDestinationMap(List<String> inputs) {
    return inputs.stream()
        .flatMap(
            entry -> {
              String[] entries = entry.split(" ");
              double destination = Double.parseDouble(entries[0]);
              double source = Double.parseDouble(entries[1]);
              double range = Double.parseDouble(entries[2]);
              List<DoublePair> pairs = new ArrayList<>();
              for (int i = 0; i < range; i++) {
                pairs.add(new DoublePair(source + i, destination + i));
              }
              return pairs.stream();
            })
        .collect(toMap(DoublePair::first, DoublePair::second));
  }
  //  private static Integer getDestination(Integer source, Map<Integer, Integer> map) {
  //    return map.getOrDefault(source, source);
  //  }
}

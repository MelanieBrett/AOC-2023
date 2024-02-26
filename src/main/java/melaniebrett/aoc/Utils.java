package melaniebrett.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;

public class Utils {

  public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#");

  public Path loadFileAsPath(final String fileName) {
    ClassLoader classLoader = getClass().getClassLoader();
    java.net.URL resource = classLoader.getResource(fileName);
    if (resource == null) {
      throw new IllegalArgumentException("File not found!");
    } else {
      try {
        // Convert URL to a Path
        return Paths.get(resource.toURI());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static List<String> getInputLines(final Path inputPath) {
    try {
      return Files.readAllLines(inputPath);
    } catch (IOException e) {
      return List.of();
    }
  }
}

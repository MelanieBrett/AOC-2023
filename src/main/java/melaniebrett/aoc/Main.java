package melaniebrett.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  private static final String adventOfCodePath = "src/main/resources/AdventOfCode.txt";

  public static void main(String[] args) {
    welcome();

    Day01.solution();
    Day02.solution();
    Day03.solution();
    Day04.solution();
    new Day05().solution();
    Day06.solution();
    Day07.solution();
  }

  private static void welcome() {
    System.out.println("Hello and welcome to Advent of Code 2023!");

    try (BufferedReader br = new BufferedReader(new FileReader(adventOfCodePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

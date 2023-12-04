package melaniebrett.aoc.models;

public record Coordinate(int row, int col) {

  public Coordinate add(Coordinate other) {
    return new Coordinate(row + other.row(), col + other.col());
  }

  public Coordinate addColumn() {
    return new Coordinate(row, col + 1);
  }

  public Coordinate subtractColumn() {
    return new Coordinate(row, col - 1);
  }
}

package knightsTour;

import knightsTour.Coord;

/**
 * Represents the physical chessboard. Must be a square board. In terms of x-y coordinates, (0,0) is
 * the bottom left point.
 * 
 * @author Ryan Godfrey, Will Graham
 *
 */
public class Board {
  private final int SIZE;
  private int[][] resultBoard; // Actual board, holds 0 or step number in tour
  private int steps; // Current step in the tour

  /**
   * Constructor. Sets size and steps, and contents of board to all zeros.
   * 
   * @param size The size of each side of the board
   */
  public Board(int size) {
    this.SIZE = size;
    resultBoard = new int[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) // Fill board with 0s
      for (int j = 0; j < SIZE; j++)
        resultBoard[i][j] = 0;
    steps = 0;
  }

  /**
   * Size getter.
   * 
   * @return The size of one side of the square board
   */
  public int getSize() {
    return SIZE;
  }

  /**
   * Getter for the number of steps of the tour taken.
   * 
   * @return The number of steps of the tour taken
   */
  public int getSteps() {
    return steps;
  }

  /**
   * Getter for the step number of a specific x-y coordinate on the board.
   * 
   * @param x Row of step to get
   * @param y Column of step to get
   * @return The step of the specified point
   */
  public int getStepOnBoard(int x, int y) {
    return resultBoard[x][y];
  }

  /**
   * Simulates placing the Knight at a coord, putting the step number in the resultBoard.
   * 
   * @param c The coord to place the Knight at
   */
  public void placeKnight(Coord c) {
    steps++;
    resultBoard[c.getX()][c.getY()] = steps;
  }

  /**
   * Formats a String of the board with appropriate rows and columns, with step number for each
   * tile. Ensures each printed number has an equal number of digits, so a nice square will be
   * returned.
   * 
   * @return String representation of the board
   */
  public String toString() {
    String result = "";
    String format = "%0" + String.valueOf(steps).length() + "d";
    for (int row = SIZE - 1; row >= 0; row--) {
      for (int col = 0; col < SIZE; col++) {
        result += String.format(format, resultBoard[col][row]) + " ";
      }
      result += "\n";
    }
    return result;
  }

  /**
   * Checks to see if the passed coord is a valid representation of a space on the board.
   * 
   * @param c The coord to check
   * @return Whether the coord is inside the board's coordinate system or not
   */
  public boolean isInBoard(Coord c) {
    return c.getX() >= 0 && c.getY() >= 0 && c.getX() < SIZE && c.getY() < SIZE;
  }

  /**
   * Checks if the passed coord has been visited on the tour already.
   * 
   * @param c The coord to be checked
   * @return Whether the coord has been visted already or not
   */
  public boolean isVisited(Coord c) {
    return resultBoard[c.getX()][c.getY()] != 0;
  }

  /**
   * Finds the number of moves on the board that a Knight at a given position can make and not
   * revisit a previously visited tile.
   * 
   * @param knight The knight to be moved
   * @param c the coord the Knight currently occupies
   * @return the number of valid moves the Knight at the coord can make
   */
  public int numberOfExits(Knight knight, Coord c) {
    int result = 0;
    for (int i = 0; i < knight.getMoves().length; i++) {
      Coord next = c.moveBy(knight.getMoves()[i]);
      if (isInBoard(next) && !isVisited(next))
        result++;
    }
    return result;
  }

}


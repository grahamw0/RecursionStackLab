/**
 * 
 */
package knightsTour;

import knightsTour.Coord;

/**
 * @author grahamw0
 *
 */
public class Board {
  private final int SIZE;
  private int[][] resultBoard;
  private int steps;

  public Board(int BOARD_SIZE) {
    SIZE = BOARD_SIZE;
    resultBoard = new int[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) // superfluous
      for (int j = 0; j < SIZE; j++)
        resultBoard[i][j] = 0;
    steps = 0;
  }

  public int getSize() {
    return SIZE;
  }
  
  public int getVisitedPoints() {
    return steps;
  }

  public int getStepOnBoard(int x, int y) {
    return resultBoard[x][y];
  }

  public void placeKnight(Coord c) // change board information as knight moves
  {
    steps++;
    resultBoard[c.getX()][c.getY()] = steps;
  }// method placeKnight

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
  
  public boolean isInBoard(Coord c) {
    return c.getX() >= 0 && c.getY() >= 0 && c.getX() < SIZE && c.getY() < SIZE;
  }
  
  public boolean isVisited(Coord c) {
    return resultBoard[c.getX()][c.getY()] != 0;
  }
  
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


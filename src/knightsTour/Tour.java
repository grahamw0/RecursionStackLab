package knightsTour;

/**
 * Actually runs the Knight's Tour, and handles the logic of movement and potentially failed Tours.
 * 
 * @author Ryan Godfrey, Will Graham
 *
 */
public class Tour {
  /**
   * Constructor and logic driver.
   * 
   * @param boardSize Size of each side of the square board
   * @param xStart The x-coordinate the Knight will start on
   * @param yStart The y-coordinate the Knight will start on
   */
  public Tour(int boardSize, int xStart, int yStart) {
    if (boardSize <= 4) // No Tours are possible on a board less than size 5
      System.out.println("No Knight's Tour possible.");
    else {
      Board board = new Board(boardSize);
      Coord startLocation = new Coord(xStart, yStart);
      Knight knight = new Knight(startLocation, board);

      while (knight.canMove()) // As long as it can, move the Knight
        knight.move();

      // If the Knight can't move, but the number of steps it took isn't the # of board tiles
      if (board.getSteps() != boardSize * boardSize) {
        new Tour(boardSize, xStart, yStart); // Recursively find another path
      } else {
        System.out.println(board);
      }
    }
  }

}

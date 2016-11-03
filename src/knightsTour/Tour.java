package knightsTour;

/**
 * Actually runs the Knight's Tour, and handles the logic of movement and potentially failed Tours.
 * 
 * @author Ryan Godfrey, Will Graham
 *
 */
public class Tour {
  private int boardSize;
  private int xStart;
  private int yStart;
  private Board board;

  /**
   * Constructor- runs the moving algorithm until a tour is found or no tour is possible.
   * 
   * @param boardSize Size of each side of the square board
   * @param xStart The x-coordinate the Knight will start on
   * @param yStart The y-coordinate the Knight will start on
   */
  public Tour(int boardSize, int xStart, int yStart) {
    this.boardSize = boardSize;
    this.xStart = xStart;
    this.yStart = yStart;
    boolean done = false;

    if (noTourPossible()) {
      System.out.println("No Knight's Tour possible.");
    } else {
      while (!done) {
        done = run();
      }
      System.out.println(board);
    }
  }

  /**
   * The logical driver for finding the tour, to be used iteratively.
   * 
   * @return whether this iteration was able to find a Knight's Tour
   */
  private boolean run() {
    board = new Board(boardSize);
    Coord startLocation = new Coord(xStart, yStart);
    Knight knight = new Knight(startLocation, board);

    while (knight.canMove()) // As long as it can, move the Knight
      knight.move();

    // If the Knight can't move, but the number of steps it took isn't the # of board tiles
    if (board.getSteps() != boardSize * boardSize) {
      return false;
    } else {
      return true;
    }

  }

  /**
   * Checks for failure conditions based on starting position and board size. If the starting
   * position is adjacent to a corner, there is no open tour possible. Additionally, for whatever
   * reason, if the absolute value of (board size - (x starting position + y starting position) is
   * even, there seems to be no tour possible.
   * 
   * @return whether a tour is possible or not
   */
  private boolean noTourPossible() {
    boolean problem = false;
    if (boardSize <= 4)
      problem = true;
    if (boardSize % 2 != 0) { // These all could have been one return statement. That's ugly.
      if (xStart == 1 && yStart == 0) // Right of bottom left
        problem = true;
      else if (xStart == 0 && yStart == 1) // Top of bottom left
        problem = true;
      else if (xStart == 0 && yStart == (boardSize - 2)) // Bottom of top left
        problem = true;
      else if (xStart == 1 && yStart == (boardSize - 1)) // Right of top left
        problem = true;
      else if (xStart == (boardSize - 2) && yStart == 0) // Left of bottom right
        problem = true;
      else if (xStart == (boardSize - 1) && yStart == 1) // Top of bottom right
        problem = true;
      else if (xStart == (boardSize - 2) && yStart == (boardSize - 1)) // Left of top right
        problem = true;
      else if (xStart == (boardSize - 1) && yStart == (boardSize - 2)) // Bottom of top right
        problem = true;
      else {
        int num = Math.abs(boardSize - (xStart + yStart));
        if (num == 0 || (num % 2 == 0 && num != 1))
          problem = true;
      }
    }

    return problem;
  }

}

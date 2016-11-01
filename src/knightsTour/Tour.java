/**
 * 
 */
package knightsTour;

/**
 * @author grahamw0
 *
 */
public class Tour {
  public Tour(int boardSize, int xStart, int yStart) {
    if (boardSize <= 4)
      System.out.println("No Knight's Tour possible.");
    else {

      Board board = new Board(boardSize);
      Coord startLocation = new Coord(xStart, yStart);
      Knight knight = new Knight(startLocation, board);

      while (knight.canMove(board))
        knight.move(board);

      if (board.getVisitedPoints() != boardSize * boardSize) {
        System.out.println("RECURSING"); //TODO: Remove test statement
        new Tour(boardSize, xStart, yStart);
      } else {
        System.out.println(board);
      }
    }
  }

  public static boolean run(int boardSize, int xStart, int yStart) {
    Board board = new Board(boardSize);
    Coord startLocation = new Coord(xStart, yStart);
    Knight knight = new Knight(startLocation, board);

    while (knight.canMove(board))
      knight.move(board);

    if (board.getVisitedPoints() != boardSize * boardSize) {
      // System.out.println("No Knight's Tour possible.");
      return false;
    } else {
      // System.out.println(board);
      return true;
    }
  }

}

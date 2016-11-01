package knightsTour;

/**
 * The actual Knight of the Knight's Tour. Contains valid moves, exit calculation methods and move
 * logic.
 * 
 * @author Ryan Godfrey, Will Graham
 */
public class Knight {
  private final int[][] MOVES = // Ordered pairs of valid moves
      {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
  private Coord position; // Current position on board

  /**
   * Constructor.
   * 
   * @param location Initial location on board
   * @param board Board to be placed on
   */
  public Knight(Coord location, Board board) {
    moveTo(location, board);
  }

  /**
   * Getter for Knight's valid moves.
   * 
   * @return Knight's valid moves
   */
  public int[][] getMoves() {
    return MOVES;
  }

  /**
   * Moves the knight around the board.
   * 
   * @param location Location to move to
   * @param board The board to move upon
   */
  private void moveTo(Coord location, Board board) {
    this.position = new Coord(location); // Independent copy, no mem-ref link
    board.placeKnight(position);
  }

  /**
   * Finds if the Knight has any valid moves
   * 
   * @param board Board to calculate from
   * @return Whether the Knight has any valid moves or not
   */
  public boolean canMove(Board board) {
    return board.numberOfExits(this, position) > 0;
  }

  /**
   * Move the Knight to a position which has the least number of next moves, as described in
   * Warnsdorf's rule.
   * 
   * @param board The board on which to move
   */
  public void move(Board board) {
    int minExits = minNumberOfExits(board);
    int posMoves = numberOfHardPoints(minExits, board);
    int choice = (int) (posMoves * Math.random() + 1);
    Coord nextMove = newLocation(choice, board, minExits);
    moveTo(nextMove, board);
  }

  // Minimum number of exits
  private int minNumberOfExits(Board board) {
    // Larger than any possible # of exits, ensures a valid # is stored from min()
    int exits = MOVES.length + 1;
    for (int i = 0; i < MOVES.length; i++) {
      Coord next = position.moveBy(getMoves()[i]);
      if (board.isInBoard(next)/* next.isInBoard(board) */ && !board.isVisited(next)) {
        exits =
            Math.min(exits, board.numberOfExits(this, next)/* next.numberOfExits(this, board) */);
      }
    }
    return exits;
  }

  // Number of exits with the aformentioned number of minimum exits
  private int numberOfHardPoints(int exits, Board board) {
    int number = 0;
    for (int i = 0; i < MOVES.length; i++) {
      Coord next = position.moveBy(getMoves()[i]);
      if (board.isInBoard(next) && !board.isVisited(next)
          && /* next.numberOfExits(this, board) */board.numberOfExits(this, next) == exits) {
        number++;
      }
    }
    return number;
  }

  private Coord newLocation(int which, Board board, int exits) {
    int number = 0;
    int i = 0;
    Coord next = new Coord();

    while (number < which) {
      next = position.moveBy(getMoves()[i]);
      if (board.isInBoard(next) && !board.isVisited(next) &&
          /* next.numberOfExits(this, board) */board.numberOfExits(this, next) == exits) {
        number++;
      }
      i++;
    }
    return next;
  }

}

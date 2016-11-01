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
  private Board board;

  /**
   * Constructor.
   * 
   * @param location Initial location on board
   * @param board Board to be placed on
   */
  public Knight(Coord location, Board board) {
    this.board = board;
    moveTo(location);
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
   */
  private void moveTo(Coord location) {
    this.position = new Coord(location); // Independent copy, no mem-ref link
    board.placeKnight(position);
  }

  /**
   * Finds if the Knight has any valid moves
   * 
   * @return Whether the Knight has any valid moves or not
   */
  public boolean canMove() {
    return board.numberOfExits(this, position) > 0;
  }

  /**
   * Move the Knight to a position which has the least number of next moves, as described in
   * Warnsdorf's rule.
   */
  public void move() {
    int minExits = minNumberOfExits();
    int posMoves = numberOfHardPoints(minExits);
    int choice = (int) (posMoves * Math.random() + 1); // If multiple next moves, randomly choose
    Coord nextMove = newLocation(choice, minExits);
    moveTo(nextMove);
  }

  /**
   * Minimum number of exits for the current position
   * 
   * @return minimum number of exits for the current position
   */
  private int minNumberOfExits() {
    // Larger than any possible # of exits, ensures a valid # is stored from min()
    int exits = MOVES.length + 1;
    for (int i = 0; i < MOVES.length; i++) {
      Coord next = position.moveBy(getMoves()[i]);
      if (board.isInBoard(next) && !board.isVisited(next)) {
        exits = Math.min(exits, board.numberOfExits(this, next));
      }
    }
    return exits;
  }

  // Number of exits with the aformentioned number of minimum exits
  /**
   * Finds the number of points with the minimum number of of exits (implementing the
   * "move to position with lest number of exits" idea of Warnsdorf).
   * 
   * @param exits the minimum number of exits the next move can have
   * @return the number of exits that have only the minimum number of exits
   */
  private int numberOfHardPoints(int exits) {
    int number = 0;
    for (int i = 0; i < MOVES.length; i++) {
      Coord next = position.moveBy(getMoves()[i]);
      if (board.isInBoard(next) && !board.isVisited(next)
          && board.numberOfExits(this, next) == exits) {
        number++;
      }
    }
    return number;
  }

  /**
   * Determines the new location for the Knight based on Warnsdorff's rule
   * 
   * @param which The numbered exit to move to
   * @param exits The number of exits the new location must have
   * @return The coordinate of the new location to move to
   */
  private Coord newLocation(int which, int exits) {
    int number = 0;
    int i = 0;
    Coord next = new Coord();

    while (number < which) {
      next = position.moveBy(getMoves()[i]);
      if (board.isInBoard(next) && !board.isVisited(next)
          && board.numberOfExits(this, next) == exits) {
        number++;
      }
      i++;
    }
    return next;
  }

}

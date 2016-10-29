/**
 * 
 */
package knightsTour;

/**
 * @author grahamw0
 *
 */
public class Knight {
  private final int[][] MOVES = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2},
      {2, -1}};
  private Coord position;
  
  public Knight(Coord location, Board board) {
    moveTo(location, board);
  }
  
  public int[][] getMoves() {
    return MOVES;
  }
  
  private void moveTo(Coord location, Board board) {
    this.position = new Coord(location); // Independent copy, no mem-ref link
    board.placeKnight(position);
  }
  
  public boolean canMove(Board board) {
    return board.numberOfExits(this, position) > 0;/*position.numberOfExits(this, board) > 0;*/
  }
  
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
      if (board.isInBoard(next)/*next.isInBoard(board)*/ && !board.isVisited(next)) {
        exits = Math.min(exits, board.numberOfExits(this, next)/*next.numberOfExits(this, board)*/);
      }
    }
    return exits;
  }
  
  // Number of exits with the aformentioned number of minimum exits
  private int numberOfHardPoints(int exits, Board board) {
    int number = 0;
    for(int i = 0; i < MOVES.length; i++) {
      Coord next = position.moveBy(getMoves()[i]);
      if(board.isInBoard(next) && !board.isVisited(next)
          && /*next.numberOfExits(this, board)*/board.numberOfExits(this, next) == exits) {
        number++;
      }
    }
    return number;
  }
  
  private Coord newLocation(int which, Board board, int exits) {
    int number = 0;
    int i = 0;
    Coord next = new Coord();
    
    while(number < which) {
      next = position.moveBy(getMoves()[i]);
      if(board.isInBoard(next) && !board.isVisited(next) && 
          /*next.numberOfExits(this, board)*/board.numberOfExits(this, next) == exits) {
        number++;
      }
      i++;
    }
    return next;
  }
  
}

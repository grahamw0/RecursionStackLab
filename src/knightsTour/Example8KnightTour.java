/**
 * 
 */
package knightsTour;
// ************************************************************************
// Example8KnightTour.java
//
// We construct knight tours of chessboards using a greedy algorithm.
// The problem is as follows. A chessboard of some size is given. We are
// required to move a knight on it in such a way that the knight will visit
// all squares of the board without visiting the same square twice. The
// algorithm we use is the following:
// 1. Start at a random point.
// 2. At any stage look at all places the knight can move to, and mark
// how many possible moves it will have if it moves to each of them.
// 3. Move the knight to that square with the minimal number of exits; if
// there are several such points, move to a random one.
// 4. Continue as long as the knight has a square it can move to.
// ************************************************************************

class Example8KnightTour {
  public static void main(String[] args) {
    final int BOARD_SIZE = 12;
    Board myBoard = new Board(BOARD_SIZE);
    int x = 5;
    int y = 3;
    Point location = new Point(x, y);
    Knight myKnight = new Knight(location, myBoard);
    while (myKnight.canMove(myBoard))
      myKnight.move(myBoard);
    System.out.println(myBoard);
  }// method main
}// class Example8KnightTour


class Knight {
  // ===========================================================
  // Supports a few actions of the knight.
  // ===========================================================
  private final int[][] STEPS =
      {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
  private Point position;

  Knight(Point location, Board myBoard) {
    placeAt(location, myBoard);
  }

  int[][] getSteps() {
    return STEPS;
  }

  private void placeAt(Point location, Board myBoard) // place the knight
  {
    this.position = new Point(location);
    myBoard.placeKnight(position);
  }// method placeAt

  boolean canMove(Board myBoard) // check if the knight can move at all
  {
    return position.numberOfExits(this, myBoard) > 0;
  }

  // Moves the knight. First we check all possible continuations to see
  // what is the minimal number of exits from each of them. Then we find
  // all moves leading to a point with such a number of exits. Finally,
  // we selct randomly one of these points.
  void move(Board myBoard) {
    int minExits = minNumberOfExits(myBoard);
    int possibilities = numberOfHardPoints(minExits, myBoard);
    int which = (int) (possibilities * Math.random() + 1);
    Point next = newLocation(which, myBoard, minExits);
    placeAt(next, myBoard);
  }// method move

  // Finds minimal number of exits from any of the points the knight can
  // move to.
  private int minNumberOfExits(Board myBoard) {
    int exits = STEPS.length + 1;
    for (int i = 0; i < STEPS.length; i++) {
      Point next = position.translate(getSteps()[i]);
      if (next.inBoard(myBoard) && !next.visited(myBoard))
        exits = Math.min(exits, next.numberOfExits(this, myBoard));
    }
    return exits;
  }// method minNumberOfExits

  // Finds the number of points with a minimal possible number of
  // continuations at the next stage.
  private int numberOfHardPoints(int exits, Board myBoard) {
    int number = 0;
    for (int i = 0; i < STEPS.length; i++) {
      Point next = position.translate(getSteps()[i]);
      if (next.inBoard(myBoard) && !next.visited(myBoard)
          && next.numberOfExits(this, myBoard) == exits)
        number++;
    }
    return number;
  }// method numberOfHardPoints

  // Selects the point the knight moves to.
  private Point newLocation(int which, Board myBoard, int exits) {
    int number = 0, i = 0;
    Point next = new Point();
    while (number < which) {
      next = position.translate(getSteps()[i]);
      if (next.inBoard(myBoard) && !next.visited(myBoard)
          && next.numberOfExits(this, myBoard) == exits)
        number++;
      i++;
    }
    return next;
  }// method newLocation
}// class Knight


class Board {
  // ===========================================================
  // Supports a few actions on the board.
  // ===========================================================
  private final int SIZE;
  private int[][] timeOfVisit;
  private int numberOfVisitedPoints;

  Board(int BOARD_SIZE) {
    SIZE = BOARD_SIZE;
    timeOfVisit = new int[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) // superfluous
      for (int j = 0; j < SIZE; j++)
        timeOfVisit[i][j] = 0;
    numberOfVisitedPoints = 0;
  }// method Board

  int getSize() {
    return SIZE;
  }

  int getTimeOfVisit(int x, int y) {
    return timeOfVisit[x][y];
  }

  void placeKnight(Point P) // change board information as knight moves
  {
    numberOfVisitedPoints++;
    timeOfVisit[P.getX()][P.getY()] = numberOfVisitedPoints;
  }// method placeKnight

  public String toString() {
    String result = "";
    String format = "%0" + String.valueOf(numberOfVisitedPoints).length() + "d";
    for (int row = SIZE - 1; row >= 0; row--) {
      for (int col = 0; col < SIZE; col++) {
        result += String.format(format, timeOfVisit[col][row]) + " ";
      }
      result += "\n";
    }
    return result;
  }// method toString
}// class Board


class Point {
  // ===========================================================
  // Supports actions on squares in the board.
  // ===========================================================
  private int x, y;

  Point() {}

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }// method Point

  Point(Point P)

  {
    x = P.x;
    y = P.y;
  }// method Point

  int getX() {
    return x;
  }

  int getY() {
    return y;
  }

  Point translate(int[] by) {
    return new Point(x + by[0], y + by[1]);
  }

  boolean inBoard(Board myBoard) // checks if point lies within the board
  {
    return 0 <= x & x < myBoard.getSize() & 0 <= y & y < myBoard.getSize();
  }

  boolean visited(Board myBoard) // checks if point has been visited earlier
  {
    return myBoard.getTimeOfVisit(x, y) != 0;
  }

  // Counts number of moves a knight could make from this point given
  // current board situation.
  int numberOfExits(Knight myKnight, Board myBoard) {
    int result = 0;
    for (int i = 0; i < myKnight.getSteps().length; i++) {
      Point next = translate(myKnight.getSteps()[i]);
      if (next.inBoard(myBoard) && !next.visited(myBoard))
        result++;
    }
    return result;
  }// method numberOfExits
}// class Point

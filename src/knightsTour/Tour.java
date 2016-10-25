/**
 * 
 */
package knightsTour;

/**
 * 
 *
 * @author Will
 */
public class Tour {
  private final int[] XMOVES = {2, 1, -1, -2, -2, -1, 1, 2};
  private final int[] YMOVES = {1, 2, 2, 1, -1, -2, -2, -1};

  private int[][] board;
  private int size;
  private int index;
  private int xStart;
  private int yStart;

  public Tour(int size, int xStart, int yStart) {
    if (size <= 3) {
      throw new IndexOutOfBoundsException("ERROR: Board size must be greater than three.");
    }
    if (xStart > size || yStart > size || xStart < 0 || yStart < 0) {
      throw new IndexOutOfBoundsException("ERROR: Starting co-ords must be inside the board.");
    }
    this.size = size;
    board = new int[size][size];
    index = 0;
    this.xStart = xStart;
    this.yStart = yStart;
    setupBoard();
    run();
  }

  private void setupBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        board[i][j] = -1;
      }
    }
  }

  private boolean run() {
    board[xStart][yStart] = 0;
    if (!solve(xStart, yStart, 1)) {
      System.out.println("Solution doesn't exist.");
      return false;
    } else
      printSolution();
    return true;
  }

  private boolean solve(int x, int y, int count) {
    int k, nextX, nextY;
    if (count == size * size)
      return true;
    for (k = 0; k < 8; k++) {
      nextX = x + XMOVES[k];
      nextY = y + YMOVES[k];
      if (isValidMove(nextX, nextY)) {
        board[nextX][nextY] = count;
        if (solve(nextX, nextY, count + 1))
          return true;
        else
          board[nextX][nextY] = -1;
      }
    }
    return false;
  }

  private boolean isValidMove(int x, int y) {
    return (x >= 0 && x < size && y >= 0 && y < size && board[x][y] == -1);
  }

  private void printSolution() {
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        //System.out.print(board[x][y] + " ");
        System.out.print(String.format("%02d ", board[x][y]));
      }
      System.out.println();
    }
  }


}

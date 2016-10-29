/**
 * 
 */
package knightsTour;

import knightsTour.Board;
import knightsTour.Knight;
import knightsTour.Coord;

/**
 * @author grahamw0
 *
 */
public class Coord {
  private int x, y;

  public Coord() {
    
  }
  
  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Coord(Coord c)

  {
    x = c.x;
    y = c.y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Coord moveBy(int[] by) {
    return new Coord(x + by[0], y + by[1]);
  }
}

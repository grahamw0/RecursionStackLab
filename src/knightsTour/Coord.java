package knightsTour;

import knightsTour.Coord;

/**
 * Represents an ordered pair.
 * 
 * @author Ryan Godfrey, Will Graham
 *
 */
public class Coord {
  private int x, y;


  /**
   * Empty constructor.
   */
  public Coord() {

  }

  /**
   * Constructor.
   * 
   * @param x x-coord
   * @param y y-coord
   */
  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Copy constructor
   * 
   * @param c the Coord to make copy from
   */
  public Coord(Coord c) {
    x = c.x;
    y = c.y;
  }

  /**
   * Getter for x-coord.
   * 
   * @return the x coordinate
   */
  public int getX() {
    return x;
  }

  /**
   * Getter for y-coordinate.
   * 
   * @return the y coordinate
   */
  public int getY() {
    return y;
  }

  /**
   * Returns a new coordinate, changed from the current coordinate by passed amounts.
   * 
   * @param by Array of length two, of format [x,y] to change coordinate by
   * @return New, changed coordinate
   */
  public Coord moveBy(int[] by) {
    return new Coord(x + by[0], y + by[1]);
  }
}

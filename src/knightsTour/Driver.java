/**
 * 
 */
package knightsTour;

/**
 * @author grahamw0
 *
 */
public class Driver {

  /**
   * @param args
   */
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    Tour test = new Tour(9, 0, 0);
    long stopTime = System.currentTimeMillis();
    double elapsedTime = (stopTime - startTime) * 0.001;
    System.out.println(elapsedTime + " seconds");
  }

}

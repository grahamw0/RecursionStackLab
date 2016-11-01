/**
 * 
 */
package calcs;

/**
 * @author grahamw0
 *
 */
public class Driver {

  /**
   * @param args
   */
  public static void main(String[] args) {
    new InfixCalc("((2 * 3)) + (3 / 2)"); // Example call
    new PostCalc("2 3 * 3 2 / +"); // Example call
  }
}

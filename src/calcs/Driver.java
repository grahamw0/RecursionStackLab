/**
 * Driver for both types of calculators, with examples of usage.
 */
package calcs;

/**
 * @author Ryan Godfrey, Will Graham
 *
 */
public class Driver {
  public static void main(String[] args) {
    new InfixCalc("((2 * 3)) + (3 / 2)"); // Example call
    new PostCalc("2 3 * 3 2 / +"); // Example call
  }
}

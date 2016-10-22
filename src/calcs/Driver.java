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
    //PostCalc test = new PostCalc("1 2 3 * 5 + +");
    InfixCalc test = new InfixCalc("2 * 3 * 1 + 2");
    
    
    /*String testString = "(31 + 234)";
    String[] testArray = testString.split("(((?<=\\()|(?=\\())|((?<=\\))|(?=\\)))|\\s+)");
    for(String s : testArray) {
      System.out.println(s);
    }*/
    
    
  }

}

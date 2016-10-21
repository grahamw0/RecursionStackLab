/**
 * 
 */
package postfix;

/**
 * @author grahamw0
 *
 */
public class PostCalc {
  private Stack stack;
  private String expression;
  
  public PostCalc(String expression) {
    this.expression = expression;
    System.out.println(validate());  // TODO: Remove this test line
  }
  
  private boolean validate() {
    String[] array = expression.trim().split("\\s+");
    int counter = 0;
    boolean wentNeg = false;
    
    for(int i = 0; i < array.length; i++) {
      if(array[i].matches("^-?\\d+$")) {
        counter++;
      }
      else if(array[i].matches("^[+-/*//]$")) {
        counter--;
        if (counter < 0)
          wentNeg = true;
        counter--;
        if (counter < 0)
          wentNeg = true;
        counter++;
      }
      else {
        return false;
      }
    }
    if (counter == 1 && !wentNeg)
      return true;
    else
      return false;
    
  }
  
  
}

/**
 * 
 */
package calcs;

/**
 * @author grahamw0
 *
 */
public class PostCalc {
  private Stack stack;
  private String expression;

  public PostCalc(String expression) {
    this.expression = expression;
    validate();
    compute();
  }

  private void validate() {
    String[] array = expression.trim().split("\\s+");
    int counter = 0;
    boolean wentNeg = false;

    for (int i = 0; i < array.length; i++) {
      if (array[i].matches("^-?\\d+$")) {
        counter++;
      } else if (array[i].matches("^[+-/*//]$")) {
        counter--;
        if (counter < 0)
          wentNeg = true;
        counter--;
        if (counter < 0)
          wentNeg = true;
        counter++;
      } else
        throw new ArithmeticException("Invalid Postfix syntax.");
    }
    if (counter != 1 && wentNeg)
      throw new ArithmeticException("Invalid Postfix syntax.");

  }

  public void compute() {
    String[] array = expression.trim().split("\\s+");
    Stack stack = new Stack();
    
    for(int i = 0; i < array.length; i++) {
      if(array[i].matches("^-?\\d+$")) {  // Number
        stack.push(array[i]);
      }
      else {  // Operator
        int result;
        int first = Integer.valueOf(stack.pop());
        int second = Integer.valueOf(stack.pop());
        switch (array[i]) {
          case "+":
            result = second + first;
            stack.push(Integer.toString(result));
            break;
          case "-":
            result = second - first;
            stack.push(Integer.toString(result));
            break;
          case "*":
            result = second * first;
            stack.push(Integer.toString(result));
            break;
          case "/":
            result = second / first;
            stack.push(Integer.toString(result));
            break;

          default:
            break;
        }
      }
    }
    System.out.println(stack.pop());
    
    
  }


}

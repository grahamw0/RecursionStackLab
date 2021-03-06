/**
 * 
 */
package calcs;

/**
 * This is Class PostCalc. It houses the logic to take in a postfix expression and computes that
 * expression to output an int result.
 * 
 * @author Will Graham, Ryan Godfrey
 * @version 10/30/2016
 *
 */
public class PostCalc {
  private String expression; // The input String

  /**
   * This is the constructor for class PostCalc. When a PostCalc object is created it will take the
   * input parameter and initialize it to the expression field. It will also call the validate() and
   * compute() methods.
   * 
   * @param expression The input string.
   */
  public PostCalc(String expression) {
    this.expression = expression;
    validate();
    compute();
  }

  /**
   * The validate() method will parse through the input string one char at a time. It will check to
   * see if each character is valid and if the overall syntax is valid.
   */
  private void validate() {
    String[] array = expression.trim().split("\\s+"); // Split on whitespace
    int counter = 0;
    boolean wentNeg = false;

    for (int i = 0; i < array.length; i++) { // Iterate through the array.
      if (array[i].matches("^-?\\d+$")) { // regex to check if it is a number.
        counter++;
      } else if (array[i].matches("^[+-/*//]$")) { // Regex to check if it is an operator.
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

  /**
   * The compute() method will be called from the constructor. It will take in the input String and
   * split it up into an array. It will then iterate through each token and compute the given
   * postfix expression.
   */
  private void compute() {
    String[] array = expression.trim().split("\\s+"); // Split on whitespace
    Stack stack = new Stack();

    for (int i = 0; i < array.length; i++) { // Iterate through the array.
      if (array[i].matches("^-?\\d+$")) { // Pos. or neg. number
        stack.push(array[i]);
      } else {
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
    System.out.println(stack.pop()); // Display result
  }
}

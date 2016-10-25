/**
 * 
 */
package calcs;

/**
 * @author grahamw0
 *
 */
public class PostfixStatic {
  
  public static void run(String expression) {
    validate(expression);
    compute(expression);
  }
  
  /**
   * The validate() method will parse through
   * the input string one char at a time.  It
   * will check to see if each character is valid.
   */
  private static void validate(String expression) {
    String[] array = expression.trim().split("\\s+"); // Take the input string and split it up into an array.  
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
   * The compute() method will be called from the constructor.
   * It will take in the input String and split it up into
   * an array.  It will then iterate through each char and 
   * compute the given postfix expression.  
   */
  public static void compute(String expression) {
    String[] array = expression.trim().split("\\s+"); // Take the input string and split it up into an array.
    Stack stack = new Stack();
    
    for(int i = 0; i < array.length; i++) { // Iterate through the array.
      if(array[i].matches("^-?\\d+$")) {  // If the char is a number then push it.  Regex for a number. 
        stack.push(array[i]); 
      }
      else {  // Use a switch to handle the individual case depending on the operator. 
        int result;
        int first = Integer.valueOf(stack.pop()); // Create an int variable equal to the value of the char. 
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
    System.out.println(stack.pop()); // pop the result
  }
}

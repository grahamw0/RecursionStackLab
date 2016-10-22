/**
 * 
 */
package postfix;

/**
 * 
 *
 * @author Will
 */
public class Infix {
  private String expression;
  private String postExpression;

  /**
   * @param stack
   * @param expression
   */
  public Infix(String expression) {
    this.expression = expression;
    this.postExpression = "";
    validate();
  }

  private void validate() {
    Stack stack = new Stack();
    for (char c : expression.toCharArray()) {
      if (c == '(') {
        stack.push("(");
      } else if (c == ')') {
        if (stack.isEmpty()) {
          throw new ArithmeticException("Parenthesis Mismatch.");
        } else if (stack.peek() == "(") {
          stack.pop();
        } else
          throw new ArithmeticException("Parenthesis Mismatch.");
      }
    }
    if(!stack.isEmpty())
      throw new ArithmeticException("Parenthesis Mismatch.");
  }

}

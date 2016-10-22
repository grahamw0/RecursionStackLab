/**
 * 
 */
package calcs;

/**
 * 
 *
 * @author Will
 */
public class InfixCalc {
  private String expression;
  private String postExpression;

  /**
   * @param stack
   * @param expression
   */
  public InfixCalc(String expression) {
    this.expression = expression;
    this.postExpression = "";
    validate();
    compute();
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
    if (!stack.isEmpty())
      throw new ArithmeticException("Parenthesis Mismatch.");
  }

  private void compute() {
    Stack opStack = new Stack();
    String splitOperWhiteSpace = "(((?<=\\()|(?=\\())|((?<=\\))|(?=\\)))|\\s+)";
    String[] exprArray = expression.trim().split(splitOperWhiteSpace);
    for (String token : exprArray) {
      if (isNumber(token)) { // Number
        postExpression += token + " ";
      } else if (token.equals(")")) {
        while (!opStack.isEmpty() && !opStack.peek().equals("(")) {
          postExpression += opStack.pop() + " ";
        }
      } else if (token.equals("(")) {
        opStack.push(token);
      } else if (isOperator(token)) {
        if (opStack.isEmpty()) {
          opStack.push(token);
        } else if (!isOperator(opStack.peek())) {
          opStack.push(token);
        } else if (getPrecedence(token) > getPrecedence(opStack.peek())) {
          opStack.push(token);
        } else {
          while(opStack.peek() != null && getPrecedence(token) <= getPrecedence(opStack.peek())) {
            postExpression += opStack.pop() + " ";
          }
          opStack.push(token);
        }
      }
    }
    while (!opStack.isEmpty()) {
      if(opStack.peek().equals("(")) {
        opStack.pop();
      }
      else {
        postExpression += opStack.pop() + " ";
      }
    }
    new PostCalc(postExpression);
    //System.out.println(postExpression); // TODO: Remove test statement
  }


  private boolean isNumber(String s) {
    return s.matches("^-?\\d+$");
  }

  private boolean isOperator(String s) {
    return s.matches("^[+-/*//]$");
  }

  private int getPrecedence(String op) {
    switch (op) {
      case "+":
      case "-":
        return 1;
      case "*":
      case "/":
        return 2;
      default:
        return -1; // Error; should be unreachable
    }
  }

}

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
    fixExpression();
    validate();
    compute();
  }

  /**
   * Replaces any amount of whitespace with just one space, then turns a paren then a number or a
   * number than a paren into an explicit multiplicative form. Then does the same for two
   * parentheses next to each other.
   */
  private void fixExpression() {
    expression = expression.trim().replaceAll("\\s+", " "); // Whitespace fix.
    expression =expression.replaceAll("[)][\\s+]?(?=\\d)", ") * "); // )# -> ) * #
    expression =expression.replaceAll("(?<=\\d)[\\s+]?[(]", " * ("); // #( -> # * (
    expression =expression.replaceAll("[)][\\s+]?[(]", ") * ("); // )( -> ) * (

    String fixedExp = "";
    String[] array = expression.split(""); // Going to look at each character
    for (int i = 0; i < expression.length(); i++) {
      if (i == expression.length() - 1 || array[i].matches("[+-/*]"))
        fixedExp += array[i] + " ";
      else if (array[i].matches("[()]"))
        fixedExp += array[i];
      else if (array[i].matches("\\d")) {
        if (array[i + 1].matches("\\d"))
          fixedExp += array[i];
        else
          fixedExp += array[i] + " ";
      }
    }
    expression = fixedExp;
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
        opStack.pop(); // Remove open paren
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
          while (opStack.peek() != null && getPrecedence(token) <= getPrecedence(opStack.peek())) {
            postExpression += opStack.pop() + " ";
          }
          opStack.push(token);
        }
      }
    }
    while (!opStack.isEmpty()) {
      // TODO: Determine whether this check is necessary after opStack.pop(); on line 55
      if (opStack.peek().equals("(")) {
        opStack.pop();
      } else {
        postExpression += opStack.pop() + " ";
      }
    }
    System.out.println(postExpression); // TODO: Remove test statement
    new PostCalc(postExpression);

  }


  private boolean isNumber(String s) {
    return s.matches("^-?\\d+$");
  }

  private boolean isOperator(String s) {
    return s.trim().matches("^[+-/*//]$");
  }

  private int getPrecedence(String op) {
    switch (op.trim()) {
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

/**
 * 
 */
package calcs;

/**
 * This is class InFixCalc. It will input a regular mathmetic expression and will turn that
 * expression into the postfix form. It will first split the expression up so it can deal with it
 * one token at a time. It will then validate that the expression is valid and compute it to a
 * postfix expression.
 * 
 * @author Will Graham, Ryan Godfrey
 * @version 10/30/2016
 */
public class InfixCalc {
  private String expression;
  private String postExpression;

  /**
   * This is the constructor for class InfixCalc. It takes in the expression and initializes the
   * fields. It will then call on the 3 methods that will handle the postfix logic.
   * 
   * @param expression the original input
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
    expression = expression.replaceAll("[)](?=\\d)", ") * "); // )# -> ) * #
    expression = expression.replaceAll("(?<=\\d)[(]", " * ("); // #( -> # * (
    expression = expression.replaceAll("[)][(]", ") * ("); // )( -> ) * (

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

  /**
   * The validate() method will check each char and check for parenthesis matching. If the
   * expression has an unbalanced number of parenthesis, this method will throw an
   * ArithmeticException.
   */
  private void validate() {
    Stack stack = new Stack();
    for (char c : expression.toCharArray()) { // iterate through the expression
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

  /**
   * The compute() method will create the stack that is used to organize the postfix expression. It
   * will properly split the expression up and sort the tokens one at a time. It will then use the
   * stack to build the expression to a postfix form.
   *
   */
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
      postExpression += opStack.pop() + " ";
    }
    new PostCalc(postExpression);

  }


  /**
   * The inNumber() method uses a regex expression to match the char in question to a number.
   * 
   * @param s the element in question
   * @return a boolean expression if it is a number.
   */
  private boolean isNumber(String s) {
    return s.matches("^-?\\d+$");
  }


  /**
   * The inOperator() method uses a regex expression to match the char in question to an operator.
   * 
   * @param s the element in question
   * @return a boolean expression if it is an operator.
   */
  private boolean isOperator(String s) {
    return s.trim().matches("^[+-/*//]$");
  }

  /**
   * The getPrecedence() method will assign an int value to each operator so the compute() method
   * will be able to check each operator for priority level and push to the stack or the String
   * result.
   * 
   * @param op the operator in question
   * @return the precedence level.
   */
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

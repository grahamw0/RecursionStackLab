/**
 * 
 */
package calcs;

/**
 * @author grahamw0
 *
 */
public class InfixStatic {
  /*public InfixCalc(String expression) {
    this.expression = expression;
    this.postExpression = "";
    validate();
    compute();
  }*/
  
  public static void run(String expression) {
    validate(expression);
    compute(expression);
  }
  

  private static void validate(String expression) {
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

  private static void compute(String expression) {
    String postExpression = "";
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
          while(opStack.peek() != null && getPrecedence(token) <= getPrecedence(opStack.peek())) {
            postExpression += opStack.pop() + " ";
          }
          opStack.push(token);
        }
      }
    }
    while (!opStack.isEmpty()) {
      //TODO: Determine whether this check is necessary after opStack.pop(); on line 55
      if(opStack.peek().equals("(")) {
        opStack.pop();
      }
      else {
        postExpression += opStack.pop() + " ";
      }
    }
    System.out.println(postExpression); // TODO: Remove test statement
    new PostCalc(postExpression);
    
  }


  private static boolean isNumber(String s) {
    return s.matches("^-?\\d+$");
  }

  private static boolean isOperator(String s) {
    return s.trim().matches("^[+-/*//]$");
  }

  private static int getPrecedence(String op) {
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

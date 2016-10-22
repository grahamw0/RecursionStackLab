package infix;

public class InfixToPostfix {
  private StackIn stack;
  private String expression;
  
  /**
   * 
   */
  public InfixToPostfix(String expression) {
	  this.expression = expression;
	  
  }
  
  /**
   * 
   * @param c
   * @return
   */
  public static boolean isOperator(char c) {
	if(c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')'){
	  return true;	
	}else{
	  return false;
	}	
  }
  
  /**
   * 
   * @param ch
   * @return
   */
  public int getPriority(String ch) {
    switch(ch){
	case "+":
    case "-":
      return 1;
    case "*":
    case "/":
      return 2;
    }
    return -1;
  }
  
  /**
   * 
   * @param ch
   * @return
   */
  public boolean isNumber(char ch) {
	String c = String.valueOf(ch); 
    return (c.matches("^-?\\d+$"));  
  }
  
  public String convertToPostFix(){
	String infix = expression;
	StackIn stack = new StackIn();
	StringBuffer postfix = new StringBuffer(infix.length());
	char c;
	
	for (int i = 0; i < infix.length(); i++){
	  c = infix.charAt(i);
	  
	  if (isNumber(c)){
		  postfix.append(c);  
	  }else if(c == '('){
		  String d = String.valueOf(c);
		  stack.push(d);
	  }
	  
	  // If the scanned char is a ')' pop and output from the stack
	  // until a '(' is found. 
	  else if (c == ')') {
		  while (!stack.isEmpty() && stack.peek() != String.valueOf('(')) {
			postfix.append(stack.pop());  
		  }
		  if (!stack.isEmpty() && stack.peek() != String.valueOf('('))
		    return null;
		  else if (!stack.isEmpty())
		    stack.pop();
	  }
	  else if (isOperator(c)) {
	    if (!stack.isEmpty() && getPriority(String.valueOf(c)) <= getPriority(stack.peek())){
	      postfix.append(stack.pop());
	    }
	    String d = String.valueOf(c);
		stack.push(d);
	  }
	}
	while (!stack.isEmpty()) {
	  postfix.append(stack.pop());
	}
	return postfix.toString();
  }
	

}

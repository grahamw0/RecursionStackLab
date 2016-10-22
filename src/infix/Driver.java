package infix;

import postfix.PostCalc;

public class Driver {
  public static void main(String[] args){
	  
    InfixToPostfix test = new InfixToPostfix("1 + 2 * (3 - 5)");
    InfixToPostfix test2 = new InfixToPostfix("1 + 2 * 3 - 5");
    //PostCalc test2 = new PostCalc("1 2 3 * 5 - +");
    System.out.println(test.convertToPostFix());
    System.out.println(test2.convertToPostFix());
    
  }

}

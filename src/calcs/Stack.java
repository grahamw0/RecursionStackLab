package calcs;

import java.util.LinkedList;

/**
 * Class Stack is the custom designed stack that 
 * all classes in package calcs will utilize.  It will be
 * called on by both the Infix and the Postfix calculators.  
 * It will house all of the stack related methods such as push, 
 * pop, peek, isEmpty, and return the actual linked list in question. 
 * @author Will Graham, Ryan Godfrey
 * @version 10/23/2016
 *
 */
public class Stack {
  private LinkedList<String> list; // The linked list called list
  private int size; // The size
  private boolean empty; // Is it empty?
  
  /**
   * The constructor for class Stack.  
   * When an instance of Stack is created
   * this constructor will initialize the new linked list,
   * initialize the size to 0, and set empty to true.  
   */
  public Stack() {
    list = new LinkedList<>();
    size = 0;
    empty = true;
  }
  
  /**
   * The push() method will push the string in question
   * to the stack.   
   * @param c - The string in question
   */
  public void push(String c) {
    list.push(c); // push onto the stack
    size++; // increment the size
    empty = false; // The list is no longer empty
  }
  
  /**
   * The pop() method will pop the string in question
   * from the stack.  If the stack is empty it will
   * throw an IndexOutOfBounds Exception. 
   * @return The element poped from the stack
   */
  public String pop() {
    if (size <= 0) {
      throw new IndexOutOfBoundsException("Error: Popped off an empty stack.");
    }
    size--; // decrement the size
    emptyCheck(); // This method resets the field empty to true if the list is now empty.  
    return list.pop(); // returns the popped element
  }
  
  /**
   * The peek() method will get the element without 
   * removing the element from the list.  This will 
   * allow access the the element for comparison.
   * @return
   */
  public String peek() {
    if (size <= 0) // If the list is empty
      return null;
    else
      return list.peek(); // Return the element on the stack but do not remove it.  
  }
  
  /**
   * The size() method will return the current
   * state of the size field. 
   * @return The field called size
   */
  public int size() {
    return size;
  }
  
  /**
   * The isEmpty() method will return the current
   * boolean value of the field empty. 
   * @return The field called empty
   */
  public boolean isEmpty() {
    return empty;
  }
  
  /**
   * The toLinkedList() method will return 
   * the list.
   * @return The LinkedLIst called list
   */
  public LinkedList toLinkedList() {
    return list;
  }

  /**
   * The emptyCheck() method checks size, adjusts empty boolean.
   */
  private void emptyCheck() {
    if (size == 0) { // If the list is empty
      empty = true;
    } else { // If the list has an element
      empty = false;
    }
  }

}

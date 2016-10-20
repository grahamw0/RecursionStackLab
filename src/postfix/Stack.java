package postfix;

import java.util.LinkedList;

/**
 * @author grahamw0
 *
 */
public class Stack {
  private LinkedList<Character> list;
  private int size;
  private boolean empty;

  public Stack() {
    list = new LinkedList<>();
    size = 0;
    empty = true;
  }

  public void push(char c) {
    list.push(c);
    size++;
    emptyCheck();
  }

  public Character pop() {
    if (size == 0) {
      throw new IndexOutOfBoundsException("Error: Popped off empty stack.");
    }
    size--;
    emptyCheck();
    return list.pop();
  }

  /**
   * Checks size, adjusts empty boolean.
   */
  private void emptyCheck() {
    if (size == 0) {
      empty = true;
    }
    else {
      empty = false;
    }
  }

}

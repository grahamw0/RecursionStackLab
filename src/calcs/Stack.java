package calcs;

import java.util.LinkedList;

/**
 * @author grahamw0
 *
 */
public class Stack {
  private LinkedList<String> list;
  private int size;
  private boolean empty;

  public Stack() {
    list = new LinkedList<>();
    size = 0;
    empty = true;
  }

  public void push(String c) {
    list.push(c);
    size++;
    empty = false;
  }

  public String pop() {
    if (size <= 0) {
      throw new IndexOutOfBoundsException("Error: Popped off an empty stack.");
    }
    size--;
    emptyCheck();
    return list.pop();
  }
  
  public String peek() {
    if (size <= 0)
      return null;
    else
      return list.peek();
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return empty;
  }
  
  public LinkedList toLinkedList() {
    return list;
  }

  /**
   * Checks size, adjusts empty boolean.
   */
  private void emptyCheck() {
    if (size == 0) {
      empty = true;
    } else {
      empty = false;
    }
  }

}

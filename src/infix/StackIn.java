package infix;

import java.util.LinkedList;

public class StackIn {
	private LinkedList<String> list;
	  private int size;
	  private boolean empty;

	  public StackIn() {
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
	  
	  public String peek() {
		    if (size <= 0) {
		      throw new IndexOutOfBoundsException("Error: Peeked at an empty stack.");
		    }
		    emptyCheck();
		    return list.peek();
		  }
	  
	  

}

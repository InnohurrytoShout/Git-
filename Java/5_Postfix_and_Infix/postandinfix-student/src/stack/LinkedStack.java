package stack;

/**
 * A {@link LinkedStack} is a generic stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 */
public class LinkedStack<T> {
	
	// TODO: define class variables here
	LLNode<T> head;
	int count = 0;
	/**
	 * Remove and return the top element on this stack.
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T pop() {
		// TODO
		LLNode<T> first = head;
		if(first == null) {return null;}
		head = head.link;
		count--;
		return first.info;
	}

	/**
	 * Return the top element of this stack (do not remove the top element).
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T top() {
		// TODO
		if(head != null) {return head.info;}
		return null;
	}

	/**
	 * Return true if the stack is empty and false otherwise.
	 */
	public boolean isEmpty() {
		// TODO
		if(head != null) {return false;}
		return true;
	}

	/**
	 * Return the number of elements in this stack.
	 */
	public int size() {
		// TODO
		return count;
	}

	/**
	 * Pushes a new element to the top of this stack.
	 */
	public void push(T elem) {
		// TODO
		LLNode<T> push = new LLNode<T>(elem);
		push.link = head;
		head = push;
		count++;
	}

}

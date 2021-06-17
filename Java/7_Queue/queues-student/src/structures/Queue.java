package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {

	private Node<T> head;
	private Node<T> tail;
	private int size;


	public Queue() {		
            // TODO 1
		head = null;
		tail = null;
		size = 0;
	}
	
	public Queue(Queue<T> other) {
            // TODO 2
		if(other == null){
			head = null;
			tail = null;
			size = 0;
		}else {
			Node<T> temp = other.head;
			while (temp != null) {
				this.enqueue(temp.data);
				temp = temp.next;
			}
		}
	}
	
	@Override
	public boolean isEmpty() {
            // TODO 3
		if (size() == 0) { return true;}
		return false;
	}

	@Override
	public int size() {
            // TODO 4
            return this.size;
	}

	@Override
	public void enqueue(T element) {
            // TODO 5
		Node<T> newnode = new Node<>(element, null);
		if (isEmpty()) {
			head = new Node<>( element, null);
			tail = head;
		}else {
			Node<T> temp = tail;
			temp.next = newnode;
			tail = newnode;
		}
		size++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
            // TODO 6
		if ( isEmpty()) { throw new NoSuchElementException("No such element");}
		T element = head.data;
		size--;
		head = head.next;
		if (head == null) { tail = null;}
            return element;
	}

	@Override
	public T peek() throws NoSuchElementException {
            // TODO 7
		if( isEmpty()) { throw new NoSuchElementException("No such element");}

            return head.data;
	}

	
	@Override
	public UnboundedQueueInterface<T> reversed() {
            // TODO 8
		Queue<T> other = new Queue<>();
		Node<T> temp = this.head;
		while (temp != null){
			Node<T> newnode = new Node<>(temp.data, null);
			if (other.head == null){ head = newnode;}
			newnode.next = other.head;
			other.head = newnode;
			other.size++;
			temp = temp.next;
		}
            return other;
	}
}

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}
}


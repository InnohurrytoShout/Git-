package structures;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {

    private LLNode<T> head;
    private LLNode<T> tail;
    private int size;

    public RecursiveList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new ListInterfaceIterator<T>(this.head);
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return size;
    }

    @Override
    public ListInterface<T> insertFirst(T elem) {
        // TODO Auto-generated method stub
        if (elem == null) {

            throw new NullPointerException("");
        }
        if (this.head == null) {

            this.head = new LLNode<T>(elem, null);
            this.tail = this.head;
        } else {

            this.head = new LLNode<T>(elem, head);
        }
        this.size++;
        return this;
    }

    @Override
    public ListInterface<T> insertLast(T elem) {
        // TODO Auto-generated method stub
        if (elem == null) {

            throw new NullPointerException("");
        }
        LLNode<T> newNode = new LLNode<>(elem);
        if (size == 0) {

            head = newNode;
            tail = head;
        } else {
            LLNode<T> temp = tail;
            temp.next = newNode;
            tail = newNode;
        }
        size++;
        return this;
    }

    @Override
    public ListInterface<T> insertAt(int index, T elem) {
        // TODO Auto-generated method stub
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("");
        }
        if (elem == null) {
            throw new NullPointerException("");
        }
        if ( index == 0){ insertFirst( elem);}
        else if ( index == size){ insertLast( elem);}
        else {
            LLNode<T> curr = nodeAt(index - 1, 0, head);
            LLNode<T> next = new LLNode<>(elem, curr);
            next.next = curr.next;
            curr.next = next;
            size++;
        }
        return this;
    }


    @Override
    public T removeFirst() {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            throw new IllegalStateException("");
        }
        T temp = head.data;
        head = head.next;
        size--;
        return temp;
    }

    @Override
    public T removeLast() {
        // TODO Auto-generated method stub
        if (head == null) {
            throw new IllegalStateException("");
        }
        T lastData = tail.data;
        tail = null;
        if (size == 1){
            head = head.next;
            tail = null;
        }
        else{
            tail = nodeAt(size - 2, 0, head);
            tail.next = null;
        }
        size--;
        return lastData;
    }


    @Override
    public T removeAt(int i) {
        // TODO Auto-generated method stub
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("");
        }
        T number;
        if (i == 0){ number = removeFirst();}
        else {
            number = nodeAt(i, 0, head).data;
            LLNode<T> curr = nodeAt(i - 1, 0, head);
            LLNode<T> next = nodeAt(i + 1, 0, head);
            curr.next = next;
            size--;
        }
        return number;
    }


    @Override
    public T getFirst() {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            throw new IllegalStateException("");
        }
        return head.data;
    }

    @Override
    public T getLast() {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            throw new IllegalStateException("");
        }
        return tail.data;
    }

    @Override
    public T get(int i) {
        // TODO Auto-generated method stub
        if( i < 0 || i >= size){ throw new IndexOutOfBoundsException("");}
        if( i == 0){ return head.data;}
        else if ( i == size - 1){ return tail.data;}
        return nodeAt(i, 0, head).data;
    }

    @Override
    public boolean remove(T elem) {
        // TODO Auto-generated method stub
        if ( elem == null){ throw new NullPointerException("");}
        int index = indexOf( elem);
        if (index == -1){return false;}
        if ( index == 0){
            removeFirst();
        }else if ( index == size - 1){
            removeLast();
        }else{
            removeAt( index);
        }
        return true;
    }

    private LLNode<T> removeElem(T elem, LLNode<T> curr) {
        if ( curr.data.equals(elem)) {
            return curr;
        }
        if ( curr == null){ return null;}
        curr = curr.next;
        return removeElem( elem, curr);
    }

    @Override
    public int indexOf(T elem) {
        // TODO Auto-generated method stub
        if ( elem == null){ throw new NullPointerException("");}
        int i = indexOf( elem, 0, head);
        return i;
    }

    private int indexOf(T elem, int currIndex, LLNode<T> currnode) {
        if ( currnode == null){ return -1;}
        if ( currnode.data.equals( elem)){ return currIndex;}
        currnode = currnode.next;
        currIndex++;
        return indexOf( elem, currIndex, currnode);
    }

    private LLNode<T> nodeAt(int index, int count, LLNode<T> curr) {
        if ( curr == null){ return null;}
        if ( count == index) {
            return curr;
        }
        curr = curr.next;
        count++;
        return nodeAt(index, count, curr);
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        if (head == null || size == 0) {
            return true;
        }
        return false;
    }

}

class LLNode<T> {
    public T data;
    public LLNode<T> next;

    public LLNode(T data) {
        this.data = data;
    }

    public LLNode(T data, LLNode<T> next) {
        this.data = data;
        this.next = next;
    }
}

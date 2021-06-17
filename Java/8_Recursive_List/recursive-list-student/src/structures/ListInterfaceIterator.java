package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;


class ListInterfaceIterator<T> implements Iterator<T> {

    private LLNode<T> head;

    public ListInterfaceIterator(LLNode<T> head){
        this.head = head;
    }
    @Override
    public boolean hasNext() {
        if (head == null){
            return false;
        }
        return true;
    }

    @Override
    public T next() {
        if ( !hasNext()){ throw new NoSuchElementException("");}
        T temp = head.data;
        head = head.next;
        return temp;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("");
    }
}

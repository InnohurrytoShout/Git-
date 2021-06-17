package structures;

import comparators.ReverseIntegerComparator;

import java.util.Comparator;
import java.util.Iterator;

public class MinQueue<V> implements PriorityQueue<Integer, V> {

    protected ReverseIntegerComparator comparator = new ReverseIntegerComparator();
    protected StudentArrayHeap<Integer, V> Min = new StudentArrayHeap<>(comparator);
    @Override
    public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
        if (priority == null || value == null){
            throw new NullPointerException();
        }
        Min.add(priority, value);
        return this;
    }

    @Override
    public V dequeue() {
        if (Min == null){
            throw new IllegalStateException();
        }
        return Min.remove();
    }

    @Override
    public V peek() {
        if (Min == null){
            throw new IllegalStateException();
        }
        return Min.peek();
    }

    @Override
    public Iterator<Entry<Integer, V>> iterator() {
        return Min.asList().iterator();
    }

    @Override
    public Comparator<Integer> getComparator() {
        return Min.comparator;
    }

    @Override
    public int size() {
        return Min.size();
    }

    @Override
    public boolean isEmpty() {
        return Min.isEmpty();
    }
}


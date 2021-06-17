package structures;

import comparators.IntegerComparator;

import java.time.Year;
import java.util.Comparator;
import java.util.Iterator;

public class MaxQueue<V> implements PriorityQueue<Integer, V> {

    protected IntegerComparator comparator = new IntegerComparator();
    protected StudentArrayHeap<Integer, V> Max = new StudentArrayHeap<>(comparator);

    @Override
    public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
        if (priority == null || value == null){
            throw new NullPointerException();
        }
        Max.add(priority, value);
        return this;
    }

    @Override
    public V dequeue() {
        if (Max == null){
            throw new IllegalStateException();
        }
        return Max.remove();
    }

    @Override
    public V peek() {
        if (Max == null){
            throw new IllegalStateException();
        }
        return Max.peek();
    }

    @Override
    public Iterator<Entry<Integer, V>> iterator() {
        return Max.asList().iterator();
    }

    @Override
    public Comparator<Integer> getComparator() {
        return Max.comparator;
    }

    @Override
    public int size() {
        return Max.size();
    }

    @Override
    public boolean isEmpty() {
        return Max.isEmpty();
    }
}

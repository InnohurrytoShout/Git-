package structures;

import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {

    /**
     * Creates an {@link AbstractArrayHeap} with the specified
     * {@link Comparator} for determining priority
     *
     * @param comparator the {@link Comparator} to use to determine priority
     * @throws NullPointerException if {@code comparator} is {@code null}
     */
    protected StudentArrayHeap(Comparator<P> comparator) {
        super(comparator);
    }

    @Override
    protected int getLeftChildOf(int index) {
        if (index < 0){
            throw new IndexOutOfBoundsException();
        }
        return 2 * index + 1;
    }

    @Override
    protected int getRightChildOf(int index) {
        if (index < 0){
            throw new IndexOutOfBoundsException();
        }
        return 2 * index + 2;
    }

    @Override
    protected int getParentOf(int index) {
        if (index < 1){
            throw new IndexOutOfBoundsException();
        }
        return (index - 1) / 2;
    }

    @Override
    protected void bubbleUp(int index) {
        while ((index > 0) && (comparator.compare(heap.get(index).getPriority(), heap.get((index - 1) / 2).getPriority()) > 0)){
            swap(index, getParentOf(index));
            index = (index - 1) / 2;
        }
    }

    @Override
    protected void bubbleDown(int index) {


        int largerChild;
        Entry<P, V> temp = heap.get(index);
        while (index < heap.size() / 2){
            if (2 * index + 2 < heap.size()
                    && comparator.compare(heap.get(2 * index + 1).getPriority(), heap.get(2 * index + 2).getPriority()) < 0) {
                largerChild = 2 * index + 2;
            }else {
                largerChild = 2 * index + 1;
            }
            if (comparator.compare(temp.getPriority(), heap.get(largerChild).getPriority()) >= 0) {
                break;
            }
            heap.set(index, heap.get(largerChild));
            index = largerChild;
        }
        heap.set(index, temp);

        /*if (getLeftChildOf(index) > size()){          //No Child
            return;
        }

        if (getRightChildOf(index) > size() && getLeftChildOf(index) < size()){         //Only Left Child exists
            if (getComparator().compare(heap.get(getLeftChildOf(index)).getPriority(), heap.get(index).getPriority()) > 0){
                swap(getLeftChildOf(index), index);
                bubbleDown(getLeftChildOf(index));
            }
        }

        if (getRightChildOf(index) < size()){               //Both two children exist
            if (comparator.compare(heap.get(getLeftChildOf(index)).getPriority(), heap.get(index).getPriority()) > 0
                    && comparator.compare(heap.get(getRightChildOf(index)).getPriority(), heap.get(index).getPriority()) > 0){
                if (comparator.compare(heap.get(getLeftChildOf(index)).getPriority(), heap.get(getRightChildOf(index)).getPriority()) > 0){
                    swap(getLeftChildOf(index), index);
                    bubbleDown(getLeftChildOf(index));
                }
                else {
                    swap(getRightChildOf(index), index);
                    bubbleDown(getRightChildOf(index));
                }
            }else if (comparator.compare(heap.get(getLeftChildOf(index)).getPriority(), heap.get(index).getPriority()) < 0
                    && comparator.compare(heap.get(getRightChildOf(index)).getPriority(), heap.get(index).getPriority()) > 0){
                swap(getRightChildOf(index), index);
                bubbleDown(getRightChildOf(index));
            }else{
                swap(getLeftChildOf(index), index);
                bubbleDown(getLeftChildOf(index));
            }
        }*/
    }
}



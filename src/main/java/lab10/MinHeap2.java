package lab10;

public class MinHeap2<T extends Comparable<T>> extends BaseMinHeap2<T> {
    @SuppressWarnings("unchecked")
    public MinHeap2(int length) {
        heap = (T[]) new Comparable[length + 1];
        currentSize = 0;
        heap[0] = null;
    }
    
    public MinHeap2(T[] arr) {
        this(arr.length);
        for (T val : arr)
            insert(val);
    }
    
    public void insert(T element) {
        if (isFull()) throw new RuntimeException("Cannot insert " + element + " because the heap is full.");
        if (DEBUG) System.out.println(element);
        heap[++currentSize] = element;
        int cursor = currentSize;
        if (DEBUG) System.out.println(this);
        while (getParent(cursor) > 0 && heap[cursor].compareTo(heap[getParent(cursor)]) < 0) {
            swap(cursor, getParent(cursor));
            cursor = getParent(cursor);
            if (DEBUG) System.out.println(this);
        }
        if (DEBUG) System.out.println("-------------------------------");
    }
    
    public T deleteMin() {
        try {
            T res = getMin();
            int cursor = 1;
            while (!isLeaf(cursor)) {
                int minChild = getMinChild(cursor);
                if (heap[cursor].compareTo(heap[minChild]) >= 0) {
                    break;
                }
                swap(cursor, minChild);
                cursor = minChild;
            }
            for (int i = cursor; i < currentSize; i++) {
                swap(i, i + 1);
            }
            currentSize--;
            return res;
        } catch (RuntimeException ignored) {
            return null;
            //throw new RuntimeException("Cannot delete minimum because the heap is empty.");
        }
    }
    
    public boolean isFull() {
        return currentSize == heap.length - 1;
    }
    
    public boolean isEmpty() {
        return currentSize == 0;
    }
    
    public T getMin() {
        if (isEmpty()) throw new RuntimeException("Cannot get minimum because the heap is empty.");
        return heap[1];
    }
    
    /**
     * @return A string represents the heap. The order of the elements are according
     * to The string starts with "[", ends with "]", and the values are seperated
     * with a comma
     */
    public String toString() {
        if (isEmpty()) return "[]";
        int c = 1;
        int p = 1;
        StringBuilder sb = new StringBuilder("[");
        for (int i = 1; i <= Math.min(currentSize, heap.length - 1); i++) {
            if (DEBUG) {
                if (c == p) {
                    p *= 2;
                    c = 1;
                    sb.append(heap[i]).append(" | ");
                } else {
                    sb.append(heap[i]).append(" , ");
                    c++;
                }
            } else {
                sb.append(heap[i]).append(",");
            }
        }
        return sb.substring(0, sb.length() - ( DEBUG ? 3 : 1 )) + "]";
    }
}

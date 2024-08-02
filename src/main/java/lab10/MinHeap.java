package lab10;

public class MinHeap<T extends Comparable<T>> {
    private T[] array;
    private int currentSize;
    
    @SuppressWarnings("unchecked")
    public MinHeap(int length) {
        array = (T[]) new Comparable[length + 1];
        currentSize = 0;
        array[0] = null;
    }
    
    public MinHeap(T[] arr) {
        this(arr.length);
        for (T element : arr) {
            insert(element);
        }
    }
    
    public boolean isFull() {
        return currentSize == array.length - 1;
    }
    
    public boolean isEmpty() {
        return currentSize == 0;
    }
    
    private int getLeftChild(int pos) { return 2 * pos; }
    
    private int getRightChild(int pos) { return 2 * pos + 1; }
    
    private int getParent(int pos) { return (int) Math.floor(pos / 2f); }
    
    private void swap(int pos1, int pos2) {
        T temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
    }
    
    private Integer getMinChild(int pos) {
        int left = getLeftChild(pos);
        if (left > currentSize) return null;
        int right = getRightChild(pos);
        if (right > currentSize) return left;
        return array[left].compareTo(array[right]) < 0 ? left : right;
    }
    
    public void insert(T element) {
        if (isFull()) { throw new RuntimeException("...."); }
        array[++currentSize] = element;
        int pos = currentSize;
        while (getParent(pos) >= 1) {
            if (element.compareTo(array[getParent(pos)]) < 0) {
                swap(pos, getParent(pos));
                pos = getParent(pos);
            } else {
                break;
            }
        }
    }
    
    public T getMin() {
        if (isEmpty()) throw new RuntimeException("....");
        return array[1];
    }
    
    public T deleteMin() {
        try {
            T min = getMin(); // throws RuntimeException
            int pos = 1;
            Integer child = getMinChild(pos);
            while (child != null) {
                if (array[pos].compareTo(array[child]) < 0) {
                    swap(pos, child);
                    pos = child;
                    child = getMinChild(pos);
                } else {
                    break;
                }
            }
            while (pos + 1 <= currentSize) {
                array[pos] = array[pos + 1];
                pos++;
            }
            currentSize--;
            return min;
        } catch (Exception ignored) { return null; }
    }
    
    /**
     * return a string represents the heap. The order of the elements are according
     * to The string starts with "[", ends with "]", and the values are seperated
     * with a comma
     */
    public String toString() {
        if (isEmpty()) return "[]";
        // [1,2,5,4,3,9,6,10,7,8]
        StringBuilder sb = new StringBuilder("[");
        for (int i = 1; i <= currentSize; i++) {
            sb.append(array[i]).append(",");
        }
        return sb.substring(0, sb.length() - 1) + "]";
    }
}
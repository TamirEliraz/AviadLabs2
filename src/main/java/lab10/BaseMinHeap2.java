package lab10;

abstract class BaseMinHeap2<T extends Comparable<T>> {
    protected int currentSize;
    protected T[] heap;
    protected static final boolean DEBUG = false;
    
    /**
     * @param pos
     *         current position.
     * @return The position of the parent.
     */
    protected int getParent(int pos) { return pos / 2; }
    
    /**
     * @param pos
     *         current position.
     * @return The position of the left child.
     */
    protected int getLeftChild(int pos) { return ( 2 * pos ); }
    
    /**
     * @param pos
     *         current position.
     * @return The position of the right child.
     */
    protected int getRightChild(int pos) {
        return ( 2 * pos ) + 1;
    }
    
    /**
     * @param pos
     *         current position.
     * @return The position of the child (left or right) with the minimum value. If pos is leaf, return -1.
     */
    protected int getMinChild(int pos) {
        if (isLeaf(pos)) return -1;
        if (!hasLeftChild(pos)) return getRightChild(pos);
        if (!hasRightChild(pos)) return getLeftChild(pos);
        return heap[getLeftChild(pos)].compareTo(heap[getRightChild(pos)]) < 0 ? getLeftChild(pos) : getRightChild(pos);
    }
    
    /**
     * @param pos
     *         current position.
     * @return Whether left child exists.
     */
    protected boolean hasLeftChild(int pos) { return getLeftChild(pos) <= currentSize; }
    
    /**
     * @param pos
     *         current position.
     * @return Whether right child exists.
     */
    protected boolean hasRightChild(int pos) { return getRightChild(pos) <= currentSize; }
    
    /**
     * @param pos
     *         current position.
     * @return Whether left child AND right child do not exist.
     */
    protected boolean isLeaf(int pos) { return !hasLeftChild(pos) && !hasRightChild(pos); }
    
    /**
     * Switch the node at pos1 with the node at pos2
     *
     * @param pos1
     *         position of node 1
     * @param pos2
     *         position of node 2
     */
    protected void swap(int pos1, int pos2) {
        T element = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = element;
    }
}

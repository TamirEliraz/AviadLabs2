package lab1;

import java.awt.Point;

public interface PointList {
    static final int MAX_SIZE = 100;
    
    /**
     * Adds newPoint to the end of the list,
     * and moves cursor to newPoint.
     *
     * @param newPoint
     */
    void append(Point newPoint);
    
    /**
     * Removes all points in the list.
     */
    void clear();
    
    boolean isEmpty();
    
    boolean isFull();
    
    /**
     * @return If list is not empty moves the cursor to the first element of the list
     * and returns true. Otherwise, returns false.
     */
    boolean goToBeginning();
    
    /**
     * @return If list is not empty moves the cursor to the last element of the list
     * and returns true. Otherwise, returns false.
     */
    boolean goToEnd();
    
    /**
     * @return If the cursor is not in the last element of the list,
     * moves it to the next element and returns true. Otherwise, returns false.
     */
    boolean goToNext();
    
    /**
     * @return If the cursor is not in the first element of the list,
     * moves it to the previous element and returns true. Otherwise, returns false.
     */
    boolean goToPrior();
    
    /**
     * @return A copy of the point marked by the cursor, and null if the list is empty.
     */
    Point getCursor();            //
    
    /**
     * Outputs the Points in the list. If the list is empty, outputs "Empty list".
     * Intended for debugging purposes only.
     *
     * @return
     */
    String toString();
}

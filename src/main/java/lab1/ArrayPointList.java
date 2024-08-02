package lab1;

import java.awt.*;

public class ArrayPointList implements PointList {
    private Point[] list;
    private int currentSize;
    private int cursor;
    
    public ArrayPointList() { this(MAX_SIZE); }
    
    public ArrayPointList(int maxSize) {
        list = new Point[maxSize];
        clear();
    }
    
    @Override
    public void append(Point newPoint) {
        if (!isFull()) {
            goToEnd();
            list[++cursor] = newPoint;
            currentSize++;
        }
    }
    
    @Override
    public void clear() {
        currentSize = 0;
        cursor = -1;
    }
    
    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }
    
    @Override
    public boolean isFull() {
        return currentSize == list.length;
    }
    
    @Override
    public boolean goToBeginning() {
        return isEmpty() ? false : ( ( cursor = 0 ) == 0 );
    }
    
    @Override
    public boolean goToEnd() {
        return isEmpty() ? false : ( ( cursor = currentSize - 1 ) == currentSize - 1 );
    }
    
    @Override
    public boolean goToNext() {
        return cursor < currentSize - 1 && ( ++cursor == cursor );
    }
    
    @Override
    public boolean goToPrior() {
        return cursor > 0 && ( --cursor == cursor );
    }
    
    @Override
    public Point getCursor() {
        return isEmpty() ? null : new Point(list[cursor]);
    }
}

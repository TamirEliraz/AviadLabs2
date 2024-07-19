package lab3;

import lab4.List;
import lombok.Getter;
import lombok.Setter;

public class DLinkedList<T> implements List<T> {
    @Getter
    @Setter
    private class DNode {
        private T element;
        private DNode next, prev;
        
        public DNode(T element) {
            setElement(element);
            setPrev(null);
            setNext(null);
        }
        
        @Override
        public String toString() {
            return getClass().getSimpleName() + " " + element.toString();
        }
    }
    
    private DNode head, tail, cursor;
    private int size;
    
    public DLinkedList() { clear(); }
    
    @Override
    public void insert(T newElement) { // O(1)
        if (newElement == null) { throw new NullPointerException("newElement is null"); }
        if (!isEmpty()) {
            if (cursor == tail) {
                DNode temp = cursor;
                cursor.setNext(new DNode(newElement));
                cursor = cursor.getNext();
                tail = cursor;
                cursor.setPrev(temp);
            } else {
                DNode tempPrev = cursor;
                DNode tempNext = cursor.getNext();
                cursor.setNext(new DNode(newElement));
                cursor = cursor.getNext();
                cursor.setNext(tempNext);
                cursor.setPrev(tempPrev);
                tempNext.setPrev(cursor);
            }
        } else {
            head = tail = cursor = new DNode(newElement);
        }
        size++;
    }
    
    @Override
    public T remove() {
        if (isEmpty()) return null;
        T deletedValue = getCursor();
        if (head == tail) {
            clear();
            return deletedValue;
        } else if (cursor == head) {
            cursor = cursor.getNext();
            cursor.setPrev(null);
            head = cursor;
        } else if (cursor == tail) {
            cursor = cursor.getPrev();
            cursor.setNext(null);
            tail = cursor;
            goToBeginning();
        } else {
            DNode tempNext, tempPrev;
            tempNext = cursor.getNext();
            tempPrev = cursor.getPrev();
            tempNext.setPrev(tempPrev);
            tempPrev.setNext(tempNext);
            cursor = tempNext;
        }
        size--;
        return deletedValue;
    }
    
    @Override
    public T remove(T element) { // O(n)
        if (element == null || isEmpty()) return null;
        DNode search = head;
        while (search != tail && search.element != element) {
            search = search.getNext();
        }
        if (search == null) return null;
        if (search.element == element) {
            cursor = search;
            return remove();
        }
        return null;
    }
    
    @Override
    public void clear() { // O(1)
        head = tail = cursor = null; size = 0;
    }
    
    @Override
    public void replace(T newElement) { // O(1)
        if (isEmpty()) throw new RuntimeException("lab3.List is empty!");
        if (newElement == null) throw new NullPointerException("newElement is null.");
        cursor.element = newElement;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public boolean goToBeginning() { // O(1)
        if (isEmpty()) return false;
        cursor = head;
        return true;
    }
    
    @Override
    public boolean goToEnd() {
        if (isEmpty()) return false;
        cursor = tail;
        return true;
    }
    
    @Override
    public T getNext() {
        if (cursor == tail) return null;
        cursor = cursor.getNext();
        return getCursor();
    }
    
    @Override
    public T getPrev() {
        if (cursor == head) return null;
        cursor = cursor.getPrev();
        return getCursor();
    }
    
    @Override
    public T getCursor() {
        return isEmpty() ? null : cursor.element;
    }
    
    @Override
    public boolean hasNext() {
        return cursor != null && cursor.getNext() != null;
    }
    
    @Override
    public boolean hasPrev() {
        return cursor != null && cursor.getPrev() != null;
    }
    
    @Override
    public String toString() {
        if (isEmpty()) return "lab3.DLinkedList is empty";
        StringBuilder sb = new StringBuilder();
        DNode tempCursor = head;
        while (tempCursor != null) {
            sb.append(tempCursor.element).append(" → ");
            tempCursor = tempCursor.getNext();
        }
        return sb.substring(0, sb.length() - 3);
    }
}
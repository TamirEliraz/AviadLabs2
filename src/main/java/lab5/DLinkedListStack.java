package lab5; // package il.ac.telhai.ds.stack;


import lab3.DLinkedList;

// FILO / LIFO
public class DLinkedListStack<T> implements Stack<T> {
    private final DLinkedList<T> list;
    
    public DLinkedListStack() { list = new DLinkedList<>(); }
    
    @Override
    public void push(T t) {
        list.goToEnd();
        list.insert(t);
    }
    
    @Override
    public T pop() {
        list.goToEnd();
        return list.remove();
    }
    
    @Override
    public T top() {
        list.goToEnd();
        return list.getCursor();
    }
    
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (!list.goToEnd()) return "[]";
        do {
            sb.append(list.getCursor()).append(", ");
        }
        while (list.getPrev() != null);// [1, 2, ]
        return sb.substring(0, sb.length() - 2) + "]";
    }
}

package lab5; // package il.ac.telhai.ds.stack;

public interface Stack<T> {
    public void push(T t);
    public T pop();
    public T top();
    public boolean isEmpty();
}
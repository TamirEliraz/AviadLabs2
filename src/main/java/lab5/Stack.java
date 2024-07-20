package lab5;

public interface Stack<T> {
    public void push(T t);
    public T pop();
    public T top();
    public boolean isEmpty();
}
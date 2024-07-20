package lab6;

public class BinaryTree<T> implements BinaryTreeI<T>{
    
    @Override
    public BinaryTreeI<T> getLeft() {
        return null;
    }
    
    @Override
    public BinaryTreeI<T> getRight() {
        return null;
    }
    
    @Override
    public T getValue() {
        return null;
    }
    
    @Override
    public void setValue(T value) {
    
    }
    
    @Override
    public void setLeft(BinaryTreeI<T> left) {
    
    }
    
    @Override
    public void setRight(BinaryTreeI<T> right) {
    
    }
    
    @Override
    public boolean isLeaf() {
        return false;
    }
    
    @Override
    public int height() {
        return 0;
    }
    
    @Override
    public int size() {
        return 0;
    }
    
    @Override
    public void clear() {
    
    }
    
    @Override
    public String preOrder() {
        return "";
    }
    
    @Override
    public String preOrder(String separationBeforeVal, String separationAfterVal) {
        return "";
    }
    
    @Override
    public String inOrder() {
        return "";
    }
    
    @Override
    public String inOrder(String separationBeforeVal, String separationAfterVal) {
        return "";
    }
    
    @Override
    public String postOrder() {
        return "";
    }
    
    @Override
    public String postOrder(String separationBeforeVal, String separationAfterVal) {
        return "";
    }
}

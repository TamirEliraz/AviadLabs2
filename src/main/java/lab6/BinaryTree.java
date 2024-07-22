package lab6;

public class BinaryTree<T> implements BinaryTreeI<T> {
    private T value;
    private BinaryTreeI<T> left, right;
    
    public BinaryTree(T value) { this(value, null, null); }
    
    public BinaryTree(T value, BinaryTreeI<T> left, BinaryTreeI<T> right) {
        setValue(value);
        this.left = left;
        this.right = right;
    }
    
    
    @Override
    public BinaryTreeI<T> getLeft() { return left; }
    
    @Override
    public BinaryTreeI<T> getRight() { return right; }
    
    @Override
    public T getValue() { return value; }
    
    @Override
    public void setValue(T value) { this.value = value; }
    
    @Override
    public void setLeft(BinaryTreeI<T> left) { this.left = left; }
    
    @Override
    public void setRight(BinaryTreeI<T> right) { this.right = right; }
    
    @Override
    public boolean isLeaf() { return !hasRight() && !hasLeft(); }
    
    public boolean hasRight() { return this.right != null; }
    
    public boolean hasLeft() { return this.left != null; }
    
    @Override
    public int height() {
        return isLeaf() ? 0 : ( 1 + Math.max(
                hasRight() ? getRight().height() : 0,
                hasLeft() ? getLeft().height() : 0
        ) );
    }
    
    @Override
    public int size() {
        return 1 + ( hasRight() ? getRight().size() : 0 ) +
                ( hasLeft() ? getLeft().size() : 0 );
    }
    
    @Override
    public void clear() { setLeft(null); setRight(null); }
    
    @Override
    public String preOrder() {
        return preOrder(" ", " ");
    }
    
    @Override
    public String preOrder(String separationBeforeVal, String separationAfterVal) {
        return separationBeforeVal + getValue() + separationAfterVal
                + ( hasLeft() ? getLeft().preOrder(separationBeforeVal, separationAfterVal) : "" )
                + ( hasRight() ? getRight().preOrder(separationBeforeVal, separationAfterVal) : "" );
    }
    
    @Override
    public String inOrder() {
        return inOrder(" ", " ");
    }
    
    @Override
    public String inOrder(String separationBeforeVal, String separationAfterVal) {
        return ( hasLeft() ? getLeft().inOrder(separationBeforeVal, separationAfterVal) : "" )
                + separationBeforeVal + getValue() + separationAfterVal
                + ( hasRight() ? getRight().inOrder(separationBeforeVal, separationAfterVal) : "" );
    }
    
    @Override
    public String postOrder() {
        return postOrder(" ", " ");
    }
    
    @Override
    public String postOrder(String separationBeforeVal, String separationAfterVal) {
        return ( hasLeft() ? getLeft().postOrder(separationBeforeVal, separationAfterVal) : "" )
                + ( hasRight() ? getRight().postOrder(separationBeforeVal, separationAfterVal) : "" )
                + separationBeforeVal + getValue() + separationAfterVal;
    }
}

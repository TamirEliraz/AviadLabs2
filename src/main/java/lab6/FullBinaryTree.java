package lab6;

public class FullBinaryTree<T> extends BinaryTree<T> {
    public FullBinaryTree(T value) {
        this(value, null, null);
    }
    
    public FullBinaryTree(T value, FullBinaryTree<T> right, FullBinaryTree<T> left) {
        super(value, right, left);
        if (!hasRight() && hasLeft() || hasRight() && !hasLeft()) throw new RuntimeException();
    }
    
    @Override
    public void setRight(BinaryTreeI<T> right) {
        if (hasLeft() && right == null
                || !hasLeft() && right != null ||
                right != null && !right.getClass().equals(getClass())) throw new RuntimeException();
        super.setRight(right);
    }
    
    @Override
    public void setLeft(BinaryTreeI<T> left) {
        if (hasRight() && left == null ||
                !hasRight() && left != null ||
                left != null && !left.getClass().equals(getClass())) throw new RuntimeException();
        super.setLeft(left);
    }
}

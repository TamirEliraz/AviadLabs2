package lab8;

public class AVLTree<T extends Comparable<T>> {
    private int height;
    private T value;
    private AVLTree<T> left, right, parent;
    
    public AVLTree(T value) {
        this(value, null);
    }
    
    private AVLTree(T value, AVLTree<T> parent) {
        this.parent = parent;
        this.value = value;
        this.left = null; this.right = null; height = 0;
    }
    
    /**
     * Add the value to the tree
     *
     * @return the updated root of the tree.
     */
    public AVLTree<T> add(T value) {
        // add value
        if (getValue() == null || getValue().compareTo(value) == 0) this.value = value;
        else if (value.compareTo(getValue()) > 0) {
            if (!hasRight()) {
                this.right = new AVLTree<>(value, this);
            } else {
                getRight().add(value);
            }
        } else {
            if (!hasLeft()) {
                this.left = new AVLTree<>(value, this);
            } else {
                getLeft().add(value);
            }
        }
        updateHeight();
        // check that the tree is still AVL
        if (Math.abs(height - Math.min(getRightHeight(), getLeftHeight())) <= 1) {
            return this;
        }
        // check if Left-Left OR Left-Right
        if (getLeftHeight() > getRightHeight()) {
            // Left CANNOT be null
            if (getLeft().getLeftHeight() > getLeft().getRightHeight()) {
                // Left-Left
                updateLeftLeft();
            }
        }
        
        // check if Right-Left OR Right-Right
        else {
            // Right CANNOT be null
            if (getRight().getRightHeight() > getRight().getLeftHeight()) {
                // Right-Right
                updateRightRight();
            }
        }
        return null;
    }
    
    private void updateLeftLeft() {
        // give names to nodes
        AVLTree<T> k1, k2, x, y, z;
        
        // init nodes
        k2 = this;
        k1 = getLeft();
        x = k1.getLeft();
        y = k1.getRight();
        z = getRight();
        
        // update inner order
        k1.left = x;
        k1.right = k2;
        k2.left = y;
        k2.right = z;
        
        updateAllChildren(k1, k2, x, y, z);
    }
    
    private void updateRightRight() {
        // give names to nodes
        AVLTree<T> k1, k2, x, y, z;
        
        // init nodes
        k2 = this;
        k1 = k2.getRight();
        z = k2.getLeft();
        x = k1.getRight();
        y = k1.getLeft();
        
        // update inner order
        k1.right = x;
        k1.left = k2;
        k2.right = y;
        k2.left = z;
        
        updateAllChildren(k1, k2, x, y, z);
    }
    
    private void updateHeight() {
        if (isLeaf()) {
            this.height = 0;
        } else {
            this.height = 1 +
                    Math.max(
                            getLeftHeight(),
                            getRightHeight())
            ;
        }
    }
    
    /**
     * @return the value in this node
     */
    public T getValue() { return value; }
    
    /**
     * @return the left subTree of this node
     */
    public AVLTree<T> getLeft() { return left; }
    
    /**
     * @return the right subTree of this node
     */
    public AVLTree<T> getRight() { return right; }
    
    private boolean hasLeft() { return getLeft() != null; }
    
    private boolean hasRight() { return getRight() != null; }
    
    private boolean hasParent() { return parent != null; }
    
    private boolean isLeaf() { return !hasRight() && !hasLeft(); }
    
    private int getRightHeight() { return !hasRight() ? 0 : getRight().height; }
    
    private int getLeftHeight() { return !hasLeft() ? 0 : getLeft().height; }
    
    private void updateChild(AVLTree<T> oldChild, AVLTree<T> newChild) {
        newChild.parent = this;
        if (!hasRight()) this.left = newChild;
        else if (!hasLeft()) this.right = newChild;
        else if (oldChild.getValue().compareTo(right.getValue()) == 0) this.right = newChild;
        else this.left = newChild;
    }
    
    private void updateAllChildren(AVLTree<T> k1, AVLTree<T> k2, AVLTree<T> x, AVLTree<T> y, AVLTree<T> z) {
        // update "original root" parent
        if (k2.hasParent()) {
            k2.parent.updateChild(k2, k1);
        } else {
            k1.parent = null;
        }
        
        // update inner parents
        k2.parent = k1;
        x.parent = k1;
        y.parent = k2;
        z.parent = k2;
    }
    
    @Override
    public String toString() {
        return "AVLTree{" +
                "height=" + height +
                ", value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

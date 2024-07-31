package lab8;

public class AVLTree<T extends Comparable<T>> {
    private static int addCounter = 0;
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
        addCounter++;
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
            addCounter--;
            return this;
        }
        // check if Left-Left OR Left-Right
        if (getLeftHeight() > getRightHeight()) {
            // Left CANNOT be null
            if (getLeft().getLeftHeight() > getLeft().getRightHeight()) {
                // Left-Left
                updateLeftLeft();
            } else {
                // Left-Right
                updateLeftRight();
            }
        }
        
        // check if Right-Left OR Right-Right
        else {
            // Right CANNOT be null
            if (getRight().getRightHeight() > getRight().getLeftHeight()) {
                // Right-Right
                updateRightRight();
            } else {
                // Right-Left
                if ((Integer) value == 4) {
                }
                updateRightLeft();
            }
        }
        addCounter--;
        // if addCounter == 0, that means we got all the way back to our "original" root.
        // so, we go all the way up to the "real" root of the Tree.
        if (addCounter == 0) {
            updateHeight();
            AVLTree<T> cursor = this;
            while (cursor.hasParent()) {
                cursor = cursor.parent;
                cursor.updateHeight();
            }
            return cursor;
        }
        updateHeight();
        return this;
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
        
        updateAllChildrenOuter(k1, k2, x, y, z);
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
        
        updateAllChildrenOuter(k1, k2, x, y, z);
    }
    
    private void updateRightLeft() {
        AVLTree<T> k1, k2, k3, a, b, c, d;
        
        // init
        k3 = this;
        d = k3.getLeft();
        k1 = k3.getRight();
        k2 = k1.getLeft();
        a = k1.getRight();
        c = k2.getLeft();
        b = k2.getRight();
        
        // inner update
        k1.right = a;
        k1.left = b;
        k3.right = c;
        k3.left = d;
        k2.right = k1;
        k2.left = k3;
        
        updateAllChildrenInner(k1, k2, k3, a, b, c, d);
    }
    
    private void updateLeftRight() {
        AVLTree<T> k1, k2, k3, a, b, c, d;
        // init
        k3 = this;
        k1 = k3.getLeft();
        d = k3.getRight();
        a = k1.getLeft();
        k2 = k1.getRight();
        b = k2.getLeft();
        c = k2.getRight();
        
        // inner update
        k1.left = a;
        k1.right = b;
        k3.left = c;
        k3.right = d;
        k2.left = k1;
        k2.right = k3;
        
        updateAllChildrenInner(k1, k2, k3, a, b, c, d);
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
    
    public boolean hasParent() { return parent != null; }
    
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
    
    /**
     * left-right or right-left
     *
     * @param k1
     * @param k2
     * @param k3
     * @param a
     * @param b
     * @param c
     * @param d
     */
    private void updateAllChildrenInner(AVLTree<T> k1, AVLTree<T> k2, AVLTree<T> k3, AVLTree<T> a, AVLTree<T> b,
                                        AVLTree<T> c, AVLTree<T> d) {
        // update "original root" parent
        if (k3.hasParent()) {
            k3.parent.updateChild(k3, k2);
        } else {
            k2.parent = null;
        }
        
        if (a != null)
            a.parent = k1;
        if (b != null)
            b.parent = k1;
        if (c != null)
            c.parent = k3;
        if (d != null)
            d.parent = k3;

//        a.parent = b.parent = k1;
//        c.parent = d.parent = k3;
        k1.parent = k3.parent = k2;
        updateAllHeights(a, b, c, d, k1, k2, k3);
    }
    
    /**
     * left-left or right-right
     *
     * @param k1
     * @param k2
     * @param x
     * @param y
     * @param z
     */
    private void updateAllChildrenOuter(AVLTree<T> k1, AVLTree<T> k2, AVLTree<T> x, AVLTree<T> y, AVLTree<T> z) {
        // update "original root" parent
        if (k2.hasParent()) {
            k2.parent.updateChild(k2, k1);
        } else {
            k1.parent = null;
        }
        
        // update inner parents
        k2.parent = k1;
        if (x != null)
            x.parent = k1;
        if (y != null)
            y.parent = k2;
        if (z != null)
            z.parent = k2;
        
        updateAllHeights(x, y, z, k1, k2);
    }
    
    private void updateAllHeights(AVLTree<T>... args) {
        for (AVLTree<T> arg : args) {
            if (arg != null) {
                arg.updateHeight();
            }
        }
    }
    
    @Override
    public String toString() {
        return ( hasLeft() ? getLeft().toString() : "" )
                + " " + getValue() + " "
                + ( hasRight() ? getRight().toString() : "" );
//        return "AVLTree{" +
//                "height=" + height +
//                ", value=" + value +
//                ", left=" + left +
//                ", right=" + right +
//                '}';
    }
}

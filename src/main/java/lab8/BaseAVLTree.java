package lab8;

import lombok.Getter;
import lombok.Setter;

@Setter
class BaseAVLTree<T extends Comparable<T>> {
    private int height;
    
    public int getHeight() { this.height = isLeaf() ? 0 : 1 + Math.max(getHeightLeft(), getHeightRight()); return height; }
    
    public int getHeightLeft() { return hasLeft() ? getLeft().getHeight() : 0; }
    
    public int getHeightRight() { return hasRight() ? getRight().getHeight() : 0; }
    
    public boolean isLeaf() { return !hasRight() && !hasLeft(); }
    
    
    /**
     * @return The value in this node.
     */
    @Getter
    private T value;
    @Getter
    private AVLTree<T> parent;
    /**
     * @return The left subTree of this node.
     */
    @Getter
    private AVLTree<T> left;
    /**
     * @return The right subTree of this node.
     */
    @Getter
    private AVLTree<T> right;
    
    protected boolean hasRight() { return getRight() != null; }
    
    protected boolean hasLeft() { return getLeft() != null; }
    
    protected boolean hasParent() { return getParent() != null; }
    
    public void setLeft(AVLTree<T> value) {
        this.left = value;
        if (value != null) value.setParent((AVLTree<T>) this);
    }
    
    public void setRight(AVLTree<T> value) {
        this.right = value;
        if (value != null) value.setParent((AVLTree<T>) this);
    }
    
    protected void baseAdd(T value) {
        if (value != null) {
            if (value.compareTo(getValue()) > 0) {
                if (hasRight()) getRight().add(value);
                else {
                    setRight(new AVLTree<>(value));
                    makeItBetter((AVLTree<T>) this);
                }
            } else {
                if (hasLeft()) getLeft().add(value);
                else {
                    setLeft(new AVLTree<>(value));
                    makeItBetter((AVLTree<T>) this);
                }
            }
        }
    }
    
    public void setParent(AVLTree<T> parent, AVLTree<T> child) {
        setParent(parent);
        if (child != null && parent != null) {
            if (parent.getHeightRight() > parent.getHeightLeft()) parent.setLeft(child);
            else parent.setRight(child);
        }
    }
    
    @Override
    public String toString() {
        return ( hasLeft() ? getLeft().toString() : "" )
                + " " + getValue().toString() + " (" + ( hasParent() ? getParent().getValue() : "" ) + ") "
                + ( hasRight() ? getRight().toString() : "" );
    }
    
    private static <T extends Comparable<T>> void makeItBetter(AVLTree<T> root) {
        boolean end = false;
        while (root != null && !end) {
            AVLTree<T> parent = root.getParent();
            if (root.getHeight() - Math.min(root.getHeightLeft(), root.getHeightRight()) > 1) {
                switch (Direction.getInstance(root)) {
                    case Direction.LEFT_RIGHT -> {
                        AVLTree<T> k3 = root,
                                k1 = root.getLeft(),
                                d = root.getRight(),
                                a = k1.getLeft(),
                                k2 = k1.getRight(),
                                b = k2.getLeft(),
                                c = k2.getRight();
                        parent = k3.getParent();
                        k1.setLeft(a);
                        k1.setRight(b);
                        k3.setLeft(c);
                        k3.setRight(d);
                        k2.setParent(parent, k2);
                        k2.setLeft(k1);
                        k2.setRight(k3);
                        end = true;
                    }
                    case Direction.RIGHT_LEFT -> {
                        AVLTree<T> k3 = root,
                                k1 = root.getRight(),
                                d = root.getLeft(),
                                a = k1.getRight(),
                                k2 = k1.getLeft(),
                                b = k2.getRight(),
                                c = k2.getLeft();
                        parent = k3.getParent();
                        k1.setRight(a);
                        k1.setLeft(b);
                        k3.setRight(c);
                        k3.setLeft(d);
                        k2.setParent(parent, k2);
                        k2.setRight(k1);
                        k2.setLeft(k3);
                        end = true;
                    }
                    case LEFT_LEFT -> {
                        AVLTree<T> k2 = root,
                                k1 = k2.getLeft(),
                                z = k2.getRight(),
                                x = k1.getLeft(),
                                y = k1.getRight();
                        parent = k2.getParent();
                        k2.setLeft(y);
                        k2.setRight(z);
                        k1.setParent(parent, k1);
                        k1.setLeft(x);
                        k1.setRight(k2);
                        end = true;
                    }
                    case RIGHT_RIGHT -> {
                        AVLTree<T> k2 = root,
                                k1 = k2.getRight(),
                                z = k2.getLeft(),
                                x = k1.getRight(),
                                y = k1.getLeft();
                        parent = k2.getParent();
                        k2.setRight(y);
                        k2.setLeft(z);
                        k1.setParent(parent, k1);
                        k1.setRight(x);
                        k1.setLeft(k2);
                        end = true;
                    }
                    case null, default -> { }// root = parent; }
                }
//                if (Direction.getInstance(root) == null) root = root.getParent();
            } //else {
            root = parent;
//            }
            System.out.println("root: " + root);
//            root = parent;
//            return;
        }
    }
}

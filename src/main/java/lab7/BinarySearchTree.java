package lab7;

import java.util.Dictionary;

public class BinarySearchTree<T extends Comparable<T>> {
    
    private BstNode root;
    private int size;
    
    // Binary Search Tree Node
    private class BstNode {
        private T val;
        private BstNode left, right;
        
        private BstNode(T val) {
            this.val = val;
            left = null;
            right = null;
        }
        
        @Override
        public String toString() {
            return "BstNode{" +
                    "val=" + val +
                    '}';
        }
    }
    
    public enum Direction {
        LEFT, RIGHT
    }
    
    /**
     * record-like class (struct) that contains parent, cursor and the direction (what child is cursor to parent)
     */
    private class BstNode2 {
        public final BstNode parent, cursor;
        public final Direction direction;
        
        public BstNode2(BstNode parent, BstNode child, Direction direction) {
            this.parent = parent; this.cursor = child;
            this.direction = direction;
        }
    }
    
    // Returns the val given a path from the root.
// Used for testing. DO NOT DELETE.
    public T getValInPath(Direction... direction) {
        BstNode node = root;
        for (Direction d : direction) {
            if (d.equals(Direction.LEFT) && node.left != null)
                node = node.left;
            else if (d.equals(Direction.RIGHT) && node.right != null)
                node = node.right;
            else
                return null;
        }
        return node.val;
    }
    
    /**
     * Constructs an empty BinarySearchTree.
     */
    public BinarySearchTree() {
        root = new BstNode(null);
        size = 0;
//        new BstNode2(root, root);
    }
    
    /**
     * returns the number of elements in the tree
     */
    public int size() {
        // O(1)
        return size;
    }
    
    /**
     * Adds the object value to the tree as a leaf according to the parameter.
     *
     * @param val
     * @return True, if the element was added. Otherwise false.
     */
    public boolean add(T val) {
        BstNode2 res = find(val);
        if (res == null) {
            root.val = val;
        } else if (val.compareTo(res.cursor.val) == 0) {
            return false;
        } else if (val.compareTo(res.cursor.val) > 0) {
            res.cursor.right = new BstNode(val);
        } else {
            res.cursor.left = new BstNode(val);
        }
        size++;
        return true;
    }
    
    /**
     * Search for a value.
     *
     * @param val
     *         of type {@link T T}
     * @return If the tree is empty (root==null), then return null.
     * if the tree contains val, then return a {@link BstNode2 BstNode2} where "cursor" is a {@link BstNode BstNode}
     * with the value val, "parent" is
     * its parent and "direction" is a {@link Direction Direction} to what child is cursor.
     * if the tree does not contain val, then return a BstNode2 where "cursor" is what would be the "parent" of the
     * BstNode that contains the value val.
     */
    private BstNode2 find(T val) {
        if (size() == 0) { return null; }
        BstNode cursor = root;
        BstNode parent = null;
        Direction direction = null;
        do {
            if (val.compareTo(cursor.val) == 0) return new BstNode2(parent, cursor, direction);
            else if (val.compareTo(cursor.val) > 0) {
                if (cursor.right == null) {
                    return new BstNode2(parent, cursor, direction);
                }
                parent = cursor;
                direction = Direction.RIGHT;
                cursor = cursor.right;
            } else {
                if (cursor.left == null) {
                    return new BstNode2(parent, cursor, direction);
                }
                parent = cursor;
                direction = Direction.LEFT;
                cursor = cursor.left;
            }
        } while (true);
    }
    
    /**
     * Removes the object in the tree which is equal to the parameter.
     * Nothing is done if not found
     *
     * @param val:
     *         the object to be looked for in the tree
     * @return True, if the element was removed. Otherwise false.
     */
    public boolean remove(T val) {
        BstNode2 res = find(val);
        if (res == null || res.cursor.val.compareTo(val) != 0) return false;
        if (res.cursor == root && size() == 1) {
            root.val = null;
        } else if (res.cursor.left == null && res.cursor.right == null) {
            switch (res.direction) {
                case RIGHT -> res.parent.right = null;
                case LEFT -> res.parent.left = null;
            }
        } else if (res.cursor.left == null) {
            switch (res.direction) {
                case RIGHT -> res.parent.right = res.cursor.right;
                case LEFT -> res.parent.left = res.cursor.right;
            }
        } else if (res.cursor.right == null) {
            switch (res.direction) {
                case RIGHT -> res.parent.right = res.cursor.left;
                case LEFT -> res.parent.left = res.cursor.left;
            }
        } else {
            // we need to remove a node that contains 2 children
            // so we go one step left, and all the way right,
            // or one step right, and all the way left
            BstNode temp = res.cursor.right;
            BstNode tempParent = null;
            while (temp.left != null) {
                tempParent = temp;
                temp = temp.left;
            }
            res.cursor.val = temp.val;
            if (tempParent != null)
                tempParent.left = null;
        }
        size--;
        return true;
    }
    
    /**
     * Looks for an object which is equal to the parameter.
     *
     * @param val:
     *         the object to be looked for in the tree
     * @return true if the tree contains this object. Otherwise, return false.
     */
    public boolean contains(T val) {
        BstNode2 res = find(val);
        return res != null && res.cursor.val.compareTo(val) == 0;
    }
    
    /**
     * Looks for the minimal object in the tree, which is greater than or equal to
     * the parameter.
     *
     * @param val:
     *         the object to be looked for in the tree
     * @return a reference to the found object.
     */
    public T findGe(T val) {
        BstNode2 temp = find(val);
        if (temp == null) return null;
        if (temp.cursor.val.compareTo(val) == 0) return val;
        while (temp != null && temp.direction != Direction.LEFT && temp.parent != null) {
            temp = find(temp.parent.val);
        }
        if (temp == null || ( temp.parent == null && ( temp.direction == null || temp.direction == Direction.RIGHT ) ))
            return null;
        if (temp.cursor.val.compareTo(val) > 0) return temp.cursor.val;
        BstNode temp2 = temp.parent;
        if (temp2 == null) return null;
        if (temp2.right != null) {
            temp2 = temp2.right;
            while (temp2.left != null) temp2 = temp2.left;
        }
        if (temp2.val.compareTo(val) > 0) return temp2.val;
        return null;
    }
    
    @Override
    public String toString() {
        return root == null ? "" : root.val.toString();
    }
}
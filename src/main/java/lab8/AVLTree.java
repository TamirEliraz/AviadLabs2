package lab8;

import lombok.Getter;

@Getter
public class AVLTree<T extends Comparable<T>> extends BaseAVLTree<T> {
    private static int addCounter = 0;
    
    public AVLTree(T value) { setValue(value); setLeft(null); setRight(null); setParent(null); setHeight(0); }
    
    /**
     * Add the value to the Tree.
     *
     * @return The updated root of the Tree.
     */
    public AVLTree<T> add(T value) {
        addCounter++;
        baseAdd(value);
        if (--addCounter == 0) {
            AVLTree<T> cursor = this;
            while (cursor.hasParent()) cursor = cursor.getParent();
            return cursor;
        }
        return this;
    }
}

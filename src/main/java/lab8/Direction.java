package lab8;

enum Direction {
    //LEFT, RIGHT,
    LEFT_LEFT, LEFT_RIGHT, RIGHT_LEFT, RIGHT_RIGHT;
    
    public static <T extends Comparable<T>> Direction getInstance(AVLTree<T> tree) {
        if (tree.getHeight() - tree.getHeightLeft() == 2) {
            // right
            AVLTree<T> temp = tree.getRight();
            if (!temp.hasLeft()) return RIGHT_RIGHT;
            if (!temp.hasRight()) return RIGHT_LEFT;
            return tree.getRight().getHeightLeft() > tree.getRight().getHeightRight() ? RIGHT_LEFT : RIGHT_RIGHT;
        } else if (tree.getHeight() - tree.getHeightRight() == 2) {
            // left
            AVLTree<T> temp = tree.getLeft();
            if (!temp.hasLeft()) return LEFT_RIGHT;
            if (!temp.hasRight()) return LEFT_LEFT;
            return tree.getLeft().getHeightLeft() > tree.getLeft().getHeightLeft() ? LEFT_RIGHT : LEFT_LEFT;
        }
        return null;
    }
}

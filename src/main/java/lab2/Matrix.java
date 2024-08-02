package lab2;

public interface Matrix {
    public static final int MAX_SIZE = 100;
    /**
     * PreCondition: 1 ≤ i,j ≤ n
     *
     * @param row
     * @param col
     * @return The value of the element at position (i,j).
     */
    double get(int row, int col);
    
    /**
     * PreCondition: 1 ≤ i,j ≤ n.<br>
     * PostCondition: update the value of the element at position (i,j) to x.
     *
     * @param row
     * @param col
     * @param value
     */
    void set(int row, int col, double value);
    
    /**
     * PostCondition: replace the current matrix wıth its transpose.
     */
    void transpose();
    
    /**
     * @return A new matrix which equals to the transpose of the current matrix.
     */
    Matrix getTranspose();
}

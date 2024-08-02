package lab2;

public class DiagonalMatrix implements Matrix {
    private boolean isTransposed;
    private double[] matrix;
    
    /**
     * Initializes a size*size diagonal matrix using an array of length 2*size-1.
     *
     * @param size
     *         size>0
     */
    public DiagonalMatrix(int size) {
        if (size <= 0) throw new RuntimeException("Size must be positive (size=" + size + ")");
        matrix = new double[2 * size - 1];
        isTransposed = false;
    }
    
    /**
     * Initializes an MAX_SIZE * MAX_SIZE diagonal matrix.
     */
    public DiagonalMatrix() {
        this(MAX_SIZE);
    }
    
    @Override
    public double get(int row, int col) {
        // 7652134
        // 2134
        // 5213
        // 6521
        // 7652
        checkBounds(row, col);
        while (row > 0 && col > 0) { row--; col--; }
        int tRow = getRow(row, col),
                tCol = getCol(row, col);
        return tCol == 0 ? matrix[getSize() - 1 - tRow] : matrix[getSize() - 1 + tCol];
    }
    
    @Override
    public void set(int row, int col, double value) {
        checkBounds(row, col);
        while (row > 0 && col > 0) { row--; col--; }
        int tRow = getRow(row, col),
                tCol = getCol(row, col);
        if (tCol == 0) matrix[getSize() - 1 - tRow] = value;
        else matrix[getSize() - 1 + tCol] = value;
    }
    
    @Override
    public void transpose() {
        isTransposed = !isTransposed;
    }
    
    @Override
    public Matrix getTranspose() {
        DiagonalMatrix temp = new DiagonalMatrix(getSize());
        transpose();
        temp.isTransposed = this.isTransposed;
        for (int x = 1; x <= getSize(); x++) temp.set(x, 1, get(x, 1));
        for (int y = 1; y <= getSize(); y++) temp.set(1, y, get(1, y));
        transpose();
        return temp;
    }
    
    /**
     * @return The size of the Matrix (number of rows/cols)
     */
    private int getSize() { return ( matrix.length + 1 ) / 2; }
    
    private void checkBounds(int row, int col) {
        if (row <= 0 || getSize() < row || col <= 0 || getSize() < col)
            throw new IndexOutOfBoundsException("(" + row + ", " + col + ") is out of bounds (" + getSize() + ", " + getSize() +
                    ")");
    }
    
    /**
     * @return The matrix in its natural n*n form as a string (with \t between entries of the
     * same row and \n between rows)
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 1; row <= getSize(); row++) {
            for (int col = 1; col <= getSize() - 1; col++) {
                sb.append(get(row, col)).append("\t");
            }
            sb.append(get(row, getSize())).append("\n");
        }
        return sb.toString();
    }
    
    private int getRow(int row, int col) { return !isTransposed ? row : col; }
    
    private int getCol(int row, int col) { return !isTransposed ? col : row; }
}

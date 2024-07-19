package lab4;

import lab3.DLinkedList;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class SparseMatrix<T> implements Matrix<T> {
    @Getter
    @Setter
    private class SparseMatrixEntry {
        private T value;
        private int row;
        private int col;
        
        public SparseMatrixEntry(int row, int col, T val) { setValue(val); setRow(row); setCol(col); }
    }
    
    private T defaultValue;
    private int size;
    private boolean isTransposed;
    private final DLinkedList<SparseMatrixEntry> list;
    
    public SparseMatrix(T defaultValue) { this(MAX_SIZE, defaultValue); }
    
    public SparseMatrix(int size, T defaultValue) {
        setSize(size);
        setDefaultValue(defaultValue);
        list = new DLinkedList<>();
        isTransposed = false;
    }
    
    private SparseMatrixEntry find(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException("row or col (or both) out of bounds!");
        }
        if (!list.goToBeginning()) return null;
        do {
            if (list.getCursor().getRow() == row &&
                    list.getCursor().getCol() == col) {
                return list.getCursor();
            }
        } while (list.getNext() != null);
        return null;
    }
    
    @Override
    public T get(int row, int col) {
        SparseMatrixEntry res = find(getTRow(row, col), getTCol(row, col));
        return res != null ? res.getValue() : defaultValue;
    }
    
    @Override
    public void set(int row, int col, T element) {
        SparseMatrixEntry res = find(getTRow(row, col), getTCol(row, col));
        if (res == null) {
            list.insert(new SparseMatrixEntry(getTRow(row, col), getTCol(row, col), element));
        } else {
            res.setValue(element);
        }
    }
    
    @Override
    public void transpose() {
        isTransposed = !isTransposed;
    }
    
    private int getTRow(int row, int col) {
        return !isTransposed ? row : col;
    }
    
    private int getTCol(int row, int col) {
        return !isTransposed ? col : row;
    }
}

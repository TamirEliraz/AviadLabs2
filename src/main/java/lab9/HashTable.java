package lab9;

import lab3.DLinkedList;

public class HashTable<V> {
    private DLinkedList<V>[] table;
    public static final int DEF_MAX_HASH_SIZE = 10;
    
    /**
     * constructor
     * constructs a hash-table of max-size "DEF_MAX_HASH_SIZE".
     */
    public HashTable() { this(DEF_MAX_HASH_SIZE); }
    
    /**
     * constructs a hash-table of size 'hashSize'.
     *
     * @param hashSize,
     *         the size of the constructed hash-table.
     */
    public HashTable(int hashSize) {
        table = new DLinkedList[hashSize];
    }
    
    /**
     * @param val
     * @return true if the hash-table contains val, otherwise return false
     */
    public boolean contains(V val) {//O(n)
        DLinkedList<V> list = getListAt(val);
        if (!list.goToBeginning()) return false; // list is empty
        do {
            if (list.getCursor().equals(val)) return true;
        } while (list.getNext() != null); // iterate through the list
        return false;
    }
    
    /**
     * Add val to the hash-table.
     *
     * @param val
     * @return If the val has already existed in the the hash-table, it will not be
     * added again. Return true if the val was added successfully. Otherwise
     * return false.
     */
    public boolean add(V val) {
        if (contains(val)) return false;
        DLinkedList<V> list = getListAt(val);
        list.insert(val);
        return true;
    }
    
    /**
     * Remove val from the hash-table.
     *
     * @param val
     * @return Return true if the val was removed successfully. Otherwise return
     * false.
     */
    public boolean remove(V val) {
        return ( getListAt(val).remove(val) != null );
    }
    
    /**
     * clear all the data in the hash-table
     */
    public void clear() {
        for (int row = 0; row < table.length; row++) {
            table[row] = null;
        }
    }
    
    /**
     * @return true if the hash-table is empty, otherwise return false.
     */
    public boolean isEmpty() { // O(table_length)
        for (DLinkedList<V> vdLinkedList : table) {
            if (vdLinkedList != null && !vdLinkedList.isEmpty()) return false;
        }
        return true;
    }
    
    private DLinkedList<V> getListAt(V val) {
        int pos = Math.abs(val.hashCode()) % table.length;
        DLinkedList<V> res = table[pos];
        if (res == null) {
            table[pos] = new DLinkedList<>();
            return table[pos];
        }
        return res;
    }
    
}
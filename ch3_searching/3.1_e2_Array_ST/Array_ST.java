import java.util.Scanner;

import Queue.Queue;

/**
 * @author Peng
 * 
 * Problem Description:
 *      Develop a symbol-table implementation Array_ST.java that 
 *      uses an (unordered) array as the underlying data structure
 *      to implement our basic symbol table API.
 */
public class Array_ST<Key, Value> {
    
    private static final int INIT_SIZE = 8;

    private Value[] vals;   // symbol table values
    private Key[] keys;     // symbol table keys
    private int n = 0;      // number of elements in symbol table
    
    public Array_ST() {
        keys = (Key[])   new Object[INIT_SIZE];
        vals = (Value[]) new Object[INIT_SIZE];
    }
    
    // is the symbol table empty?
    public boolean isEmpty() {
        return n == 0;
    }
    
    // return the number of key-value pairs in the symbol table
    public int size() {
        return n;
    }
    
    // insert the key-value pair into the symbol table
    public void put(Key key, Value val) {
        delete(key);
        if(n >= vals.length)  resize(2*n);
        
        vals[n] = val;
        keys[n] = key;
        n++;
    }
    
    // resize the parallel arrays to the given capacity
    private void resize(int capacity) {
        Key[]   tempk = (Key[])   new Object[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++)  tempk[i] = keys[i];
        for (int i = 0; i < n; i++)  tempv[i] = vals[i];
        keys = tempk;
        vals = tempv;
    }
    
    // remove given key (and associated value)
    public void delete(Key key) {
        for (int i = 0; i < n; i++) {
            if (key.equals(keys[i])) {
                keys[i] = keys[n-1];
                vals[i] = vals[n-1];
                keys[n-1] = null;
                vals[n-1] = null;
                n--;
                if (n > 0 && n == keys.length/4) resize(keys.length/2);
                return;
            }
        }
    }
    
    public Value get(Key key) {
        for (int i = 0; i < n; i++)
            if (keys[i].equals(key)) return vals[i];
        return null;
    }
    
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < n; i++)
            queue.enqueue(keys[i]);
        return queue;
    }
    
    // test code
    public static void main(String[] args) {
        Array_ST<String, Integer> st = new Array_ST<String, Integer>();
        
        Scanner scan = new Scanner(System.in);
        for (int i = 0; scan.hasNext(); i++) {
            String key = scan.next();
            st.put(key, i);
        }
        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
        
        scan.close();
    }

}

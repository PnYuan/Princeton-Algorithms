
/**
 * @author Peng
 * implementation of heap sort
 */
public class Heap {

    // This class should not be instantiated.
    private Heap() { }
    
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param pq the array to be sorted
     */
    public static <Key extends Comparable<Key>> void sort(Key[] pq) {
        int n = pq.length;
        for(int k = n/2; k >= 1; k--)
            sink(pq, k, n);
        while (n > 1) {
            exch(pq, 1, n);  // biggest to the end of the array
            sink(pq, 1, --n);  //continue for the rest
        }
    }
    
    /**
     * sink operation, biggest -> root
     * @param pq is the array
     * @param k is the node index 
     * @param n is the length
     */
    private static <Key extends Comparable<Key>> void sink(Key[] pq, int k, int n) {
        while(2*k <= n){
            int j = 2*k;  //left child index
            if(j < n && less(pq, j, j+1)) j++; // right child is bigger
            if(!less(pq,k,j))  break;  // there is no need for more sink
            exch(pq, k, j);
            k = j;  // continue down
        }
    }
    
    /**
     * swim operation, biggest -> root
     * @param pq is the array
     * @param k is the node index 
     * @param n is the length
     */
    private static <Key extends Comparable<Key>> void swim(Key[] pq, int k, int n) {
        while(k/2 > 1 && less(pq, k/2, k)) { // parent is smaller
            exch(pq, k, k/2);
            k = k/2;
        }
    }
    
    private static <Key extends Comparable<Key>> boolean less(Key[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }
    
    private static <Key extends Comparable<Key>> void exch(Key[] pq, int i, int j) {
        Key swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }
    
    // print array to standard output
    private static <Key extends Comparable<Key>> void show(Key[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.printf(a[i] + " ");
        }
    }
        
    // test code
    public static void main(String[] args) {
        Integer[] a = {1,4,2,7,4,2,9};
        Heap.sort(a);
        show(a);
    }
    
}

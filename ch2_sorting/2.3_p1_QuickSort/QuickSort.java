import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {

    // This class should not be instantiated.
    private QuickSort() { }
    
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static <Key extends Comparable<Key>> void sort(Key[] a, int lo, int hi) { 
        if (hi <= lo) return;  // recursion condition
        int j = partition(a, lo, hi);

        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }
    
    // quicksort the subarray from a[lo] to a[hi]
    // by partition the array into three parts
    private static <Key extends Comparable<Key>> void sort_3way(Key[] a, int lo, int hi) { 
        if (hi <= lo) return;
        
        int lt = lo, gt = hi;
        Key v = a[lo];
        int i = lo;
        
        // Partitioning operation
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }
    
    /**
     * The entry a[j] is in its final place in the array, for some j.
     * No entry in a[lo] through a[j-1] is greater than a[j].
     * No entry in a[j+1] through a[hi] is less than a[j].
     * 
     * @return the index j.
     */
    private static <Key extends Comparable<Key>> int partition(Key[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Key v = a[lo];
        while (true) { 
            while(less(a[++i], v)) {  // find the first bigger item on lo to swap
                if (i == hi) break;
            }
            while(less(v, a[--j])) {  // find the first smallest item on lo to swap 
                if (j == lo) break;  // redundant since a[lo] acts as sentinel
            }
            // check if pointers cross
            if (i >= j) break;
            exch(a, i, j);
            
            // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        }
        // put partitioning item v at a[j]
        exch(a, lo, j);
        return j;
    }
    
    // is v < w ?
    private static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }
    
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    public static void main(String[] args) {
        Integer[] a ={6,2,3,1,2,1,4,5};
        sort_3way(a, 0, a.length - 1);
        for(int i : a){
            System.out.printf(i + " ");
        }
    }

}

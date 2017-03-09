import java.util.Comparator;

public class MergeSort {
    
    public MergeSort() { }
    
    /**************************************************************************************************************
     * Using Comparator
     ***************************************************************************************************************/
    
    /**
     * merge-sort a[lo..hi] using auxiliary array aux[lo..hi] with natural order
     * here we use recursion approach
     * 
     * @param a is the raw array to be sorted
     * @param aux is the auxiliary array space to store copies
     * @param lo is the first index of a for recursion
     * @param hi is the last index of a for recursion
     */
    public void merge_sort(Object[] a, Object[] aux, int lo, int hi, Comparator<Object> comparator) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        
        // recursive for top-down merge
        merge_sort(a, aux, lo, mid, comparator);
        merge_sort(a, aux, mid + 1, hi, comparator);
        merge(a, aux, lo, mid, hi, comparator);
    }
    
    
    /**
     * stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi] with natural order
     * 
     * this is a merge-in-place method
     * 
     * @param a is the raw array to be merge after partly sorted
     * @param aux is the auxiliary array space to store copies
     * @param lo
     * @param mid
     * @param hi
     */
    public void merge(Object[] a, Object[] aux, int lo, int mid, int hi, Comparator<Object> comparator) {
        assert isSorted(a, lo, mid, comparator);  // check if two halves are sorted
        assert isSorted(a, mid+1, hi, comparator);
        
        // make a copy
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        
        // merging
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)   a[k] = aux[j++];  // first half is sorted over
            else if (j > hi)    a[k] = aux[i++];  // second half is sorted over
            else if (less(aux[j], aux[i], comparator)) 
                                a[k] = aux[j++];  // compare and merge
            else                a[k] = aux[i++];
        }
    }
    
    /**
     * compare two items with natural order, using Comparator
     * @param x
     * @param y
     * @param comparator is Comparator class for comparing
     * @return true if x < y
     */
    private boolean less(Object x, Object y, Comparator<Object> comparator) {
        return comparator.compare(x, y) < 0;
    }
    
    /**
     * check if is sorted using Comparator
     * @param a is the raw array to be checked
     * @param lo
     * @param hi
     * @return true if a has been sorted
     */
    private boolean isSorted(Object[] a, int lo, int hi, Comparator<Object> comparator) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1], comparator)) return false;
        return true;
    }
    
    /**************************************************************************************************************
     * Using Comparable
     ***************************************************************************************************************/
    
    /**
     * merge-sort a[lo..hi] using auxiliary array aux[lo..hi] with comparable interface
     * here we use recursion approach
     * 
     * @param a is the raw array to be sorted
     * @param aux is the auxiliary array space to store copies
     * @param lo is the first index of a for recursion
     * @param hi is the last index of a for recursion
     */
    public <Key extends Comparable<Key>> void merge_sort(Key[] a, Key[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        
        // recursive for in-place merge
        merge_sort(a, aux, lo, mid);
        merge_sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }
    
    /**
     * stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi] with comparable interface
     * 
     * @param a is the raw array to be merge after partly sorted
     * @param aux is the auxiliary array space to store copies
     * @param lo
     * @param mid
     * @param hi
     */
    public <Key extends Comparable<Key>> void merge(Key[] a, Key[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);  // check if two halves are sorted
        assert isSorted(a, mid+1, hi);
        
        // make a copy
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        
        // merging
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];  // first half is sorted over
            else if (j > hi)               a[k] = aux[i++];  // second half is sorted over
            else if (less(aux[j], aux[i])) a[k] = aux[j++];  // compare and merge
            else                           a[k] = aux[i++];
        }
    }
    
    /**
     * compare two items, using Comparable interface
     * @param x
     * @param y
     * @return true if x < y
     */
    private <Key extends Comparable<Key>> boolean less(Key x, Key y) {
        return x.compareTo(y) < 0;
    }
    
    /**
     * check if is sorted using Comparable
     * @param a is the raw array to be checked
     * @param lo
     * @param hi
     * @return true if a has been sorted
     */
    private <Key extends Comparable<Key>> boolean isSorted(Key[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    } 
    
    /**************************************************************************************************************
     * Improvement of Merge-sort
     ***************************************************************************************************************/
    
    private static final int CUTOFF = 7;  // cutoff to insertion sort
    
    /**
     * merge sort with improvement skills
     * @param a is the array to be sorted
     */
    public <Key extends Comparable<Key>> void merge_sort_x(Key[] a) {
        Key[] aux = a.clone();  // Eliminate the copy to the auxiliary array
        merge_sort_x(aux, a, 0, a.length-1);  
        assert isSorted(a, 0, a.length-1);
    }

    /**
     * new merge sort with improvement skills
     * 
     * @param src is the auxiliary array space to store copies 
     * @param dst is the raw array to be merge after partly sorted
     * @param lo
     * @param hi
     */
    private <Key extends Comparable<Key>> void merge_sort_x(Key[] src, Key[] dst, int lo, int hi) {
        if (hi <= lo + CUTOFF) { 
            insertionSort(dst, lo, hi);  // using insertion for small array to reduce runtime
            return;
        }
        
        // recursion
        int mid = lo + (hi - lo) / 2;
        merge_sort_x(dst, src, lo, mid);
        merge_sort_x(dst, src, mid+1, hi);

        // if (!less(src[mid+1], src[mid])) {
        //    for (int i = lo; i <= hi; i++) dst[i] = src[i];
        //    return;
        // }

        // using System.arraycopy() is a bit faster than the above loop
        if (!less(src[mid+1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi);
    }
    
    /**
     * For improvement of merge-sort, using insertion sort for small part
     * sort from a[lo] to a[hi] using insertion sort
     * @param a
     * @param lo
     * @param hi
     */
    private <Key extends Comparable<Key>> void insertionSort(Key[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    

    /**************************************************************************************************************
     * Implementation of Buttom-up way
     ***************************************************************************************************************/
    
    /**
     * merge_sort using buttom-up pattern
     * @param a is the array to be sorted
     */
    public <Key extends Comparable<Key>> void merge_sort_bu(Key[] a) {
        int n = a.length;
        Key[] aux = a.clone();  // Eliminate the copy to the auxiliary array
        
        for (int len = 1; len < n; len *= 2) {  // len refers to the length of each step's part-array
            for (int lo = 0; lo < n-len; lo += len+len) {
                int hi = Math.min(lo+len+len-1, n-1);
                merge_sort_x(a, aux, lo, hi);
            }
        }
        assert isSorted(a, 0, a.length);
    }
    
    
}















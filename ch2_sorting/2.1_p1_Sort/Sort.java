import java.util.Comparator;

/**
 * @author Peng
 * 
 * implementation of some typical sorting algorithms
 * here we code by using Comparator or not
 */
public class Sort {
    
    //constructor
    public Sort() {}

    /************************************************************************
     * selection sort
     *************************************************************************/ 
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public void selection_sort(@SuppressWarnings("rawtypes") Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
//            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }
    
    /**
    * Rearranges the array in ascending order, using a comparator.
    * @param a the array
    * @param comparator the comparator specifying the order
    */
    public void selection_sort(Object[] a, Comparator comparator) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (less(comparator, a[j], a[min])) min = j;
            }
            exch(a, i, min);
//           assert isSorted(a, comparator, 0, i);
        }
//       assert isSorted(a, comparator);
    }
    
   /************************************************************************
    * insertion sort
    *************************************************************************/ 
   /**
    * Rearranges the array in ascending order, using the natural order.
    * @param a the array to be sorted
    */
    public void insertion_sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
//           assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }
   
   /**
    * Rearranges the subarray a[lo..hi] in ascending order, using the natural order.
    * @param a the array to be sorted
    * @param lo left endpoint
    * @param hi right endpoint
    */
    public void insertion_sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
//       assert isSorted(a, lo, hi);
    }
   
   /**
    * Rearranges the array in ascending order, using a comparator.
    * @param a the array
    * @param comparator the comparator specifying the order
    */
   public  void insertion_sort(Object[] a, Comparator comparator) {
       int n = a.length;
       for (int i = 0; i < n; i++) {
           for (int j = i; j > 0 && less(comparator, a[j], a[j-1]); j--) {
               exch(a, j, j-1);
           }
//           assert isSorted(a, 0, i, comparator);
       }
//       assert isSorted(a, comparator);
   }
   
   /****************************************
    * shell sort
   *****************************************/
   public void shell_sort(Comparable[] a) {
       int n = a.length;

       // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
       int h = 1;
       while (h < n/3) h = 3*h + 1; 

       while (h >= 1) {
           // h-sort the array
           for (int i = h; i < n; i++) {
               for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                   exch(a, j, j-h);
               }
           }
//           assert isHsorted(a, h); 
           h /= 3;
       }
//       assert isSorted(a);
   }
   
   /**
    * Rearranges the subarray a[lo..hi] in ascending order, using a comparator.
    * @param a the array
    * @param lo left endpoint
    * @param hi right endpoint
    * @param comparator the comparator specifying the order
    */
   public void insertion_sort(Object[] a, int lo, int hi, Comparator comparator) {
       for (int i = lo; i <= hi; i++) {
           for (int j = i; j > lo && less(comparator, a[j], a[j-1]); j--) {
               exch(a, j, j-1);
           }
       }
//     assert isSorted(a, lo, hi, comparator);
   }

   // return a permutation that gives the elements in a[] in ascending order
   // do not change the original array a[]
   /**
    * Returns a permutation that gives the elements in the array in ascending order.
    * @param a the array
    * @return a permutation {@code p[]} such that {@code a[p[0]]}, {@code a[p[1]]},
    *    ..., {@code a[p[n-1]]} are in ascending order
    */
   public int[] indexSort(Comparable[] a) {
       int n = a.length;
       int[] index = new int[n];
       for (int i = 0; i < n; i++)
           index[i] = i;

       for (int i = 0; i < n; i++)
           for (int j = i; j > 0 && less(a[index[j]], a[index[j-1]]); j--)
               exch(index, j, j-1);

       return index;
   }
   
   
   
   /************************************************************************
    * helper functions
    *************************************************************************/
    /**
     * @param v
     * @param w
     * @return true if v < w
     */
       
   private boolean less(Comparable v, Comparable w) {
       return v.compareTo(w) < 0;
   }

    /**
     * @param comparator
     * @param v
     * @param w
     * @return true if v < w
     */
    private boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }
    
    /**
     * exchange a[i] & a[j]
     * @param a
     * @param i
     * @param j
     */
    private void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    /**
     * exchange a[i] & a[j]  (for indirect sort)
     * @param a
     * @param i
     * @param j
     */
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    /**
     * print the array to standard output
     * @param a
     */
    public void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.printf(a[i] + " ");
        }
    }
    
    
    /************************************************************************
     * check functions
     *************************************************************************/
    /**
     * check if the array is sorted
     * @param a
     * @return true if is sorted
     */
    private boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }
    
    /**
     * check if the array is sorted from a[lo] to a[hi]
     * @param a
     * @param lo
     * @param hi
     * @return true if is sorted
     */
    private boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    /**
     * check if the array is sorted
     * @param a
     * @param comparator
     * @return true if is sorted
     */
    private boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, comparator, 0, a.length - 1);
    }

    /**
     * check the array is sorted from a[lo] to a[hi]
     * @param a
     * @param comparator
     * @param lo
     * @param hi
     * @return true if is sorted
     */
    private boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(comparator, a[i], a[i-1])) return false;
        return true;
    }
    
    // is the array h-sorted?
    private boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i-h])) return false;
        return true;
    }
    
}

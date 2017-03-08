import java.util.Scanner;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author Peng
 * 
 * here we implement an visualization code for sort operation
 * based on two means (insertion & selection) using vertical bars 
 */
public class VisualSort {

    /**
     * implementation of selection sort
     * @param a
     */
    public static void selection_sort(double[] a) {
        for(int i = 0; i < a.length; i++) {
            int min = i;
            for(int j = i + 1; j < a.length; j++){
                if(less(a[j], a[min]))  min = j;
            }
            show(a, i, min);
            exch(a, i, min);
        }
    }
    
    /**
     * implementation of insertion sort
     * @param a
     */
    public static void insertion_sort(double[] a) {
        for(int i = 0; i < a.length; i++) {
            int j = i;
            while(j >= 1 && less(a[j], a[j-1])){
                exch(a, j-1, j);
                j--;
            }
            show(a, i, j);
        }
    }
    
    private static boolean less(double v, double w) {
        return v < w;
    }

    private static void exch(double[] a, int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    /**
     * displaying by vertical bars
     * @param a
     * @param i
     * @param j
     */
    private static void show(double[] a, int i, int j) {
        StdDraw.setYscale(-a.length + i + 1, i);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < j; k++)
            StdDraw.line(k, 0, k, a[k]*0.6);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(j, 0, j, a[j]*0.6);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int k = j+1; k <= i; k++)
            StdDraw.line(k, 0, k, a[k]*0.6);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = i+1; k < a.length; k++)
            StdDraw.line(k, 0, k, a[k]*0.6);
    }
    
    //test code
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        
        StdDraw.setCanvasSize(160, 640);
        StdDraw.setXscale(-1, n+1);
        StdDraw.setPenRadius(0.006);
        
        double[] a = new double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform(0.0, 1.0);  // generation of random array
        selection_sort(a);
        
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform(0.0, 1.0);  // generation of random array
        insertion_sort(a);
        
        scan.close();
    }

}

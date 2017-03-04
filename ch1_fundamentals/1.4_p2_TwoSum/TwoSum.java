import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Peng
 *
 * here we use brute-force and binary-search method for 2-Sum Problem
 */
public class TwoSum {
	
    /**
     * here we using brute-force method
     * @param a is the array
     * @return number of distinct pairs (i, j) such that a[i] + a[j] = 0
     * 
     * Complexity:
     * 		time: O(N^2)
     * 		space: O(1)
     */
    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (a[i] + a[j] == 0) {
                    count++;
                }
            }
        }
        return count;
    } 
    
    /**
     * here we using binary-search method
     * @param a is the array
     * @return number of distinct pairs (i, j) such that a[i] + a[j] = 0
     * 
     * Complexity:
     * 		time: O(n*logN)
     * 		space: O(1)
     */
    public static int countFast(int[] a) {
    	int n = a.length;
    	Arrays.sort(a);
    	//the binary search require there is no duplicates in array
    	if(containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
    	int count = 0;
    	for (int i = 0; i < n; i++) {
            int j = Arrays.binarySearch(a, -a[i]);
            if (j > i) count++;
        }
        return count;
    	
    }

    // returns true if the sorted array a[] contains any duplicated integers
    private static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] == a[i-1]) return true;
        return false;
    }
    
    public static void main(String[] args)  { 
    	File f = new File("src/data.txt");
    	Scanner stdin;
    	List<Integer> list = new ArrayList<Integer>();
		try {
			stdin = new Scanner(f);
			
	        while(stdin.hasNext()){
	        	list.add(stdin.nextInt());
	        }
	        
	        int[] a = new int[list.size()];  
	        for(int i = 0; i < list.size(); i++){  
	            a[i] = list.get(i);  
	        }  
	        
	        Stopwatch timer = new Stopwatch();
	        int count = count(a);
	        System.out.println("elapsed time = " + timer.elapsedTime());
	        System.out.println(count);
	        
	        Stopwatch timerFast = new Stopwatch();
	        int countFast = countFast(a);
	        System.out.println("elapsed timeFast = " + timerFast.elapsedTime());
	        System.out.println(countFast);
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    } 

}

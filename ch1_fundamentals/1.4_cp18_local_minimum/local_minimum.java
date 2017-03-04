
/**
 * @author Peng
 * 
 * creative problem 18 & 19 in algs-1.4
 */
public class local_minimum {

	/**
	 * Problem:
	 * 		given an array a[] of N distinct integers, 
	 * 		finds a local minimum: an index i such that:
	 * 			both a[i] < a[i-1] and a[i] < a[i+1] 
	 * 		(assuming the neighboring entry is in bounds). 
	 * 		Your program should use ~2 lg N compares in the worst case.
	 * 
	 * @param arr is the array
	 */
	public static void local_minimum_for_array(int arr[]){
		for(int i = 1; i < arr.length - 1; i++){
			if( arr[i-1] > arr[i] && arr[i] < arr[i+1])
				System.out.printf("local minimum %d with index %d. \n", arr[i], i);
		}
	}
	
	/**
	 * Problem:
	 * 		Given an N-by-N array a[] of N^2 distinct integers, 
	 * 		design an algorithm that runs in time proportional to N to find a local minimum: 
	 * 		an pair of indices i and j such that:
	 * 			a[i][j] < a[i+1][j], a[i][j] < a[i][j+1], a[i][j] < a[i-1][j], and a[i][j] < a[i][j-1] 
	 * 		(assuming the neighboring entry is in bounds).
	 * 		Hint: 	
	 * 			Find the minimum entry in row N/2, say a[N/2][j]. 
	 * 			Check its two vertical neighbors a[N/2-1][j] and a[N/2+1][j]. 
	 * 			Recur in the half with the smaller neighbor. 
	 * 			In that half, find the minimum entry in column N/2.
	 * 
	 * @param arr is the matrix
	 */
	public static void local_minimum_for_matrix(int arr[][]){
		for(int i = 1; i < arr.length - 1; i++){  //row
			for(int j = 1; j < arr[i].length - 1; j++)  //column
				if( arr[i-1][j] > arr[i][j] && arr[i+1][j] > arr[i][j] && arr[i][j-1] > arr[i][j] && arr[i][j+1] > arr[i][j])
					System.out.printf("local minimum %d with index %d,%d. \n", arr[i][j], i,j);
		}
	}
	
	public static void main(String[] args) {
		int arr[] = {2,1,3,-1,1,4};
		local_minimum_for_array(arr);
		
		int arr2[][] = { {1,2,3,4,5},
						 {2,0,6,2,6},
					     {3,2,1,4,6} };
		local_minimum_for_matrix(arr2);
		
	}

}

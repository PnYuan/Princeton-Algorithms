import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author Peng
 *
 * In the Josephus problem from antiquity, 
 * N people are in dire straits and agree to the following strategy to reduce the population. 
 * They arrange themselves in a circle (at positions numbered from 0 to N-1) 
 * and proceed around the circle, 
 * eliminating every Mth person until only one person is left. 
 * Legend has it that Josephus figured out where to sit to avoid being eliminated. 
 * Write a Queue client josephur_problem.java that takes M and N from the command line 
 * and prints out the order in which people are eliminated 
 * (and thus would show Josephus where to sit in the circle).
 *
 */
public class josephus_problem {

    public static void main(String[] args) {
       
        Scanner scan  = new Scanner(System.in);
        System.out.println("please input m and n");
        int m = scan.nextInt();
        int n = scan.nextInt();
        
        Queue<Integer> q = new LinkedList<Integer>();
        
        for(int i = 0; i < n; i++){
            q.add(i);
        }
        while(!q.isEmpty()){
            for(int i = 0; i < m-1; i++)
                q.add(q.poll());  //poll the head to the tail;
            System.out.print(q.poll() + " ");
        }
        
        scan.close();
    }

}

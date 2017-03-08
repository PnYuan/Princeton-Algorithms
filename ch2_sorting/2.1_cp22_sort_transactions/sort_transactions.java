import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class sort_transactions {

    public static Transaction[] readTransactions() {
        Queue<Transaction> queue = new Queue<Transaction>();
        
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            Transaction transaction = new Transaction(line);
            queue.enqueue(transaction);
        }

        int n = queue.size();
        Transaction[] transactions = new Transaction[n];
        for (int i = 0; i < n; i++)
            transactions[i] = queue.dequeue();

        return transactions;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        // read data
        File file = new File("src/data/tinyBatch.txt");
        Scanner stdin;
        stdin = new Scanner(file);
        Queue<Transaction> queue = new Queue<Transaction>(); 
        while (stdin.hasNextLine()) {
            String line = stdin.nextLine();
            Transaction transaction = new Transaction(line);
            queue.enqueue(transaction);
        }
        stdin.close();

        int n = queue.size();
        Transaction[] transactions = new Transaction[n];
        for (int i = 0; i < n; i++)
            transactions[i] = queue.dequeue();
        
        Arrays.sort(transactions);
        for (int i = 0; i < transactions.length; i++)
            StdOut.println(transactions[i]);
        

    }
}

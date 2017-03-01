import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stats {

    public static void main(String[] args) {
        
        Bag<Double> numbers = new Bag<Double>();  //create a bag storing double numbers
        //get input
        while (!StdIn.isEmpty()) {
            numbers.add(StdIn.readDouble());  //put into bag
        }
        
        int n = numbers.size();
        
        //compute sample mean
        double sum = 0.0;
        for (double x : numbers)
            sum += x;
        double mean = sum/n;
        
        //compute sample standard deviation
        sum = 0.0;
        for (double x : numbers) {
            sum += (x - mean) * (x - mean);
        }
        double stddev = Math.sqrt(sum/(n-1));

        StdOut.printf("Mean:    %.2f\n", mean);
        StdOut.printf("Std dev: %.2f\n", stddev);
    }
}

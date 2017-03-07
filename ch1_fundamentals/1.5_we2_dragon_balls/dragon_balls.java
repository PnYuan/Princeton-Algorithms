import java.util.Scanner;

/**
 * @author Peng
 * 
 * Problem' websites: http://acm.hdu.edu.cn/showproblem.php?pid=3635
 * 
 * Problem Description:
 *      Five hundred years later, the number of dragon balls will increase unexpectedly,
 *      so it's too difficult for Monkey King(WuKong) to gather all of the dragon balls together. 
 *      
 *      His country has N cities and there are exactly N dragon balls in the world. At first, 
 *      for the ith dragon ball, the sacred dragon will puts it in the ith city. Through long years, 
 *      some cities' dragon ball(s) would be transported to other cities. To save physical strength 
 *      WuKong plans to take Flying Nimbus Cloud, a magical flying cloud to gather dragon balls. 
 *      Every time WuKong will collect the information of one dragon ball, he will ask you the 
 *      information of that ball. You must tell him which city the ball is located and how many 
 *      dragon balls are there in that city, you also need to tell him how many times the ball 
 *      has been transported so far.
 * 
 * Input:  
 *      The first line of the input is a single positive integer T(0 < T <= 100). 
 *      For each case, the first line contains two integers: N and Q (2 < N <= 10000 , 2 < Q <= 10000).
 *      Each of the following Q lines contains either a fact or a question as the follow format:
 *    T A B : All the dragon balls which are in the same city with A have been transported to 
 *      the city the Bth ball in. You can assume that the two cities are different.
 *    Q A : WuKong want to know X (the id of the city Ath ball is in), Y (the count of 
 *      balls in Xth city) and Z (the tranporting times of the Ath ball). (1 <= A, B <= N)
 *      
 * Output:
 *      For each test case, output the test case number formated as sample output. 
 *      Then for each query, output a line with three integers X Y Z saparated by a blank space.     
 *      
 * Idea:
 *      using union-find, but we need to make a little change.
 *      X: use root id;
 *      Y: use the size(rank) of X's component;
 *      Z: add a new variables to save transporting times; 
 */
public class dragon_balls {
    
    /**
     * make some changes on UF class.
     */
    public static class UF_new {
        private int[] parent;  // parent[i] = parent of i
        private byte[] rank;   // rank[i] = rank of subtree rooted at i (here is the size of component)
        private byte[] trans;  // trans[i] save the transport times of i;
        private int count;     // number of components
        
        /**
         * Initializes an empty union¨Cfind data structure with {@code n} sites
         * {@code 0} through {@code n-1}. Each site is initially in its own 
         * component.
         *
         * @param  n the number of sites
         * @throws IllegalArgumentException if {@code n < 0}
         */
        public UF_new(int n) {
            if (n < 0) throw new IllegalArgumentException();
            count = n;
            parent = new int[n];
            rank = new byte[n];
            trans = new byte[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;  // Each site is initially in its own component.
                rank[i] = 1;
                trans[i] = 0;
            }
        }
        
        /**
         * Returns the component identifier for the component containing site {@code p}.
         *
         * @param  p the integer representing one site
         * @return the component identifier for the component containing site {@code p}
         * @throws IndexOutOfBoundsException unless {@code 0 <= p < n}
         */
        public int find(int p) {
            validate(p);
//            while (p != parent[p]) {
//                parent[p] = parent[parent[p]];    // path compression by halving
//                p = parent[p];
//            }
//            return p;
            
            while (parent[p] != parent[parent[p]]) {  //if there exists intermediate node 
                // p's parents trans times add to p's
                trans[p] += trans[parent[p]];  // update transporting times of p while path compression 
                parent[p] = parent[parent[p]];  // path compression
             }  
             return parent[p];  
        }
        
        /**
         * Returns the number of components.
         *
         * @return the number of components (between {@code 1} and {@code n})
         */
        public int count() {
            return count;
        }
      
        /**
         * Returns true if the the two sites are in the same component.
         *
         * @param  p the integer representing one site
         * @param  q the integer representing the other site
         * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
         *         {@code false} otherwise
         * @throws IndexOutOfBoundsException unless
         *         both {@code 0 <= p < n} and {@code 0 <= q < n}
         */
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
      
        /**
         * Merges the component containing site {@code p} with the 
         * the component containing site {@code q}.
         *
         * @param  p the integer representing one site
         * @param  q the integer representing the other site
         * @throws IndexOutOfBoundsException unless
         *         both {@code 0 <= p < n} and {@code 0 <= q < n}
         */
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
    
//            // make root of smaller rank point to root of larger rank
//            if      (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
//            else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
//            else {
//                parent[rootQ] = rootP;
//                rank[rootP]++;
//            }
//            count--;
            
            /**
             * here we do not union basing on rank;
             * but we have to save the rank refer to the node numbers (size) of an component.
             * and add trans[rootP] variable for save transporting number of ball p;
             */
            parent[rootP] = rootQ;  // update the root
            trans[rootP]++;  //update the transport times
            rank[rootQ] += rank[rootP];  // update the size  
        }
        
        // validate that p is a valid index
        private void validate(int p) {
            int n = parent.length;
            if (p < 0 || p >= n) {
                throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));  
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("please input the number of N (cities and balls): ");
        int N = scan.nextInt();  //the number of friends
        UF_new uf = new UF_new(N);
        
        System.out.println("please input operation: ");
        System.out.println("ps. (T  A  b) means trans ball A to city b ");
        System.out.println("ps. (Q  A) means ask information of ball");
        while (scan.hasNext()) {
            String s = scan.next();
            if(s.charAt(0) == 'T'){
                int p = scan.nextInt();
                int q = scan.nextInt();
                if (uf.connected(p, q)) continue;
                uf.union(p, q);
            }
            else if(s.charAt(0) == 'Q'){
                int p = scan.nextInt();
                System.out.printf("information for Wukong: %d  %d  %d. ",uf.parent[p], uf.rank[p], uf.trans[p]);
            }
        }
        
        scan.close();
    }
}

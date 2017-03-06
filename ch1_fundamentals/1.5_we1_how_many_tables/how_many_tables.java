import java.util.Scanner;

/**
 * @author Peng
 * 
 * Problem' websites: http://acm.hdu.edu.cn/showproblem.php?pid=1213
 * 
 * Problem description:
 *      Today is Ignatius' birthday. He invites a lot of friends.
 *      Now it's dinner time. 
 *      Ignatius wants to know how many tables he needs at least.
 *      You have to notice that not all the friends know each other, 
 *      and all the friends do not want to stay with strangers.
 *
 *      One important rule for this problem is that if I tell you A knows B, and B knows C, 
 *      that means A, B, C know each other, so they can stay in one table.
 *      
 * For example: 
 *      If I tell you A knows B, B knows C, and D knows E, 
 *      so A, B, C can stay in one table, and D, E have to stay in the other one. 
 *      So Ignatius needs 2 tables at least.
 *      
 * Idea:
 *      it is a typical union-find problem
 *      we ought to find out the component number in the end  
 */
public class how_many_tables {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("please input the number of Ignatius's friends: ");
        int N = scan.nextInt();  //the number of friends
        UF uf = new UF(N);
        
        System.out.println("please input the person pair who know each other: ");
        while (scan.hasNext()) {
            int p = scan.nextInt();
            int q = scan.nextInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
        }
        System.out.println("tables needed: " + uf.count());
        
        scan.close();
    }

}

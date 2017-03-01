import java.util.Stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Reverse {

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<Integer>();  //build a stack
        while (!StdIn.isEmpty()) {  //get numbers
            st.push(StdIn.readInt());  //push
        }
        for (int i : st) {
            StdOut.println(i);  //print (in order)
        }
        while(!st.isEmpty()){
            System.out.println(st.pop());  //pop out and print()
        }
    }

}

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author Peng
 * 
 * build a fixed capacity stack using generic
 */
public class FixedCapacityStack <Item> implements Iterable<Item> {
    
    private Item[] a;    // holds the items
    private int N;       // number of items in stack
    
    // create an empty stack with given capacity
    public FixedCapacityStack(int capacity) {
        a = (Item[]) new Object[capacity];   // no generic array creation
        N = 0;
    }
    
    public boolean isEmpty()          {  return N == 0;                    }
    
    /**
     * add isFull()
     * @return true if the stack is full
     */
    public boolean isFull()           {  return N == a.length;             }
    
    public void push(Item item)       {  a[N++] = item;                    }
    public Item pop()                 {  return a[--N];                    }
    public Iterator<Item> iterator()  { return new ReverseArrayIterator(); }

    // an iterator, doesn't implement remove() since it's optional
    public class ReverseArrayIterator implements Iterator<Item> {
        private int i = 0;
        
        public boolean hasNext() {
            return i < N;
        }
        
        public Item next(){
            if(!hasNext()) throw new NoSuchElementException();
            return a[i++];
        }
        
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
    
    //test code 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("input stack capacity: ");
        int max = scan.nextInt();
        FixedCapacityStack<Integer> st = new FixedCapacityStack<Integer>(max);
        
        //pushing
        System.out.println("input stack items, using ' space ' for links: ");
        while (!StdIn.isEmpty()) {
            Integer item = StdIn.readInt();
            if(!st.isFull())  st.push(item);
            else{
                StdOut.print("the stack is full, cannot push anymore");   
                break;
            }
        }
        
        //printing
        StdOut.println();
        StdOut.print("Items in stack (using iterator):");
        for (Integer s : st) {
            StdOut.print(s + " ");
        }
        StdOut.println();
        
        StdOut.print("Items in stack (using pop):");
        while (!st.isEmpty()) {
            StdOut.print(st.pop() + " ");
        }
        StdOut.println();
        
        scan.close();
    }

}





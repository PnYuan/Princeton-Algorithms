import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/****************************************************************************
 * Queue<Item> class
 * 
 * @param Item: generic type
 *****************************************************************************/
public class Queue<Item> implements Iterable<Item> {
    //definition of queue's first, last, and length(size).
    private Node<Item> first;
    private Node<Item> last;
    private int n;
    
    //definition of nested class Node
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
    
    //constructor
    public Queue(){
        first = last = null;
        n = 0;
    }
    
    /**
     * isEmpty()
     * 
     * @return true if the queue is empty 
     */
    public boolean isEmpty(){
        return n == 0;
    }
    
    /**
     * size()
     * 
     * @return size of the queue
     */
    public int size(){
        return n;
    }
    
    /**
     * peek() get the value of first in
     * 
     * @return the least recently added item
     * 
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }
    
    /**
     * enqueue(Item item): add item into the queue (at last)
     * 
     * @param item: ought to add
     */
    public void enqueue(Item item){
        Node<Item> oldlast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if(isEmpty())   first = last;
        else    oldlast.next = last;
        n++;
    }
    
    /**
     * dequeue() "First Out" Removes and returns the item on this queue 
     *      that was least recently added
     * 
     * @return the item on this queue that was least recently added
     */
    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }
    
    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    } 
    
    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);  
    }
    
    // an iterator, doesn't implement remove() since it's optional
    @SuppressWarnings("hiding")
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    //test code
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        
        while (!StdIn.isEmpty()) {
            Integer item = StdIn.readInt();
            queue.enqueue(item);  //add item to the Queue (last in)
        }
        
        StdOut.println(queue.toString());
        while(!queue.isEmpty()){
            StdOut.printf("%d - ", queue.dequeue());
        }
    }

}




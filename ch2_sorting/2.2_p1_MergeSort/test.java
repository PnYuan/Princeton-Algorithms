
public class test {

    public static void print_array(Integer[] a) {
        for(Integer i : a) {
            System.out.printf(i + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Integer[] a1 = {19, 2, 23, 9, 9, 8, 41, 78, 4, 32, 11 };
        Integer[] a2 = {19, 2, 23, 9, 9, 8, 41, 78, 4, 32, 11 };
        System.out.println("before sorted:");
        print_array(a1);
        
        MergeSort m = new MergeSort(); 
        
        m.merge_sort_x(a1);
        System.out.println("after merge-sorted_td:");
        print_array(a1);
        
        m.merge_sort_bu(a2);
        System.out.println("after merge-sorted_bu:");
        print_array(a2);
    }

}

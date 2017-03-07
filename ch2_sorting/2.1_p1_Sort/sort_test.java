
public class sort_test {

    public static void main(String[] args) {
        Sort s = new Sort();
        
        Integer[] a = {1, 2, 4, 2, 5, 7, 21, 1};
        System.out.printf("raw array: \n");
        s.show(a);
        System.out.println();
        
        //selection sort
        s.selection_sort(a);
        System.out.printf("array after selection-sort: \n");
        s.show(a);
        System.out.println();
        
        //insertion sort
        s.insertion_sort(a);
        System.out.printf("array after insertion-sort: \n");
        s.show(a);
        System.out.println();

    }

}

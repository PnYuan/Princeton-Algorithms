import java.util.Stack;

public class print_binary {

    public static void main(String[] args) {
        int n = 100;
        Stack<Integer> s = new Stack<Integer>();
        while(n > 0){
            s.push(n % 2);
            n /= 2;
        }
        while (!s.isEmpty())
            System.out.print(s.pop());
    }
}

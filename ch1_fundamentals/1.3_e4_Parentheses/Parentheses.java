import java.util.Stack;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author Peng
 *
 * here we should judge whether a string is properly 
 * for it's parentheses sequence. 
 */
public class Parentheses {
    private static final char LEFT_PAREN     = '(';
    private static final char LEFT_BRACKET   = '[';
    private static final char LEFT_BRACE     = '{';
    private static final char RIGHT_PAREN    = ')';
    private static final char RIGHT_BRACKET  = ']';
    private static final char RIGHT_BRACE    = '}';
    
    private static Object isBalanced(String s) {
        Stack<Character> st = new Stack<Character>();
        
        for(int i = 0; i < s.length(); i++){
            //push stack while left parentheses;
            switch (s.charAt(i)){
            default: break;
            case LEFT_PAREN    : st.push(LEFT_PAREN  ); break;
            case LEFT_BRACKET  : st.push(LEFT_BRACKET); break; 
            case LEFT_BRACE    : st.push(LEFT_BRACE  ); break;
            case RIGHT_PAREN   : if (st.isEmpty() || st.pop() != LEFT_PAREN  )  return false; break;
            case RIGHT_BRACKET : if (st.isEmpty() || st.pop() != LEFT_BRACKET)  return false; break;
            case RIGHT_BRACE   : if (st.isEmpty() || st.pop() != LEFT_BRACE  )  return false; break;
            }
        }
        return st.isEmpty();
    }
    
    public static void main(String[] args) {
        String s = "{ 7 * [ (3 + 2) * 5 ] } / 3";
        StdOut.println(isBalanced(s));
    }

}

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class RevString {
    public static void main(String[] args) {
        Stack<Character> s = new Stack<Character>();
        Queue<Character> q = new LinkedList<Character>();
        String str = args[0];
        int slen = str.length();
        for(int i = 0; i < slen; i++) { s.push(str.charAt(i)); }
        while (!s.empty()) { q.add(s.pop()); }
        for (Character c: q)
            System.out.print(c);
        System.out.print('\n');
    }
}

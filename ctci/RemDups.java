import java.util.*;

/* Make assumption that we can sort the string
Introducing a complexity of log N for sort,
and N for a single pass.*/
public class RemDups {
    public static void main(String[] args) {
        String s = args[0];
        int slen = s.length();
        Character[] chars = new Character[slen];
        for(int i = 0; i < slen; i++)
            chars[i] = s.charAt(i);
        Arrays.sort(chars, new Comparator<Character>() {
                public int compare(Character c1, Character c2) {
                    return Character.compare(Character.toLowerCase(c1.charValue()),
                                             Character.toLowerCase(c2.charValue()));
                }
            });
        StringBuilder sb = new StringBuilder(slen);
        char lastChar = chars[0];
        sb.append(lastChar);
        for (char c: chars) {
            if (c != lastChar) {
                sb.append(c);
                lastChar = c;
            }
        }
        System.out.println(sb);
    }
}

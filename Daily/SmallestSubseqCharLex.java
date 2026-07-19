import java.util.*;

public class SmallestSubseqCharLex {
    public static String smallestSubsequence(String s) {
        
    int lastseen[] = new int[26];
    

    //used to update the last seen index of the characters --> "aaaa" -> last[0] = 3
    for(int i = 0 ; i < s.length() ; i++){
        lastseen[s.charAt(i) - 'a'] = i;
    }

    boolean seen[] = new boolean[26];
    StringBuilder result = new StringBuilder();


    for(int i = 0 ; i < s.length() ; i++){

        char c = s.charAt(i);

        if(seen[c - 'a']){
            continue;
        }

        // While result is not empty and:
        // 1. Current char is smaller than last char in result
        // 2. The last char appears again later in the string
        // Then iteratively remove the last char
        while(result.length() > 0 && c < result.charAt(result.length() - 1) && lastseen[result.charAt(result.length() - 1) - 'a'] > i){
            seen[result.charAt(result.length() - 1) - 'a'] = false;
            result.deleteCharAt(result.length() - 1);
        }


        result.append(c);
        seen[c - 'a'] = true;

    }


        return result.toString();
    }
    public static void main(String[] args) {
        System.out.println(smallestSubsequence("cbacdcbc"));
    }
}

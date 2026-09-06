import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class WordBreak {

    // GREEDY APPROACH (FAILS)
    /*
    public static boolean wordBreak(String s, List<String> wordDict) {
       
        if(s.length() == 1 && wordDict.contains(s))
            return true;


        int start = 0;
        int end = start + 1;

        while(start < s.length() && end < s.length() + 1 && start < end){
            if(breakHelper(s, wordDict, start, end)){
                start = end;
                end++;
            }
            else
                end++;
        }

        if(start == s.length())
            return true;

        return false;
    }

    private static boolean breakHelper(String s, List<String> wordDict, int start, int end){
        if(wordDict.contains(s.substring(start, end)))
            return true;

        return false;
    }
*/

    public static boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<String>();

        for(String x : wordDict)
            set.add(x);

        boolean dp[] = new boolean[s.length() + 1];

        //Empty String is always matched
        dp[0] = true;

        for(int i = 1; i <= s.length() ; i++){
            for(int j = 0 ; j < i ; j++){
                if(dp[j] && set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    public static void main(String[] args) {
        System.out.println(wordBreak("aaaaaaa", new ArrayList<>(Arrays.asList(new String[]{"aaaa","aaa"}))));

    }
}

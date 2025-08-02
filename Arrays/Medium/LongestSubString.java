import java.util.*;

public class LongestSubString {
    public static int lengthOfLongestSubstring(String s) {
        if(s.length() == 0 || s == null)
            return 0;
        HashSet<Character> hs = new HashSet<Character>();
        long maxLen = 0L;
        int left = 0;
        
        for(int right = 0 ; right < s.length() ; right ++){
            char current = s.charAt(right);

            while(hs.contains(current)){
                hs.remove(s.charAt(left));
                left++;
            }
            hs.add(current);
            maxLen = Math.max(maxLen , ((long)right - (long)left) + (long)1 );
        }
        return (int)maxLen;
    }

    // public static int lengthOfLongestSubstring(String s) {
    //     if(s.length() == 0 || s == null)
    //         return 0;
    //     String str = "";
    //     int maxLen = 0, index = 0;

    //     for(int i = 0 ; i < s.length() ; i ++){
    //         str = String.valueOf(s.charAt(i));
    //         index = i+1;
    //         while(index < s.length()){           
    //         if(!str.contains(String.valueOf(s.charAt(index)))){
    //             str+=String.valueOf(s.charAt(index));
    //             index++;
    //         }
    //         else
    //             break;           
    //         }
    //         maxLen = Math.max(str.length() , maxLen);
    //     }
    //     return maxLen;
    // }
    public static void main(String args[]){
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }
}

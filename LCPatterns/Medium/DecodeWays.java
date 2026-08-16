import java.util.*;

public class DecodeWays {
    public static int numDecodings(String s) {

        if(s == null || s.length() == 0 || s.charAt(0) == '0')
            return 0;

        int n = s.length();

        int dp[] = new int[n + 1];

        dp[0] = 1;  // Empty string has 1 way to decode
        dp[1] = 1; // One character has 1 way to decode and it is not '0'

        for(int i = 2 ; i <= n ; i++){
            int singledigit = Integer.parseInt(s.substring(i-1,i));

            //Single character window
            if(singledigit > 0 && singledigit <= 9)
                dp[i]+= dp[i - 1];

            // Two character window
            int doubledigit = Integer.parseInt(s.substring(i-2,i));
    
            if(doubledigit >= 10 && doubledigit <= 26)
                dp[i]+= dp[i - 2];
        }

        return dp[n];
    }



    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
    }
}

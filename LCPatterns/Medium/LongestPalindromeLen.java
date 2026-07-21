public class LongestPalindromeLen{
    public static String longestPalindrome(String s) {
        if(s == null || s.length() == 0)
            return "";

        int start = 0, maxLen = 1;

        for(int i = 0 ; i < s.length() ; i ++){

            // FOR ODD single centered aba
            int len1 = expandAroundCenter(s, i, i);

            //FOR EVEN double centerd abbc
            int len2 = expandAroundCenter(s, i, i+1);

            int len = Math.max(len1,len2);


            if(len > maxLen){
                maxLen = len;
                start = i - ((len - 1)/2);
            }

/*
Why start will never be negative
For odd length:
Maximum leftward expansion = i steps
Maximum len = 2*i + 1

start = i - (len - 1)/2
      = i - (2*i + 1 - 1)/2
      = i - (2*i)/2
      = i - i
      = 0 ✓


For even length:
Maximum leftward expansion = i steps (from position i to position 0)
Maximum len = 2*i + 2

start = i - (len - 1)/2
      = i - (2*i + 2 - 1)/2
      = i - (2*i + 1)/2
      = i - i  (integer division)
      = 0 ✓           

*/

        }

        return s.substring(start,start+maxLen);
    }

    public static int expandAroundCenter(String s, int left, int right){
        while(left>=0 && right < s.length() && s.charAt(right) == s.charAt(left)){
            right++;
            left--;
        }
        return right - left - 1;
    }



    public static void main(String args[]){
        System.out.println(longestPalindrome("babad"));
    }
}
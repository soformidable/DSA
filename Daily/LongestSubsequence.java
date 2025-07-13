public class LongestSubsequence {
    public static int longestSubsequence(String s, String str){
        int index = 0;
        for(int i = 0 ; i < s.length(); i++)
            if(s.charAt(i) == str.charAt(index%str.length()))
                index++;

        return (int)(index / str.length());
    }
    public static int countOccurences(String s , String str){
        int count = 0;
        for(int i = 0 ; i < s.length() ; i++){
            int start = 0;
            if(s.charAt(i) == str.charAt(start))
                while(start < str.length() && start + i < s.length()){
                    if(s.charAt(start + i) == str.charAt(start))
                        start++;
                    else break;
                }
            if(start == 3) count++;
        }
        return count;
    }
    public static void main(String args[]){
        System.out.println(longestSubsequence("ababaaa", "bba"));
        System.out.println(countOccurences("ababbaaba", "bba"));
    }
}

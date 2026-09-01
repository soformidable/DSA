import java.util.List;
import java.util.ArrayList;

public class PalindromePartition {
    public static List<List<String>> partition(String s) {
        
            List<List<String>> result = new ArrayList<>();

            partitionHelper(s, result, new ArrayList<String>());
        
            return result;
    }

    private static void partitionHelper(String s, List<List<String>> result, List<String> current){

        // Base Case -- End of the string is reached, so it is successfully partitioned
        if(s == null || s.length() == 0){
            result.add(new ArrayList<>(current));
            return;
        }

        for(int i = 1; i <= s.length(); i++){

            // skip over if not a palindrome
            if(!isPalindrome(s.substring(0,i)))
                continue;

            //add to current path
            current.add(s.substring(0,i));

            //recurse
            partitionHelper(s.substring(i,s.length()), result, current);

            // backtrack
            current.remove(current.size() - 1);

        }

        //final return
        return;
    }


    private static boolean isPalindrome(String str){
        int left = 0, right = str.length() - 1;
        while(left <= right){
            if(str.charAt(right) != str.charAt(left))
                return false;
            right--;
            left++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }
}

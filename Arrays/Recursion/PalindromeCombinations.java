import java.util.*;

public class PalindromeCombinations {
    
        public static List<List<String>> partition(String s) {
            List<List<String>> result = new ArrayList<List<String>>();
            List<String> lst = new ArrayList<String>();
            String str = "";
            getCombinations(s, 0, lst, result);

            return result;
        }

        public static void getCombinations(String str, int start, List<String> current, List<List<String>> result){
            if(start == str.length()){
                result.add(new ArrayList<String>(current));
                return;
            }

            for(int end = start + 1 ; end <= str.length() ; end++) {
                String substring = str.substring(start, end);

                if(isPalindrome(substring)){
                    current.add(substring);
                    getCombinations(str, end, current, result);
                    current.remove(current.size() - 1);
                }
            }

        }

        public static boolean isPalindrome(String str){
            int left = 0 , right = str.length() - 1;
            while(left <= right){
                if(str.charAt(left) != str.charAt(right)){
                    return false;
                }
                left++;
                right--;
            }

            return true;
        }

        public static void main(String args[]){
            System.out.println(partition("abba"));
        }
            
}

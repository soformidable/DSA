import java.util.*;

public class PhoneNumberCombination {
    private static final String[] MAPPING = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public static List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0)
                return Collections.emptyList();

        List<String> result =  new ArrayList<String>();

        backtrack(result, new StringBuilder(), 0, digits);

        return result;
    }

    private static void backtrack(List<String> result,StringBuilder current, int index, String digits){
        if(index == digits.length()){
            result.add(current.toString());
            return;
        }
        
        // can be used with - '2' if MAPPING[0] = "abc"
        String letters = MAPPING[digits.charAt(index) - '0'];


        for(char c : letters.toCharArray()){
            current.append(c);

            //Next index in digits
            backtrack(result,current,index + 1,digits);

            //explore other paths
            current.deleteCharAt(current.length() - 1);
        }

    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("2"));
    }

}
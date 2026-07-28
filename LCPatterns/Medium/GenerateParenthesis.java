import java.util.*;

public class GenerateParenthesis{
    public static List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<String>();
        
        generate(result,  new StringBuilder(), 0,0,n);
        
        return result;
    }

    public static void generate(List<String> result, StringBuilder str, int open, int close, int n){

        if(str.length() == n*2){
            result.add(str.toString());
            return;
        }

        if(open < n){
            str.append("(");
            generate(result, str, open + 1, close, n);
            str.deleteCharAt(str.length() - 1);
        }

        if(close < open){
            str.append(")");
            generate(result, str, open, close + 1, n);
            str.deleteCharAt(str.length() - 1);
        }

        
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(2));
    }

}
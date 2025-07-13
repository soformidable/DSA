import java.util.*;

public class DivideStringFillK {
    public static String[] divideString(String s, int k, char fill) {

    if(s == null || s.length() == 0 )    
    return new String[0];
    
    int len = ((s.length()+k) - 1)/k;
    
    String str[] = new String[len];


    for(int i = 0 ; i < len ; i++){
        int start = i * k;
        int end = Math.min(s.length(), start+k);
        StringBuilder sb = new StringBuilder(s.substring(start, end));
        while(sb.length()<k)
            sb.append(fill);
        str[i] = sb.toString();
    }

     return str;
    }
    public static void main(String args[]){
        System.out.println(Arrays.toString(divideString("abcdefgh", 4, 'x')));
    }
}

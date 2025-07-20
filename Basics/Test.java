import java.util.*;

public class Test {
    public static void main(String[] args){

        // How to generate the sequences
        //Create a tree of elements swapping index starting from 0 to length - 1 using recursion


        int input[] = {1,2,3};
        String s = "";
        for(int x:input){
            s =  s+ String.valueOf(x);
        }
        String arr[] = new String[]{"123","213","132"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

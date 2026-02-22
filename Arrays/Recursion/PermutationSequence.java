import java.util.*;


public class PermutationSequence {
    public static String getPermutation(int n, int k) {

        boolean used[] = new boolean[n+1];

        List<String> lst = new ArrayList<String>();
        getPermutationList("",used,n,k,lst);
        Collections.sort(lst);
        return(lst.get(k-1));
    }

    public static void getPermutationList(String str, boolean used[], int n, int k, List<String> lst){
        if (str.length() == n){
            lst.add(str);
            return;
        }

        if(lst.size() > k)
            return;


        for(int i = 1 ; i <= n ; i++)
            if(!used[i]){
                used[i] = true;
                getPermutationList(str + i, used, n, k, lst);
                used[i] = false;
            }

    
    }
    public static void main(String args[]){
        System.out.println(getPermutation(3, 3));
        // getPermutation(3, 3);
    }

}

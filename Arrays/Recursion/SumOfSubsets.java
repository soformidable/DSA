import java.util.*;

public class SumOfSubsets {
    public static List<Integer> getSubsetSum(int N,int arr[]){
        ArrayList<Integer> lst = new ArrayList<Integer>();
        getSubsetHelper(0, 0, N, arr, lst);
        Collections.sort(lst);
        return lst;
    }
    // Use Set<Integer> to avoid duplicates in the final subset for example {1,-1,2}
    public static void getSubsetHelper(int index, int sum, int N, int arr[], List<Integer> subset){

        if(N == index){
            subset.add(sum);
            return;
        }

        // Creating "choose the element" tree
        getSubsetHelper(index + 1, sum + arr[index], N, arr, subset);

        // Creating "not to choose the element" tree
        getSubsetHelper(index + 1, sum, N, arr, subset);
        
    }

    public static void main(String args[]){
        int arr[] = new int[] {3,1,2};
        System.out.println(getSubsetSum(3, arr));
    }
}

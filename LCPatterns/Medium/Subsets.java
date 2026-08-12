import java.util.*;

public class Subsets{
    public static List<List<Integer>> subsets(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();
        getSubset(new ArrayList<Integer>(), result, nums, 0);

        return result;
    }

    private static void getSubset(List<Integer> cand, List<List<Integer>> result, int nums[], int index){

        
        result.add(new ArrayList<Integer>(cand));

        if(index >= nums.length)
            return;

                   
        for(int i = index ; i < nums.length ; i++){
        cand.add(nums[i]);
        getSubset(cand, result, nums, i + 1);
        cand.remove(cand.size() - 1);   
        }
        
    }

    public static void main(String args[]){
        System.out.println(subsets(new int[]{0}));
    }
}
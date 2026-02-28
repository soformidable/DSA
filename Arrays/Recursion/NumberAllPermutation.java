import java.util.*;
import java.util.stream.*;

public class NumberAllPermutation {
    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        boolean used[] = new boolean[nums.length];

        getPermutations(nums, new ArrayList<Integer>(), used, result);

        return result;
    }

    public static void getPermutations(int[] nums, List<Integer> cand, boolean[] used,  List<List<Integer>> result){
        if(cand.size() == nums.length){
            result.add(new ArrayList<>(cand));
            return;
        }
        for(int i = 0 ; i < nums.length ; i ++){
            if(used[i]) continue;
            used[i] = true;
            cand.add(nums[i]);
            getPermutations(nums, cand, used, result);
            cand.remove(cand.size() - 1);
            used[i] = false;
        }


    }

    public static void main(String args[]){
        System.out.println(permute(new int[]{1,2,3}));
    }

}

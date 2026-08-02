import java.util.*;

public class PermutationII {
    public static List<List<Integer>> permuteUnique(int[] nums) {


        // Sort the array first to get the duplicates next to each other
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        boolean[] is_used = new boolean[nums.length];
        getPermutation(nums, new ArrayList<Integer>(), result, is_used);

        return result;
    }

    public static void getPermutation(int nums[], List<Integer> cand, List<List<Integer>> result, boolean[] is_used){
        if(cand.size() == nums.length){
            result.add(new ArrayList<>(cand));
            return;
        }

        for(int i = 0; i< nums.length ; i ++){

            if(is_used[i])
                continue;

            //if the current number is same as the previous number and the previous number has not been used, SKIP
            if(i > 0 && nums[i] == nums[i - 1] && !is_used[i - 1])
                continue;

            is_used[i] = true;
            cand.add(nums[i]);
            getPermutation(nums, cand, result, is_used);
            cand.remove(cand.size() - 1);
            is_used[i] = false;
        }
    }

    public static void main(String args[]){
        System.out.println(permuteUnique(new int[]{1,1,2}));
    }

}

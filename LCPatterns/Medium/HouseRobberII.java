import java.util.*;

public class HouseRobberII{
    public static int rob(int[] nums) {
        
        if(nums.length == 1)
            return nums[0];

        int memo1[] = new int[nums.length + 1];
        int memo2[] = new int[nums.length + 1];
        Arrays.fill(memo1,-1);
        Arrays.fill(memo2,-1);

        // Robbing from 0 th n - 1
        int case1 = rob(nums, 0, nums.length - 2, memo1);
        // Robbing from 1 to n
        int case2 = rob(nums, 1, nums.length - 1, memo2);

        return Math.max(case1, case2);
    }   

    private static int rob(int[] nums, int index, int endIndex, int memo[]){
        if(index > endIndex)
            return 0;

        if(memo[index] >= 0)
            return memo[index];

        int result = Math.max(nums[index] + rob(nums, index + 2, endIndex, memo),rob(nums, index + 1, endIndex, memo));
        memo[index] = result;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{2,3,2}));

        // 1,1,1,1,1
    }
}
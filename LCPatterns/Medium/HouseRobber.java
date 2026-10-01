import java.util.Arrays;

public class HouseRobber{
    public static int rob(int[] nums) {
        if(nums.length == 1)
            return nums[0];

        int memo[] = new int[nums.length  +1];
        Arrays.fill(memo,-1);

        return rob(nums,memo,0);
    }

    private static int rob(int nums[], int[] memo, int index){

        if(index >= nums.length)
            return 0;

        if(memo[index] >= 0)
            return memo[index];

        int result = Math.max(nums[index] + rob(nums, memo, index + 2) , rob(nums, memo, index + 1));
        memo[index] = result;
        return result;

    }

    //Bottom up DP
    public static int robDP(int nums[]){
        if(nums.length == 1)
            return nums[0];

        int dp[] = new int[nums.length + 1];

        dp[0] = 0;
        dp[1] = Math.max(nums[0],nums[1]);

        for(int i = 2; i < nums.length ; i++){
            dp[i] = Math.max(dp[i - 1],dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    //Space optmimzed DP
    public static int robDPSpace(int[] nums){
        if(nums.length == 1)
            return nums[0];

        int prev1 = 0;
        int prev2 = Math.max(nums[0], nums[1]);

        for(int i = 2; i < nums.length; i++){
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{2,1,1,2}));
    }
}
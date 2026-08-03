public class MaximumSumSubArray {
    public static int maxSubArray(int[] nums) {

        if(nums.length==1)
        return nums[0];

        int temp_sum = nums[0];
        int max_sum = nums[0];

        for(int i = 1; i < nums.length; i++){
            temp_sum = Math.max(nums[i],temp_sum + nums[i]);
            max_sum = Math.max(temp_sum,max_sum);
        }
        return max_sum;
    }
}

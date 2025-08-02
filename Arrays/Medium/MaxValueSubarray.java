import java.util.*;

public class MaxValueSubarray {
    public static int maxSubArray(int[] nums) {
        if(nums.length == 0 || nums == null)
            return 0;
        if(nums.length == 1)
            return(nums[0]);

        int max = nums[0];
        int temp_sum = nums[0];

        for(int i = 1 ; i<nums.length;i++){
            temp_sum = Math.max(nums[i],temp_sum+nums[i]);
            max = Math.max(max,temp_sum);
        }

    return max;
    }

    public static void main(String args[]){
        int arr[] = new int[]{5,4,-1,7,8};
        System.out.println(maxSubArray(arr));
    }
}

//    -2,1,-3,4,-1,2,1,-5,4

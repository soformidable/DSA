public class MaximumDifference {
    static int maximumDifference(int[] nums){
        if(nums.length<2)
            return -1;
        int min = nums[0];
        int max = nums[1] - nums[0];
        
        for(int i=1;i<nums.length;i++){
            max = Math.max(max, nums[i]-min);
            min = Math.min(min,nums[i]);
        }

        return max;
    }

    public static void main(String args[]){
        int arr[] = new int[]{7,1,5,4};
        System.out.println("Max : "+maximumDifference(arr));
    }
}

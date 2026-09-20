public class MaximumProductSubArray {
    public static int maxProduct(int[] nums) {

        if(nums.length == 1)
            return nums[0];

        int minProd = nums[0];
        int maxProd = nums[0];
        int result = nums[0];

        for(int i = 1; i < nums.length; i++){
            int current = nums[i];

            int candidate1 = current;
            int candidate2 = maxProd * current;
            int candidate3 = minProd * current;


            maxProd = Math.max(candidate1 , Math.max(candidate2,candidate3));
            minProd = Math.min(candidate1, Math.min(candidate2,candidate3));

            result = Math.max(maxProd, result);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{-2, 3 , -4}));
    }
}

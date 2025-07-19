public class ReversePairs {
    public static int reversePairs(int[] nums){
        int count = 0;
        for(int  i = 0 ; i < nums.length ; i ++)
            for(int j = i + 1 ; j < nums.length ; j ++)
                if(nums[i] > (nums[j] * 2))
                    count++;

        return count;
    }
    public static void main(String args[]){
        System.out.println(reversePairs(new int[]{2,4,3,5,1}));
    }
}

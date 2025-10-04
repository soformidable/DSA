public class MaxConsecutiveOnes {
    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        for(int i = 0 ; i < nums.length ; i ++){
            int streak = 0;
            int index = i;
            while(index < nums.length && nums[index] == 1){
                index++;
                streak++;
            }
            i = index;
            max = Math.max(max,streak);
        }
        return max;
    }
    public static void main(String args[]){
        int arr[] = new int[]{1,1,0,0,1,1,1};
        System.out.println(findMaxConsecutiveOnes(arr));
    }
}

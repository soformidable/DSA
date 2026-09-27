public class PeakElementLogn {
    public static int findPeakElement(int[] nums) {
        
        if(nums.length == 1)
            return 0;

        if(nums[0] > nums[1])
            return 0;

        if(nums[nums.length - 1] > nums[nums.length - 2])
            return nums.length - 1;

        int prev = nums[0];


        for(int i = 1 ; i < nums.length - 1 ; i++){
            if(nums[i] > nums[i+1] && nums[i] > prev)
                return i;
            else
                prev = nums[i];
        }

        return -1;
    }
    public static int findPeakElementBS(int[] nums) {

        if(nums.length == 0)
            return 0;
        
        int left = 0;
        int right = nums.length - 1;

        while(left < right){

            int mid = left + ((right - left) / 2);

            if(nums[mid] < nums[mid + 1])
                left = mid + 1;
            else
                right = mid;

        }

        return left;
    }
    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1,2,1,3,5,6,4}));
        System.out.println(findPeakElementBS(new int[]{1,2,3,5}));
    }
}

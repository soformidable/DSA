public class SearchInRotArray{
    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while(start <= end){
            int middle = start + (end - start) / 2;

            if(nums[middle] == target)
                return middle;

            // CHECK FOR THE SORTED HALF FIRST -> THEN CHECK FOR TARGET
            if(nums[start] <= nums[middle]){
                if(target >= nums[start] && target < nums[middle])
                    end = middle - 1;
                else
                    start = middle + 1;
            }
            else{
                if(nums[end] >= target && target > nums[middle])
                    start = middle + 1;
                else
                    end = middle - 1;
            }
                    
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0));
    }
}
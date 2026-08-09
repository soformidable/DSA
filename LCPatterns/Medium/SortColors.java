import java.util.Arrays;

public class SortColors {
    public static void sortColors(int[] nums) {

        if(nums == null || nums.length == 0)
            return;

        int r = 0, w =0, b = 0;
        int index = 0;

        for(int i =  0 ; i < nums.length ; i++){
            switch (nums[i]){
                case 0:
                    r++;
                    break;
                case 1:
                    w++;
                    break;
                case 2:
                    b++;
                    break;
            }
        }


            while(index < nums.length && r > 0){
                nums[index] = 0;
                index++;
                r--;
            }
            while(index < nums.length && w > 0){
                nums[index] = 1;
                index++;
                w--;
            }
            while(index < nums.length && b > 0){
                nums[index] = 2;
                index++;
                b--;
            }
    
    }


    public static void sortColors_DNF(int[] nums) {
        if (nums == null || nums.length == 0) return;
        
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;
        
        while (mid <= high) {
            if (nums[mid] == 0) {
                // Swap with low partition
                swap(nums, mid, low);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // Already in correct position
                mid++;
            } else { // nums[mid] == 2
                // Swap with high partition
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int arr[] = new int[]{2,0,2,1,1,0};
        sortColors(arr);
        System.out.println(Arrays.toString(arr));
    }
}

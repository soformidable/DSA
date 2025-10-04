import java.util.*;

public class RemoveDuplicates {
        public static int removeDuplicates(int[] nums) {
        if(nums == null  || nums.length == 0)
            return 0;

        //As the input is already sorted TreeSet is unnecessary to maintain the order
        TreeSet<Integer> hs = new TreeSet<Integer>();
        for(int x : nums)
            hs.add(x);
        int index = 0;
        for(Integer x : hs)
            nums[index++] = x;
        return hs.size();
    }

    public static int removeDuplicatesOP(int[] nums){
        if(nums == null && nums.length == 0)
            return 0;
        int uniqueIndex = 0;
        for(int i = 1 ; i < nums.length ; i ++){
            if(nums[uniqueIndex] != nums[i]){
                uniqueIndex++;
                nums[uniqueIndex] = nums[i];
            }
        }

        return uniqueIndex + 1;
    }

    public static void main(String args[]){
        int arr[] = new int[]{0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(arr));
        System.out.println(Arrays.toString(arr));
    }
}

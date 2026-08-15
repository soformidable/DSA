import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class SearchInRotatedArrayII {
    public static boolean search(int[] nums, int target) {
        


        Set<Integer> set = new HashSet<>();
        List<Integer> lst = new ArrayList<>();

        for(int x : nums){
            if(set.add(x))
                lst.add(x);
        }

        int start = 0;
        int end = lst.size() - 1;

        while(start <= end){
            int middle = start + (end - start) / 2;

            if(target == lst.get(middle))
                return true;

            if(lst.get(start) <= lst.get(middle)){
                if(target >= lst.get(start) && target < lst.get(middle))
                    end = middle - 1;
                else
                    start = middle + 1;
            }
            else{
                if(lst.get(end) >= target && target > lst.get(middle))
                    start = middle + 1;
                else
                    end = middle - 1;
            }
        }   

        return false;
    }

        public static boolean search_withouthashset(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while(start <= end){


            int middle = start + (end - start) / 2;

            if(nums[middle] == target)
                return true;


            // Cannot determine which side is sorted
            if(nums[start] == nums[middle] && nums[middle] == nums[end]){
                start++;
                end--;
            }

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
        return false;
    }

    public static void main(String[] args) {
        System.out.println(search_withouthashset(new int[]{1,0,1,1,1}, 0));
    }
}

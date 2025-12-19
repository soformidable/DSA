import java.util.*;

public class PowerSet {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {

        // Sorting the elements so that all the duplicates are consecutive
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        getSubs(0, nums, new ArrayList<>(), result);

        return result;
    }

    public static void getSubs(int index, int nums[], List<Integer> lst, List<List<Integer>> res){
        res.add(new ArrayList<>(lst));

        for(int i = index ; i < nums.length ; i++){
            if (i > index && nums[i] == nums[i-1])continue;
            
            lst.add(nums[i]);
            getSubs(i+1, nums, lst, res);
            lst.remove(lst.size() - 1);
        }
        
    }

    public static void main(String args[]){
        int arr[] = new int[]{1,2,2};
        System.out.println(subsetsWithDup(arr));
        
    }
}

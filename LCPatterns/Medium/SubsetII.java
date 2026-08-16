import java.util.Collections;
import java.util.List;
import java.util.ArrayList;


public class SubsetII {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();

        generate(new ArrayList<Integer>(), result, nums, 0);

        return result;
    }

    private static void generate(List<Integer> cand, List<List<Integer>> result,  int nums[], int index){

        result.add(new ArrayList<Integer>(cand));

        if(index >= nums.length)
            return;

        for(int i = index; i < nums.length ; i++){

            if(i > index && nums[i - 1] == nums[i]) continue;
            
          
            cand.add(nums[i]);
            generate(cand, result, nums, i + 1);
            cand.remove(cand.size() - 1);
        }

    }
    public static void main(String[] args) {
        System.out.println(subsetsWithDup(new int[]{1,2,2}));
    }
}

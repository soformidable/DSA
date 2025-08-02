import java.util.*;

public class FourSum {
    public static List<List<Integer>> fourSum(int nums[],int target){
       List<List<Integer>> lst = new ArrayList<>();
        if(nums.length < 4 || nums == null)
            return lst;
        Arrays.sort(nums);
        for(int i = 0 ; i < nums.length ; i ++){
            if(i> 0 && nums[i] == nums[i-1])
                continue;
            for(int j = i+1 ; j < nums.length ; j++){
                if(j > i + 1 && nums[j] == nums[j-1])
                    continue;
                int k = j+1, l = nums.length - 1;
                while(k < l){
                if((long)nums[i] + (long)nums[j] + (long)nums[k] + (long)nums[l] == (long)target){
                    lst.add(Arrays.asList(new Integer[]{nums[i],nums[j],nums[k],nums[l]}));
                    k++; l--;
                    while(k < l && nums[k-1] == nums[k])k++;
                    while(k < l && nums[l+1] == nums[l])l--;
                }
                else if((long)nums[i] + (long)nums[j] + (long)nums[k] + (long)nums[l] > (long)target)
                    l--;
                else
                    k++;
                }
            }
        }
        
        return lst;
    }
    public static void main(String args[]){
        System.out.println(fourSum(new int[]{-2,-1,-1,1,1,2,2}, 0));
    }
}

// [1,0,-1,0,-2,2] ;; target = 0
// Sorted -> [-2, -1, 0, 0, 1, 2].








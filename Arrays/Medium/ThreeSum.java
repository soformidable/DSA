import java.util.*;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> lst = new ArrayList<List<Integer>>();

        for(int i = 0 ; i < nums.length ; i ++){
            if(i > 0 && nums[i-1] == nums[i])
                continue;
            int j = i + 1 , k = nums.length - 1;
            if(j > i + 1 && nums[j] == nums[j-1])
                continue;
            while(j < k){
            if((long)nums[i] + (long)nums[j] + (long)nums[k] == 0L){
                lst.add(Arrays.asList(nums[i],nums[j],nums[k]));
                j++;k--;
                while(j<k && nums[j-1]==nums[j]) j++;
                while(j<k && nums[k+1]==nums[k]) k--;
            }
            else if ((long)nums[i] + (long)nums[j] + (long)nums[k] > 0L)
                k--;
            else
                j++;
        }
        }
        return lst;
    }

    public static List<List<Integer>> triplet(int n, int[] arr) {
        Set<List<Integer>> st = new HashSet<>();

        for (int i = 0; i < n; i++) {
            Set<Integer> hashset = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                //Calculate the 3rd element:
                int third = -(arr[i] + arr[j]);

                //Find the element in the set:
                if (hashset.contains(third)) {
                    List<Integer> temp = Arrays.asList(arr[i], arr[j], third);
                    temp.sort(null);
                    st.add(temp);
                }
                hashset.add(arr[j]);
            }
        }

        // store the set elements in the answer:
        List<List<Integer>> ans = new ArrayList<>(st);
        return ans;
    }

    public static void main(String args[]){
        int nums[] = new int[]{-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }
}

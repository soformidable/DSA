import java.util.*;

public class KDistantIndices {

    // public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {

    //     List<Integer> lst = new ArrayList<Integer>();

    //     if(nums.length == 0 || nums == null)
    //         return lst;

    //     List<Integer> lst_key = new ArrayList<Integer>();

    //     for(int i = 0 ; i < nums.length;i++){
    //         if(nums[i] == key)
    //             lst_key.add(i);
    //     }
    //     System.out.println(lst_key);

    //     for(int i = 0 ; i <nums.length ; i++){
    //         int index = 0;
    //         while(index < lst_key.size()){
    //             if(Math.abs(lst_key.get(index) - i) <= k && nums[lst_key.get(index)] == key && !lst.contains(i))
    //                 lst.add(i);
    //             index++;
    //         }
    //     }

    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
    List<Integer> result = new ArrayList<>();
    int lastAddedEnd = -1;

    for (int i = 0; i < nums.length; i++) {
        if (nums[i] == key) {
            int start = Math.max(lastAddedEnd + 1, i - k);
            int end = Math.min(i + k, nums.length - 1);
            for (int j = start; j <= end; j++) {
                result.add(j);
            }
            lastAddedEnd = end;
        }
    }
    return result;
    }
    public static void main(String args[]){
        System.out.println(findKDistantIndices(new int[]{1,1000,1,1000}, 1, 1));
    }
}

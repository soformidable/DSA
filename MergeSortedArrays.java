import java.util.*;

public class MergeSortedArrays {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1[0] == 0 && nums2 != null && nums1.length == 1){
            nums1[0] = nums2[0];
            return;
        }

        if(nums2==null)
            return;
        
        
        
        for(int i = 0 ; i < n ; i ++){
            nums1[m+i] = nums2[i];
        }    
        
    }
    public static void main(String args[]){
        int nums1[] = new int[]{0,0,0,0,0};
        int nums2[] = new int[]{1,2,3,4,5};
        merge(nums1,0,nums2,5);
        System.out.println(Arrays.toString(nums1));
    }
}



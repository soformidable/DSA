import java.util.*;

public class TwoSum{
    public static int[] twoSum(int nums[] , int target){
        int i=0,j=0;
        for(i = 0 ; i < nums.length;i++){
            for(j = i+1 ; j < nums.length; j++){
                if(nums[i] + nums[j] == target)
                    return(new int[]{i,j});
            }
        }
        return (new int[]{});
    }
    public static int[] twoSumHash(int nums[],int target){
        HashMap<Integer,Integer> hs = new HashMap<Integer,Integer>();
        for(int i = 0 ; i < nums.length ; i++){
            int num = nums[i];
            int diff = target - num;
            if(hs.containsKey(diff))
                return(new int[]{i,hs.get(diff)});
            else
                hs.put(num,i);
        }
        return(new int[]{-1,-1});
    }

    //Only works for the variant where answer is boolean (Yes/No)
    public static int[] twoSumTwoPoint(int nums[] , int target){
        Arrays.sort(nums);
        int start = 0 , end = nums.length - 1;
        while(start < end){
            if(nums[end] + nums[start] == target)
                return(new int[]{start,end});
            else if(nums[end] + nums[start] < target)
                end--;
            else
                start++;
        }
        return (new int[]{-1,-1});
    }

    public static void main(String args[]){
        System.out.println(Arrays.toString(twoSum(new int[]{3,3}, 6)));
        System.out.println(Arrays.toString(twoSumHash(new int[]{3,3}, 6)));
        System.out.println(Arrays.toString(twoSumTwoPoint(new int[]{3,2,4}, 6)));
    }
}

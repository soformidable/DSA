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
    public static void main(String args[]){
        System.out.println(Arrays.toString(twoSum(new int[]{2,7,11,15}, 9)));
    }
}
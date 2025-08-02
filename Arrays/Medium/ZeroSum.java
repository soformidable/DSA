import java.util.*;

public class ZeroSum {
    public static int zeroSum(int nums[]){
        int maxCount = 0;

        for(int i = 0 ; i < nums.length ; i ++){
            int sum = 0;
            int index = i;
            int count = 0;
            while(index < nums.length){
                sum += nums[index];
                if(sum == 0)
                    count = (index - i)+1;
                index++;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }

    public static int zeroSumPrefix(int nums[]){
        int sum = 0, maxLength = 0;
        Map<Integer,Integer> hm = new HashMap<Integer,Integer>();

        for(int i = 0 ; i < nums.length ; i ++){
            sum+=nums[i];

            if(sum == 0)
                maxLength = i + 1;
            if(hm.containsKey(sum)){
                maxLength = Math.max((i - hm.get(sum)), maxLength);
            }
            else
                hm.put(sum,i);
        }

        return maxLength;
    }

    public static void main(String args[]){
        System.out.println(zeroSum(new int[]{6, -2, 2, -8, 1, 7, 4, -10}));
        System.out.println(zeroSumPrefix(new int[]{9, -3, 3, -1, 6, -5}));
    }
}
